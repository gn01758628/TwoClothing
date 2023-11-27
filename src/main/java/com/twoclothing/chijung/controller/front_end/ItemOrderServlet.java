package com.twoclothing.chijung.controller.front_end;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.twoclothing.gordon.service.MembersService;
import com.twoclothing.gordon.service.MembersServiceImpl;
import com.twoclothing.huiwen.service.BalanceHistoryService;
import com.twoclothing.huiwen.service.BalanceHistoryServiceImpl;
import com.twoclothing.huiwen.service.ItemService;
import com.twoclothing.huiwen.service.ItemServiceImpl;
import com.twoclothing.huiwen.service.PointHistoryService;
import com.twoclothing.huiwen.service.PointHistoryServiceImpl;
import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.aproduct.itemorder.ItemOrder;
import com.twoclothing.model.aproduct.orderdetails.OrderDetails;
import com.twoclothing.model.aproduct.orderdetails.OrderDetails.OrderDetailsCompositeDetail;
import com.twoclothing.model.balancehistory.BalanceHistory;
import com.twoclothing.model.members.Members;
import com.twoclothing.model.memberscoupon.MembersCoupon;
import com.twoclothing.model.memberscoupon.MembersCoupon.MembersCouponCompositeDetail;
import com.twoclothing.model.pointhistory.PointHistory;
import com.twoclothing.redismodel.notice.Notice;
import com.twoclothing.utils.JedisPoolUtil;
import com.twoclothing.utils.generic.DAOSelector;
import com.twoclothing.utils.generic.GenericDAO;
import com.twoclothing.utils.generic.GenericService;
import com.twoclothing.utils.generic.QueryCondition;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@WebServlet("/front_end/itemorder/itemorder.check")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ItemOrderServlet extends HttpServlet{
	
	private GenericService gs;
	//==================================
	private ItemService itemService;
	
	private PointHistoryService PHSvc;
	
	private BalanceHistoryService BHSvc;
	
	private MembersService memSvc;
	
	private GenericDAO couponDAO;
	
	
	
//	@Override
	public void init() throws ServletException {
		this.gs = gs.getInstance();
		//==================================
		itemService = new ItemServiceImpl();
		PHSvc = new PointHistoryServiceImpl();
		memSvc = new MembersServiceImpl();
		BHSvc = new BalanceHistoryServiceImpl();
		couponDAO = DAOSelector.getDAO(MembersCoupon.class);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		
		
		switch (action) {
			case "buyer":	
				getBuyerOrderList(req, res);
				forwardPath ="bAll.jsp";
				break;
			case "buyer0":	
				buyerStatus(req,res,0);
				forwardPath ="bUnpaid.jsp";
				break;
			case "buyer1":	
				buyerStatus(req,res,1);
				forwardPath ="bUnshipped.jsp";
				break;
			case "buyer2":	
				buyerStatus(req,res,2);
				forwardPath ="bUnreceived.jsp";
				break;
			case "buyer3":	
				buyerStatus(req,res,3);
				forwardPath ="bFinished.jsp";
				break;
			case "buyer4":	
				buyerStatus(req,res,4);
				forwardPath ="bCancel.jsp";
				break;
				
			case "seller":
				sellerAll(req, res);
				forwardPath ="sAll.jsp";
				break;
			case "seller0":
				sellerStatus(req,res,0);
				forwardPath ="sUnpaid.jsp";
				break;
			case "seller1":
				sellerStatus(req,res,1);
				forwardPath ="sUnshipped.jsp";
				break;
			case "seller2":
				sellerStatus(req,res,2);
				forwardPath ="sUnreceived.jsp";
				break;
			case "seller3":
				sellerStatus(req,res,3);
				forwardPath ="sFinished.jsp";
				break;
			case "seller4":
				sellerStatus(req,res,4);
				forwardPath ="sCancel.jsp";
				break;
				
			case "updateOrder":
				updateOrder(req,res);
				break;
			case "cancelOrder":
				cancelOrder(req,res);
				break;
				
			case "addOrder":
				addOrder(req,res);
				break;
			default:
				forwardPath = "index.jsp";
		}
		if(!forwardPath.isEmpty()) {
			res.setContentType("text/html; charset=UTF-8");
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
		
		
	}
	
	
	private void getBuyerOrderList(HttpServletRequest req, HttpServletResponse res) throws IOException{
		HttpSession session = req.getSession();
		Integer mbrId = (Integer)session.getAttribute("mbrId");
		
//		Integer status = Integer.parseInt(req.getParameter("status"));
		
		Map<ItemOrder,List<OrderDetails>> itemOrderMap = new LinkedHashMap<>();
		
		List<ItemOrder> itemOrderList = gs.getBy(ItemOrder.class, "buyMbrId", mbrId);				
		for( ItemOrder itemOrder : itemOrderList) {
			List<OrderDetails> orderDetailsList = gs.getBy(OrderDetails.class, "compositeKey.orderId", itemOrder.getOrderId());
			itemOrderMap.put(itemOrder, orderDetailsList);
		}
		req.setAttribute("itemOrderMap", itemOrderMap);
	}
	
	private void buyerStatus(HttpServletRequest req, HttpServletResponse res,Integer status) throws IOException{
		String buyerParam = req.getParameter("buyer");
		Integer buyerId = Integer.parseInt(buyerParam);
		
		Map<ItemOrder,List<OrderDetails>> itemOrderMap = new LinkedHashMap<>();
		
		QueryCondition qc = new QueryCondition();
		qc.toMap("and", "buyMbrId", "=", buyerId);
		qc.toMap("and", "orderStatus", "=", status);				
		List<ItemOrder> itemOrderList = gs.getByQueryConditions(ItemOrder.class, qc.getConditionList());
		
		for( ItemOrder itemOrder : itemOrderList) {
			List<OrderDetails> orderDetailsList = gs.getBy(OrderDetails.class, "compositeKey.orderId", itemOrder.getOrderId());
			itemOrderMap.put(itemOrder, orderDetailsList);
		}
		
		req.setAttribute("buyer",buyerId);
		req.setAttribute("itemOrderMap", itemOrderMap);
	}
	
	
	
	private void sellerAll(HttpServletRequest req, HttpServletResponse res) throws IOException{
//		String sellerParam = req.getParameter("seller");
//		Integer sellerId = Integer.parseInt(sellerParam);
//		
//		Map<ItemOrder,List<OrderDetails>> itemOrderMap = new LinkedHashMap<>();
//		
//		List<ItemOrder> itemOrderList = gs.getBy(ItemOrder.class, "sellMbrId", sellerId);				
//		for( ItemOrder itemOrder : itemOrderList) {
//			List<OrderDetails> orderDetailsList = gs.getBy(OrderDetails.class, "compositeKey.orderId", itemOrder.getOrderId());
//			itemOrderMap.put(itemOrder, orderDetailsList);
//		}
//		req.setAttribute("seller",sellerId);
//		req.setAttribute("itemOrderMap", itemOrderMap);
	}
	
	
	private void sellerStatus(HttpServletRequest req, HttpServletResponse res,Integer status) throws IOException{
		String seller = req.getParameter("seller");
		Integer sellerId = Integer.parseInt(seller);
		
		Map<ItemOrder,List<OrderDetails>> itemOrderMap = new LinkedHashMap<>();
		
		QueryCondition qc = new QueryCondition();
		qc.toMap("and", "sellMbrId", "=", sellerId);
		qc.toMap("and", "orderStatus", "=", status);				
		List<ItemOrder> itemOrderList = gs.getByQueryConditions(ItemOrder.class, qc.getConditionList());
		
		for( ItemOrder itemOrder : itemOrderList) {
			List<OrderDetails> orderDetailsList = gs.getBy(OrderDetails.class, "compositeKey.orderId", itemOrder.getOrderId());
			itemOrderMap.put(itemOrder, orderDetailsList);
		}
		
		req.setAttribute("seller",sellerId);
		req.setAttribute("itemOrderMap", itemOrderMap);
	}
	
	
	private void updateOrder(HttpServletRequest req, HttpServletResponse res) throws IOException{
		Integer orderId = Integer.parseInt(req.getParameter("orderId"));
		ItemOrder itemOrder = gs.getByPrimaryKey(ItemOrder.class,orderId );
		if(itemOrder.getOrderStatus() < 3 ) {
			itemOrder.setOrderStatus(itemOrder.getOrderStatus()+1);
		}
		gs.update(itemOrder);
	}
	
	private void cancelOrder(HttpServletRequest req, HttpServletResponse res) throws IOException{
		Integer orderId = Integer.parseInt(req.getParameter("orderId"));
		ItemOrder itemorder = gs.getByPrimaryKey(ItemOrder.class,orderId );
		itemorder.setOrderStatus(4);
		gs.update(itemorder);
	}
	
	
	
	
	
	
	
	private void addOrder(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// 設置響應的內容類型
	    res.setCharacterEncoding("UTF-8");
	    res.setContentType("text; charset=UTF-8");
	    
	    HttpSession session = req.getSession();
	    Integer buyerId = (Integer)session.getAttribute("mbrId");
	    
	    String itemIdStr = req.getParameter("itemId");
	    String quantityStr = req.getParameter("quantity");
	    String eachCountStr = req.getParameter("eachCount");
	            
        Integer payment = Integer.parseInt(req.getParameter("payment"));
        String receiveName = req.getParameter("receiveName");
        String receivePhone = req.getParameter("receivePhone");
        String receiveAddress = req.getParameter("receiveAddress");
        Integer mytotal = Integer.parseInt(req.getParameter("mytotal"));
        Integer count = Integer.parseInt(req.getParameter("count"));
        Integer mbrPoint = Integer.parseInt(req.getParameter("mbrPoint"));
        Integer cpnId = Integer.parseInt(req.getParameter("cpnId"));
        Integer totalPay = Integer.parseInt(req.getParameter("totalPay"));
        
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        List<ItemOrder> itemOrderList = new ArrayList<>();
        
        
        String[] itemParts = itemIdStr.split(",");
        String[] quantityParts = quantityStr.split(",");
        String[] eachCountParts = eachCountStr.split(",");
        
        for (int i = 0; i < itemParts.length; i++) {
        	// 商品編號 數量 折扣金額
        	Integer itemId = Integer.parseInt(itemParts[i].trim());
        	Integer quantity = Integer.parseInt(quantityParts[i].trim());
        	Integer eachCount = Integer.parseInt(eachCountParts[i].trim());
        	
        	// 訂單明細 複合之商品編號 
        	OrderDetails orderDetails = new OrderDetails();
        	OrderDetailsCompositeDetail orderDetailsCompositeDetail = new OrderDetailsCompositeDetail();
        	orderDetailsCompositeDetail.setItemId(itemId);
        	orderDetails.setCompositeKey(orderDetailsCompositeDetail);
        	// 訂單明細 明細金額 商品數量 折扣金額 明細總金額
        	Item item = gs.getByPrimaryKey(Item.class, itemId);
        	orderDetails.setPrice(item.getPrice());
        	orderDetails.setQuantity(quantity);
        	orderDetails.setDiscountPrice(eachCount);
        	orderDetails.setBuyingPrice((orderDetails.getPrice()*orderDetails.getQuantity())-orderDetails.getDiscountPrice());
        	orderDetailsList.add(orderDetails);
        	
        }
        
        Map<Integer, List<OrderDetails>> groupedByMbrId = orderDetailsList.stream()
                .collect(Collectors.groupingBy(orderDetail -> {
                    Item item = gs.getByPrimaryKey(Item.class,orderDetail.getCompositeKey().getItemId());
                    return item.getMbrId();
                }));
        
        groupedByMbrId.forEach((mbrId, detailsList) -> {
        	ItemOrder itemOrder = new ItemOrder();
        	itemOrder.setBuyMbrId(buyerId);
        	itemOrder.setSellMbrId(mbrId);
        	itemOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
        	itemOrder.setPayType(payment);
        	itemOrder.setReceiveAddress(receiveAddress);
        	itemOrder.setReceiveName(receiveName);
        	itemOrder.setReceivePhone(receivePhone);
        	if( payment.equals(2) ) {
        		itemOrder.setOrderStatus(1);
        	}else {
        		itemOrder.setOrderStatus(0);
        	}
        	
        	Integer totalPrice = detailsList.stream().mapToInt(orderDetail -> orderDetail.getPrice()*orderDetail.getQuantity()).sum();
        	Integer totalDiscountPrice = detailsList.stream().mapToInt(orderDetail -> orderDetail.getDiscountPrice()).sum();

            itemOrder.setAmount(totalPrice);
            itemOrder.setDiscount(totalDiscountPrice);
            itemOrder.setFinalAmount(totalPrice-totalDiscountPrice);
            
            Integer itemOrderId = (Integer) gs.insert(itemOrder);
            detailsList.forEach(orderDetail ->{
            	orderDetail.getCompositeKey().setOrderId(itemOrderId);
            	gs.insert(orderDetail);
            });
            
        });
        
        
        
        
        //===================== 以下代碼 由第四小組成員 卉溫 提供 =====================
        
        session = req.getSession();			//HttpSession 宣告拿掉 避免重複宣告 by jung
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
        itemService.addNotice(notice, buyerId);	//mbrId -> buyerId by jung
    	//購物車清空
    	//處理商品id存成陣列
        itemIdStr = req.getParameter("itemId"); //String 宣告拿掉 避免重複宣告 by jung
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
			quantityStr = jedis.hget(mbrIdStr, String.valueOf(Id)); //String 宣告拿掉 避免重複宣告 by jung
			
			Item item = itemService.getItemByItemId(Id);
			Integer newInventory = (item.getQuantity())-(Integer.valueOf(quantityStr));
			item.setQuantity(newInventory);
			//如果扣完後庫存為0即自動下架
			if(newInventory == 0) {
				item.setItemStatus(1);
			}
			Integer success =itemService.updateItem(item);
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
		mbrPoint = 0;				//Integer 宣告拿掉 避免重複宣告 by jung
    	if(!(mbrPointStr.trim().equals("0") && mbrPointStr!=null)) {
			mbrPoint = Integer.valueOf(mbrPointStr);
			//新增異動
			PointHistory pointHistory = new PointHistory();
			
			//取訂單編號
//			if(!req.getParameter("orderId").trim().isEmpty() ) {
//				int orderId =Integer.parseInt(req.getParameter("orderId"));
//			}
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
    	
    	Members mem=memSvc.getByPrimaryKey(mbrId);
    	
    	//買家若用虛擬錢包，扣錢包與新增異動//如果==2代表選擇虛擬錢包付款
        //Integer payment = Integer.valueOf(req.getParameter("payment"));//付款方式  	//Stream發現該變數有可能異動 故註解掉 值一樣是從Parameter取 並無更改過 by jung
        if(payment == 2) {
        	Integer newBalance = mem.getBalance()-totalPay;
        	mem.setBalance(newBalance);
        	memSvc.updateMembers(mem);
        	
			//新增異動
			BalanceHistory balanceHistory = new BalanceHistory();
			
			//取訂單編號
//			if(!req.getParameter("orderId").trim().isEmpty() ) {
//				int orderId =Integer.parseInt(req.getParameter("orderId"));
//			}
			Timestamp currentTime2 = new Timestamp(System.currentTimeMillis());
			
			balanceHistory.setMbrId(mbrId);
			balanceHistory.setOrderId(1);//從訂單取
			balanceHistory.setWrId(null);
			//異動時間1/訂單完成(+) 2/訂單確認(-)
			balanceHistory.setChangeDate(currentTime2);			
			balanceHistory.setChangeValue(totalPay*-1);
			
			int balanceHistoryPK = BHSvc.addBH(balanceHistory);            	
        }
        
        //使用後的優惠券改變狀態
        String cpnIdStr = req.getParameter("cpnId");
        cpnId = 0;				//Integer 宣告拿掉 避免重複宣告 by jung
        if(cpnIdStr!=null) {
        	cpnId = Integer.valueOf(cpnIdStr);
        }
		MembersCouponCompositeDetail memcoupon = new MembersCouponCompositeDetail();
		if(memcoupon!=null) {
			memcoupon.setCouponId(cpnId);
			memcoupon.setMemberId(mbrId);
			
		}
		
		MembersCoupon membersCoupon = (MembersCoupon)couponDAO.getByPrimaryKey(memcoupon);
		if(membersCoupon!=null) {
			Timestamp currentTime3 = new Timestamp(System.currentTimeMillis());
			membersCoupon.setCouponStatus(1);
			membersCoupon.setUseDate(currentTime3);
			boolean boo = couponDAO.update(membersCoupon);
			
		}
		
	    String responseData = "訂單成立！"; // 這裡是要回傳的訊息

	    PrintWriter out = res.getWriter();
	    out.print(responseData);
	    out.flush();
        
        
       
	}
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
