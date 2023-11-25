package com.twoclothing.huiwen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.twoclothing.gordon.service.MembersService;
import com.twoclothing.gordon.service.MembersServiceImpl;
import com.twoclothing.huiwen.service.BalanceHistoryService;
import com.twoclothing.huiwen.service.BalanceHistoryServiceImpl;
import com.twoclothing.huiwen.service.ItemService;
import com.twoclothing.huiwen.service.ItemServiceImpl;
import com.twoclothing.huiwen.service.PointHistoryService;
import com.twoclothing.huiwen.service.PointHistoryServiceImpl;
import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.balancehistory.BalanceHistory;
import com.twoclothing.model.coupon.Coupon;
import com.twoclothing.model.members.Members;
import com.twoclothing.model.memberscoupon.MembersCoupon;
import com.twoclothing.model.memberscoupon.MembersCoupon.MembersCouponCompositeDetail;
import com.twoclothing.model.pointhistory.PointHistory;
import com.twoclothing.model.shipsetting.ShipSetting;
import com.twoclothing.redismodel.notice.Notice;
import com.twoclothing.utils.JedisPoolUtil;
import com.twoclothing.utils.generic.DAOSelector;
import com.twoclothing.utils.generic.GenericDAO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@WebServlet("/ItemCart/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 2 * 1024 * 1024, maxRequestSize = 2 * 2 * 1024 * 1024)
public class ItemCartServlet extends HttpServlet {

	private ItemService itemService;
	
	private PointHistoryService PHSvc;
	
	private BalanceHistoryService BHSvc;
	
	private MembersService memSvc;
	
	private GenericDAO couponDAO;


	public void init() throws ServletException {

		itemService = new ItemServiceImpl();
		PHSvc = new PointHistoryServiceImpl();
		memSvc = new MembersServiceImpl();
		BHSvc = new BalanceHistoryServiceImpl();
		couponDAO = DAOSelector.getDAO(MembersCoupon.class);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text; charset=UTF-8");

		// 商品詳情到購物車
		if ("gotoCart".equals(req.getParameter("gotoCart"))) {

			String itemId = req.getParameter("itemId");
			List<Item> itemList = new ArrayList<>();
			Item item = itemService.getItemByItemId(Integer.valueOf(itemId));
			itemList.add(item);

			String quantity = req.getParameter("quantity");

			HttpSession session = req.getSession();
			String mbrId = String.valueOf(session.getAttribute("mbrId"));

			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			jedis.select(13);
			try {
			    jedis.hset(mbrId, itemId, quantity);		        
				req.setAttribute("item", itemList);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();	
			}
			
		    String responseData = "加入成功！"; // 這裡是要回傳的訊息

		    PrintWriter out = res.getWriter();
		    out.print(responseData);
		    out.flush();

		}
		
		if ("addCartNum".equals(req.getParameter("addCartNum"))) {
			String itemIdStr = req.getParameter("itemId");
			
			List<Integer> keyList = new ArrayList<>();
			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			jedis.select(13);
			
			try {
	
				HttpSession session = req.getSession();
				String mbrId = String.valueOf(session.getAttribute("mbrId"));
				
			    Set<String> keys = jedis.hkeys(mbrId);
			    for (String key : keys) {
			    	keyList.add(Integer.valueOf(key));
			    }

			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();	
			}
			boolean isItemIdExist = keyList.contains(Integer.valueOf(itemIdStr));
			String responseData2 = String.valueOf(isItemIdExist); // 將布林值轉為文字
			PrintWriter out = res.getWriter();
			out.print(responseData2);
			out.flush();
		}
		
		//查看購物車
		if ("cart".equals(req.getParameter("goto"))) {
			//抓會員存的商品id
			List<Item> itemList = new ArrayList<>();
			//抓商品的數量
			List<String> quantities = new ArrayList<>();
			Map<Integer, Integer> map = new HashMap<>();//賣家點數判斷用

			HttpSession session = req.getSession();
			String mbrIdStr = String.valueOf(session.getAttribute("mbrId"));
			
			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			
			try {
				jedis.select(13);
				Set<String> itemIds = jedis.hkeys(mbrIdStr);
				
				
				//抓出存在Redis的商品顯示在會員購物車
				for (String itemId : itemIds) {
					String quantity = jedis.hget(mbrIdStr, itemId);
					quantities.add(quantity);
					Item item = itemService.getItemByItemId(Integer.valueOf(itemId));
					itemList.add(item);
//					取得商品的賣家與賣家分數						
					Integer mbrIdSell = item.getMbrId();
					if (!map.containsKey(mbrIdSell)) {
						Integer score = itemService.getSellScoreByMbrId(mbrIdSell);
						map.put(mbrIdSell, score);
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();
			}
			
			//若賣家權限分超過即不可購買該商品
			List<Integer> itemIdListEnableBuy = new ArrayList<>();//不可買的所有itemId
			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			    if (entry.getValue() == 0) {
			    	List<Integer> itemIdList = itemService.getItemByMbrId(entry.getKey());
			    	itemIdListEnableBuy.addAll(itemIdList);
			    }
			}
			
			//取得會員擁有的點數
			Integer mbrId = Integer.valueOf(mbrIdStr);
			Integer mbrPoint = itemService.getMbrPointByMbrId(mbrId);
			

			//取得該會員擁有(且可用)的優惠券(狀態為0:未使用的)
			List<MembersCoupon> membersCouponList = itemService.getMemCouponByMbrId("mbrIdStr", mbrId);
			List<MembersCoupon> elementsToRemove = new ArrayList<>();
			for(MembersCoupon membersCoupon : membersCouponList) {
				int status = membersCoupon.getCouponStatus();
				if(status != 0) {
					elementsToRemove.add(membersCoupon);
				}
			}
			//獲得所有狀態為0的優惠券
			membersCouponList.removeAll(elementsToRemove);
			
			//取得該優惠券的物件(條件:日期未失效)
			List<Coupon> couponList = new ArrayList<>();
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			for(MembersCoupon membersCoupon : membersCouponList) {
				Integer CouponId = membersCoupon.getCompositeKey().getCouponId();
				Coupon coupon = itemService.getCouponByPK(CouponId);
				if((coupon.getExpireDate()) == null || (coupon.getExpireDate()).compareTo(currentTime) > 0 ) {
					couponList.add(coupon);
				}
			}

			req.setAttribute("itemList", itemList);
			req.setAttribute("quantities", quantities);
			req.setAttribute("mbrPoint", mbrPoint);
			req.setAttribute("MembersCouponList", membersCouponList);
			req.setAttribute("couponList", couponList);
			req.setAttribute("itemIdListEnableBuy", itemIdListEnableBuy);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/front_end/item/cartDetail.jsp");
			dispatcher.forward(req, res);
			return;
			
		}
		
		//使用者刪除購物車某商品(Redis刪除)
		if ("delCart".equals(req.getParameter("delCart"))) {
			String itemId = req.getParameter("itemId");
			HttpSession session = req.getSession();
			String mbrId = String.valueOf(session.getAttribute("mbrId"));
			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			try {
				jedis.select(13);
				jedis.hdel(mbrId, itemId);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();
			}
		}
		
		//使用者在購物車修改數量(Redis修改)
		if("updateCart".equals(req.getParameter("updateCart"))) {
			String itemId = req.getParameter("itemId");
			String quantity = req.getParameter("quantity");
			HttpSession session = req.getSession();
			String mbrId = String.valueOf(session.getAttribute("mbrId"));
			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			try {
				jedis.select(13);
				jedis.hset(mbrId, itemId, quantity);
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();
			}
		}
		
		//準備結帳
		if ("toPay".equals(req.getParameter("toPay"))) {
			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			List<Item> itemList = new ArrayList<>();
			List<String> quantities = new ArrayList<>();
			//之後從session取mbrId
			HttpSession session = req.getSession();
			String mbrId = String.valueOf(session.getAttribute("mbrId"));
			try {
				jedis.select(13);
			
				//取得有選取的itemId查對應Item
				//購買數量從Redis取
				String[] itemIdCheckValues = req.getParameterValues("itemIdCheck");
				if (itemIdCheckValues != null) {
					for (String itemId : itemIdCheckValues) {
						quantities.add(jedis.hget(mbrId, itemId));					
						Item item = itemService.getItemByItemId(Integer.valueOf(itemId));
						itemList.add(item);
					}
				}							
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();	
			}
		
			//取得會員物流資訊
			List<ShipSetting> shipSettingList = itemService.getSettingByMbrId(Integer.valueOf(mbrId));
			ShipSetting shipSetting = null;
			if (!shipSettingList.isEmpty()) {
				shipSetting = shipSettingList.get(0);
			}
			//取得會員使用點數
			String mbrPointStr = req.getParameter("mbrPoint");
			Integer mbrPoint = 0;
			if(mbrPointStr != null) {
				mbrPoint = Integer.valueOf(mbrPointStr);
				
			}
						
			//取得會員錢包餘額
			Integer balanceEableUse = itemService.getMbrBalanceByMbrId(Integer.valueOf(mbrId));
			
			//取得折扣金額
			Integer cartCount = Integer.valueOf(req.getParameter("cartCount"));
			
			//取得被選到的優惠券
			String cpnIdStr = req.getParameter("cpnName");
			Integer cpnId = 0;
			if(cpnIdStr!=null) {
				cpnId = Integer.valueOf(cpnIdStr);
			}
			req.setAttribute("itemList", itemList);
			req.setAttribute("quantities", quantities);
			req.setAttribute("shipSetting", shipSetting);
			req.setAttribute("cartCount", cartCount);
			req.setAttribute("balanceEableUse", balanceEableUse);
			req.setAttribute("mbrPoint", mbrPoint);
			req.setAttribute("cpnId", cpnId);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/front_end/item/cartToOrder.jsp");
			dispatcher.forward(req, res);
			return;
		}
		
		//送去產生訂單
		//測試，到時放在啟榮的訂單servlet
        String requestURI = req.getRequestURI();
        
        if (requestURI.endsWith("/toOrder")) {
			HttpSession session = req.getSession();
			String mbrIdStr = String.valueOf(session.getAttribute("mbrId"));
			Integer mbrId = 0;
			if(mbrIdStr!=null) {
				mbrId = Integer.valueOf(mbrIdStr);
			}
            // 發送通知
            Notice notice = new Notice();
            notice.setType("訂單通知");
            notice.setHead("訂單成立通知");
            notice.setContent("感謝您的購買，付款後即通知賣家出貨");
        	//連到訂單頁面的link
            notice.setLink("#");
            notice.setImageLink("/images/cart/placeOrder.png");
            itemService.addNotice(notice, mbrId);
        	
        	//購物車清空
        	//處理商品id存成陣列
            String itemIdStr = req.getParameter("itemId");
            String[] parts = itemIdStr.split(",");
          
            //準備陣列存商品id
            List<Integer> itemIdArr = new ArrayList<>();
            for (String part:parts) {
            	Integer itemId = Integer.parseInt(part);
                itemIdArr.add(itemId);              
            }  
            JedisPool jedisPool = JedisPoolUtil.getJedisPool();
            Jedis jedis = jedisPool.getResource();
            jedis.select(13);
                     
			for (Integer Id : itemIdArr) {
				//該商品扣庫存 
				String quantityStr = jedis.hget(mbrIdStr, String.valueOf(Id));
				
				Item item = itemService.getItemByItemId(Id);
				Integer newInventory = (item.getQuantity())-(Integer.valueOf(quantityStr));
				item.setQuantity(newInventory);
				//如果扣完後庫存為0即自動下架
				if(newInventory == 0) {
					item.setItemStatus(1);
				}
				Integer success =itemService.updateItem(item);
				
				//賣家錢包++
				Integer mbrIdBalanceAdd = itemService.getMbrIdByItemId(Id);
				Integer itemPrice = itemService.getItemPriceByItemId(Id);
            	Members mem = memSvc.getByPrimaryKey(mbrIdBalanceAdd);
            	Integer newBalance = mem.getBalance()+itemPrice;
            	mem.setBalance(newBalance);
            	memSvc.updateMembers(mem);
            	
            	//賣家錢包異動紀錄++
            	BalanceHistory balanceHistory = new BalanceHistory();
    			
    			//取訂單編號
//    			if(!req.getParameter("orderId").trim().isEmpty() ) {
//    				int orderId =Integer.parseInt(req.getParameter("orderId"));
//    			}
    			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    			
    			balanceHistory.setMbrId(mbrIdBalanceAdd);
    			balanceHistory.setOrderId(1);//從訂單取
    			//異動時間1/訂單完成(+) 2/訂單確認(-)
    			balanceHistory.setChangeDate(currentTime);			
    			balanceHistory.setChangeValue(itemPrice);
    			
    			int balanceHistoryPK = BHSvc.addBH(balanceHistory);

			}
            //清購物車
			try {
				for(Integer itemId:itemIdArr) {
					jedis.hdel(String.valueOf(mbrId), String.valueOf(itemId));					
				}				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();
			}
 	
        	//買家若有使用會員點數即扣點與新增異動
			String mbrPointStr = req.getParameter("mbrPoint");
			Integer mbrPoint = 0;
        	if(!(mbrPointStr.trim().equals("0") && mbrPointStr!=null)) {
    			mbrPoint = Integer.valueOf(mbrPointStr);
    			//新增異動
    			PointHistory pointHistory = new PointHistory();
    			
    			//取訂單編號
//    			if(!req.getParameter("orderId").trim().isEmpty() ) {
//    				int orderId =Integer.parseInt(req.getParameter("orderId"));
//    			}
    			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    			
    			pointHistory.setMbrId(mbrId);
    			pointHistory.setOrderId(1);//從訂單取
    			//異動時間1/訂單完成(+) 2/訂單確認(-)
    			pointHistory.setChangeDate(currentTime);			
    			pointHistory.setChangeValue(mbrPoint*-1);
    			
    			int pointHistoryPK = PHSvc.addPH(pointHistory);
    			
    			//會員表格點數同步扣點
            	Members mem=memSvc.getByPrimaryKey(mbrId);
            	Integer newPoint = mem.getMbrPoint()-mbrPoint;
            	mem.setMbrPoint(newPoint);
            	memSvc.updateMembers(mem);
        		
        	}

        	//買家若用虛擬錢包，扣錢包與新增異動//如果==2代表選擇虛擬錢包付款
            Integer payment = Integer.valueOf(req.getParameter("payment"));//付款方式
            Integer totalPay = Integer.valueOf(req.getParameter("totalPay"));//要付總額
            if(payment == 2) {
            	Members mem=memSvc.getByPrimaryKey(mbrId);
            	Integer newBalance = mem.getBalance()-totalPay;
            	mem.setBalance(newBalance);
            	memSvc.updateMembers(mem);
            	
    			//新增異動
    			BalanceHistory balanceHistory = new BalanceHistory();
    			
    			//取訂單編號
//    			if(!req.getParameter("orderId").trim().isEmpty() ) {
//    				int orderId =Integer.parseInt(req.getParameter("orderId"));
//    			}
    			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    			
    			balanceHistory.setMbrId(mbrId);
    			balanceHistory.setOrderId(1);//從訂單取
    			balanceHistory.setWrId(null);
    			//異動時間1/訂單完成(+) 2/訂單確認(-)
    			balanceHistory.setChangeDate(currentTime);			
    			balanceHistory.setChangeValue(totalPay*-1);
    			
    			int balanceHistoryPK = BHSvc.addBH(balanceHistory);            	
            }
            
            //使用後的優惠券改變狀態
            String cpnIdStr = req.getParameter("cpnId");
            Integer cpnId = 0;
            if(cpnIdStr!=null) {
            	cpnId = Integer.valueOf(cpnIdStr);
            }
			MembersCouponCompositeDetail memcoupon = new MembersCouponCompositeDetail();
			memcoupon.setCouponId(cpnId);
			memcoupon.setMemberId(mbrId);
			
			MembersCoupon membersCoupon = (MembersCoupon)couponDAO.getByPrimaryKey(memcoupon);
			
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			membersCoupon.setCouponStatus(1);
			membersCoupon.setUseDate(currentTime);
			boolean boo = couponDAO.update(membersCoupon);
            
        	
        	//測試結束=============================================================
        	//以下是取jsp資料，請忽略=================================================
        	
//        	//處理商品id存成陣列
//            String itemIdStr = req.getParameter("itemId");
//            String[] parts = itemIdStr.split(",");
//            
//            //準備陣列存商品id、會員id
//            List<Integer> itemIdArr = new ArrayList<>();
//            List<Integer> mbrIdArr = new ArrayList<>();
//            for (String part:parts) {
//                Integer itemId = Integer.parseInt(part);
//                itemIdArr.add(itemId);
//                
//                //用商品id取得該會員id
//                Integer mbr = itemService.getMbrIdByItemId(itemId);
//                mbrIdArr.add(mbr);
//                
//            }	
//            System.out.println(itemIdArr);
//            System.out.println(mbrIdArr);
//            
//            //處理數量存成陣列
//            String quantityStr = req.getParameter("quantity");
//            String[] parts2 = quantityStr.split(",");
//            
//            List<Integer> quantityArr = new ArrayList<>();
//            for (String part:parts2) {
//            	Integer quantity = Integer.parseInt(part);
//            	quantityArr.add(quantity);
//            	
//            }
//            System.out.println(quantityArr);
//            Integer payment = Integer.valueOf(req.getParameter("payment"));

//            
//            String receiveName = req.getParameter("receiveName");
//            String receivePhone = req.getParameter("receivePhone");
//            String receiveAddress = req.getParameter("receiveAddress");
//            
//            
//            //處理比例折扣存成陣列
//            String eachCountStr = req.getParameter("eachCount");
//            String[] parts3 = eachCountStr.split(",");
//            
//            List<Integer> countArr = new ArrayList<>();
//            for (String part:parts3) {
//            	Integer eachCount = Integer.parseInt(part);
//            	countArr.add(eachCount);
//            	
//            }
//            System.out.println(countArr);
//            
//            //未折扣金額
//            Integer mytotal = Integer.valueOf(req.getParameter("mytotal"));
//            
//            //折扣總額
//            Integer count = Integer.valueOf(req.getParameter("count"));
//            
//            //折扣後金額
//            Integer totalPay = Integer.valueOf(req.getParameter("totalPay"));
//
//            
//     
        }
        
		
		
		
	}
}
