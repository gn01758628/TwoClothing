package com.twoclothing.huiwen.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
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

import com.twoclothing.huiwen.service.ItemService;
import com.twoclothing.huiwen.service.ItemServiceImpl;
import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.coupon.Coupon;
import com.twoclothing.model.memberscoupon.MembersCoupon;
import com.twoclothing.model.shipsetting.ShipSetting;
import com.twoclothing.utils.JedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@WebServlet("/ItemCart/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 2 * 1024 * 1024, maxRequestSize = 2 * 2 * 1024 * 1024)
public class ItemCartServlet extends HttpServlet {

	private ItemService itemService;

	public void init() throws ServletException {

		itemService = new ItemServiceImpl();
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
			System.out.println("quantity" + quantity);

			String mbrId = req.getParameter("mbrId");
			System.out.println("mbrId" + mbrId);
			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			jedis.select(13);

			String[] itemIds = { itemId };
			String[] quantities = { quantity };

			for (int i = 0; i < itemIds.length; i++) {
				jedis.hset(mbrId, itemIds[i], quantities[i]);
			}

			Map<String, String> productInfo = jedis.hgetAll(mbrId);

			// 打印商品ID与数量
//			for (Map.Entry<String, String> entry : productInfo.entrySet()) {
//				System.out.println("Product ID: " + entry.getKey() + ", Quantity: " + entry.getValue());
//			}

			System.out.println("productInfo:" + productInfo);
			System.out.println("itemList:" + itemList);

			req.setAttribute("item", itemList);
			jedis.close();

		}
		//查看購物車
		if ("cart".equals(req.getParameter("goto"))) {
			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			jedis.select(13);

			System.out.println("mbrId:" + req.getParameter("mbrId"));

			String mbrIdStr = req.getParameter("mbrId");
			
			//抓會員存的商品id
			List<Item> itemList = new ArrayList<>();
			Set<String> itemIds = jedis.hkeys(mbrIdStr);
			
			//抓商品的數量
			List<String> quantities = new ArrayList<>();
			
			//抓出存在Redis的商品顯示在會員購物車
			for (String itemId : itemIds) {
				String quantity = jedis.hget(mbrIdStr, itemId);
				quantities.add(quantity);
				Item item = itemService.getItemByItemId(Integer.valueOf(itemId));
				itemList.add(item);
			}
			
			jedis.close();
			
			//取得會員擁有的點數
			Integer mbrId = Integer.valueOf(mbrIdStr);
			Integer mbrPoint = itemService.getMbrPointByMbrId(mbrId);
			

			//取得該會員擁有(且可用)的優惠券(狀態為0:未使用的)
			List<MembersCoupon> membersCouponList = itemService.getMemCouponByMbrId("mbrIdStr", mbrId);
			List<MembersCoupon> elementsToRemove = new ArrayList<>();
			for(MembersCoupon membersCoupon : membersCouponList) {
				int status = membersCoupon.getCouponStatus();
				System.out.println("status"+status);
				if(status != 0) {
					elementsToRemove.add(membersCoupon);
				}
			}
			//獲得所有狀態為0的優惠券
			membersCouponList.removeAll(elementsToRemove);

			System.out.println("尚未使用的會員優惠券"+membersCouponList);
			
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
			
			System.out.println("couponList:"+couponList);
//			System.out.println("MembersCouponList:"+membersCouponList);
//			System.out.println("mbrPoint"+mbrPoint);
//			System.out.println("quantities:" + quantities);
			System.out.println("itemList" + itemList);

			
			req.setAttribute("itemList", itemList);
			req.setAttribute("quantities", quantities);
			req.setAttribute("mbrPoint", mbrPoint);
			req.setAttribute("MembersCouponList", membersCouponList);
			req.setAttribute("couponList", couponList);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/front_end/item/cartDetail.jsp");
			dispatcher.forward(req, res);
			return;
			
		}
		
		//使用者刪除購物車某商品(Redis刪除)
		if ("delCart".equals(req.getParameter("delCart"))) {
			String itemId = req.getParameter("itemId");
			String mbrId = req.getParameter("mbrId");
			System.out.println("delCart_itemId:"+itemId);
			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			jedis.select(13);
			jedis.hdel(mbrId, itemId);
			jedis.close();
		}
		
		//使用者在購物車修改數量(Redis修改)
		if("updateCart".equals(req.getParameter("updateCart"))) {
			String itemId = req.getParameter("itemId");
			String quantity = req.getParameter("quantity");
			String mbrId = req.getParameter("mbrId");
			
			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			jedis.select(13);
			jedis.hset(mbrId, itemId, quantity);
			jedis.close();

		}
		
		//準備結帳
		if ("toPay".equals(req.getParameter("toPay"))) {
			System.out.println("準備結帳");
			
			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			jedis.select(13);
			
			//之後從session取mbrId
			String mbrId = "2";
			
			//取得有選取的itemId查對應Item
			//購買數量從Redis取
			List<Item> itemList = new ArrayList<>();
			List<String> quantities = new ArrayList<>();
			String[] itemIdCheckValues = req.getParameterValues("itemIdCheck");
			if (itemIdCheckValues != null) {
			    for (String itemId : itemIdCheckValues) {
			        System.out.println("Selected itemId: " + itemId);
			        
					quantities.add(jedis.hget(mbrId, itemId));
					
					Item item = itemService.getItemByItemId(Integer.valueOf(itemId));
					System.out.println(item);
					itemList.add(item);
			    }
			}
			
			jedis.close();
			
			//取得會員物流資訊
			List<ShipSetting> shipSettingList = itemService.getSettingByMbrId(Integer.valueOf(mbrId));
			ShipSetting shipSetting = shipSettingList.get(0);
			System.out.println(shipSetting);
			
			//取得折扣金額
			Integer cartCount = Integer.valueOf(req.getParameter("cartCount"));
			
//			//取得付款方式
//			String payment = req.getParameter("payMethod");
//			System.out.println(payment);
			
			req.setAttribute("itemList", itemList);
			req.setAttribute("quantities", quantities);
			req.setAttribute("shipSetting", shipSetting);
			req.setAttribute("cartCount", cartCount);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/front_end/item/cartToOrder.jsp");
			dispatcher.forward(req, res);
			return;
		}
		
		//送去產生訂單
        String requestURI = req.getRequestURI();
        
        if (requestURI.endsWith("/toOrder")) {
        	//處理商品id存成陣列
            String itemIdStr = req.getParameter("itemId");
            String[] parts = itemIdStr.split(",");
            
            //準備陣列存商品id、會員id
            List<Integer> itemIdArr = new ArrayList<>();
            List<Integer> mbrIdArr = new ArrayList<>();
            for (String part:parts) {
                Integer itemId = Integer.parseInt(part);
                itemIdArr.add(itemId);
                
                //用商品id取得該會員id
                Integer mbr = itemService.getMbrIdByItemId(itemId);
                mbrIdArr.add(mbr);
                
            }	
            System.out.println(itemIdArr);
            System.out.println(mbrIdArr);
            
            //處理數量存成陣列
            String quantityStr = req.getParameter("quantity");
            String[] parts2 = quantityStr.split(",");
            
            List<Integer> quantityArr = new ArrayList<>();
            for (String part:parts2) {
            	Integer quantity = Integer.parseInt(part);
            	quantityArr.add(quantity);
            	
            }
            System.out.println(quantityArr);
            Integer payment = Integer.valueOf(req.getParameter("payment"));

            
            String receiveName = req.getParameter("receiveName");
            String receivePhone = req.getParameter("receivePhone");
            String receiveAddress = req.getParameter("receiveAddress");
            
            
            //處理比例折扣存成陣列
            String eachCountStr = req.getParameter("eachCount");
            String[] parts3 = eachCountStr.split(",");
            
            List<Integer> countArr = new ArrayList<>();
            for (String part:parts3) {
            	Integer eachCount = Integer.parseInt(part);
            	countArr.add(eachCount);
            	
            }
            System.out.println(countArr);
            
            //未折扣金額
            Integer mytotal = Integer.valueOf(req.getParameter("mytotal"));
            
            //折扣總額
            Integer count = Integer.valueOf(req.getParameter("count"));
            
            //折扣後金額
            Integer totalPay = Integer.valueOf(req.getParameter("totalPay"));

            
     
        }
        
		
		
		
	}
}
