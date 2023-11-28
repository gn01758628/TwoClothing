package com.twoclothing.chijung.controller.front_end;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
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
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.twoclothing.chijung.Mapping;
import com.twoclothing.gordon.service.MembersService;
import com.twoclothing.gordon.service.MembersServiceImpl;
import com.twoclothing.huiwen.service.BalanceHistoryService;
import com.twoclothing.huiwen.service.BalanceHistoryServiceImpl;
import com.twoclothing.huiwen.service.ItemService;
import com.twoclothing.huiwen.service.ItemServiceImpl;
import com.twoclothing.huiwen.service.PointHistoryService;
import com.twoclothing.huiwen.service.PointHistoryServiceImpl;
import com.twoclothing.model.abid.bidorder.BidOrder;
import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.aproduct.itemorder.ItemOrder;
import com.twoclothing.model.aproduct.orderdetails.OrderDetails;
import com.twoclothing.model.aproduct.orderdetails.OrderDetails.OrderDetailsCompositeDetail;
import com.twoclothing.model.balancehistory.BalanceHistory;
import com.twoclothing.model.members.Members;
import com.twoclothing.model.memberscoupon.MembersCoupon;
import com.twoclothing.model.memberscoupon.MembersCoupon.MembersCouponCompositeDetail;
import com.twoclothing.model.pointhistory.PointHistory;
import com.twoclothing.model.shipsetting.ShipSetting;
import com.twoclothing.redismodel.notice.Notice;
import com.twoclothing.redismodel.notice.NoticeDAO;
import com.twoclothing.redismodel.notice.NoticeJedisDAO;
import com.twoclothing.utils.HibernateUtil;
import com.twoclothing.utils.JedisPoolUtil;
import com.twoclothing.utils.generic.DAOSelector;
import com.twoclothing.utils.generic.GenericDAO;
import com.twoclothing.utils.generic.GenericService;
import com.twoclothing.utils.generic.QueryCondition;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@WebServlet("/front_end/itemorder/ItemOrderServlet.check")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ItemOrderServlet extends HttpServlet{
	
	private GenericService gs;
	//==================================
	private ItemService itemService;
	
	private PointHistoryService PHSvc;
	
	private BalanceHistoryService BHSvc;
	
	private MembersService memSvc;
	
	private GenericDAO couponDAO;
	
	private NoticeDAO noticeDAO;
	
	
	
//	@Override
	public void init() throws ServletException {
		this.gs = gs.getInstance();
		//==================================
		itemService = new ItemServiceImpl();
		PHSvc = new PointHistoryServiceImpl();
		memSvc = new MembersServiceImpl();
		BHSvc = new BalanceHistoryServiceImpl();
		couponDAO = DAOSelector.getDAO(MembersCoupon.class);
		noticeDAO = new NoticeJedisDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		
		
		switch (action) {
			case "buyer":	
				buyer(req, res);
				forwardPath ="bAll.jsp";
				break;
			case "seller":
				seller(req, res);
				forwardPath ="sAll.jsp";
				break;
				
			case "updateOrder":
				forwardPath = updateOrder(req,res);
				break;
			case "cancelOrder":
				forwardPath = cancelOrder(req,res);
				break;
			//買家功能	
			case "addOrder":
				addOrder(req,res);
				break;
			case "turn_To_Pay":
				turnToPayAndAddress(req,res);
				forwardPath = "payAndAddress.jsp";
				break;
			case "pay_And_Address":
				payAndAddress(req,res);
				forwardPath = "/front_end/itemorder/ItemOrderServlet.check?action=buyer&status=1";
				break;
			case "turn_To_Details":
				turnToDetails(req,res);
				forwardPath = "/front_end/itemorder/bItemOrderDetails.jsp";
				break;
			case "turn_To_Assign_Rating":
				turnToAssignRating(req,res);
				forwardPath = "/front_end/itemorder/assignRating.jsp";
				break;
			case "assign_Rating":
				assignRatingn(req,res);
				break;
			default:
				forwardPath = "index.jsp";
		}
		if(!forwardPath.isEmpty()) {
			res.setContentType("text/html; charset=UTF-8");
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
		
		
//			case "buyer0":	
//				buyerStatus(req,res,0);
//				forwardPath ="bUnpaid.jsp";
//				break;
//			case "buyer1":	
//				buyerStatus(req,res,1);
//				forwardPath ="bUnshipped.jsp";
//				break;
//			case "buyer2":	
//				buyerStatus(req,res,2);
//				forwardPath ="bUnreceived.jsp";
//				break;
//			case "buyer3":	
//				buyerStatus(req,res,3);
//				forwardPath ="bFinished.jsp";
//				break;
//			case "buyer4":	
//				buyerStatus(req,res,4);
//				forwardPath ="bCancel.jsp";
//				break;
//			case "seller0":
//				sellerStatus(req,res,0);
//				forwardPath ="sUnpaid.jsp";
//				break;
//			case "seller1":
//				sellerStatus(req,res,1);
//				forwardPath ="sUnshipped.jsp";
//				break;
//			case "seller2":
//				sellerStatus(req,res,2);
//				forwardPath ="sUnreceived.jsp";
//				break;
//			case "seller3":
//				sellerStatus(req,res,3);
//				forwardPath ="sFinished.jsp";
//				break;
//			case "seller4":
//				sellerStatus(req,res,4);
//				forwardPath ="sCancel.jsp";
//				break;
		
	}
	
	
	private void buyer(HttpServletRequest req, HttpServletResponse res) throws IOException{
		HttpSession session = req.getSession();
		Integer mbrId = (Integer)session.getAttribute("mbrId");
		Integer status = (req.getParameter("status") == null) ? null :Integer.parseInt(req.getParameter("status"));
		
		Map<ItemOrder,List<OrderDetails>> itemOrderMap = new LinkedHashMap<>();
		Map<Integer,Item> itemMap = new LinkedHashMap<>();
		
		
		QueryCondition qc = new QueryCondition();
		qc.toMap("and", "buyMbrId", "=", mbrId);
		if( status != null ) {
			qc.toMap("and", "orderStatus", "=", status);							
		}
		List<ItemOrder> itemOrderList = gs.getByQueryConditions(ItemOrder.class, qc.getConditionList());
		
		
		itemOrderList = itemOrderList.stream()
                .sorted(Comparator.comparingInt(ItemOrder::getOrderId).reversed())
                .collect(Collectors.toList());	
		
		for( ItemOrder itemOrder : itemOrderList) {
			List<OrderDetails> orderDetailsList = gs.getBy(OrderDetails.class, "compositeKey.orderId", itemOrder.getOrderId());
			Integer itemId;
			for(OrderDetails orderDetails : orderDetailsList ) {
				itemId = orderDetails.getCompositeKey().getItemId();
				Item it =gs.getByPrimaryKey(Item.class, orderDetails.getCompositeKey().getItemId());
				itemMap.put(itemId, it);
			}
			itemOrderMap.put(itemOrder, orderDetailsList);
		}
		
		
		req.setAttribute("itemOrderMap", itemOrderMap);
		req.setAttribute("itemMap", itemMap);
		req.setAttribute("OrderStatusMap", Mapping.OrderStatusMap);
	}
	
	
	private void seller(HttpServletRequest req, HttpServletResponse res) throws IOException{
		HttpSession session = req.getSession();
		Integer mbrId = (Integer)session.getAttribute("mbrId");
		Integer status = (req.getParameter("status") == null) ? null :Integer.parseInt(req.getParameter("status"));
		
		Map<ItemOrder,List<OrderDetails>> itemOrderMap = new LinkedHashMap<>();
		Map<Integer,Item> itemMap = new LinkedHashMap<>();
		
		
		QueryCondition qc = new QueryCondition();
		qc.toMap("and", "sellMbrId", "=", mbrId);
		if( status != null ) {
			qc.toMap("and", "orderStatus", "=", status);							
		}
		List<ItemOrder> itemOrderList = gs.getByQueryConditions(ItemOrder.class, qc.getConditionList());
		
		
		itemOrderList = itemOrderList.stream()
				.sorted(Comparator.comparingInt(ItemOrder::getOrderId).reversed())
				.collect(Collectors.toList());	
		
		for( ItemOrder itemOrder : itemOrderList) {
			List<OrderDetails> orderDetailsList = gs.getBy(OrderDetails.class, "compositeKey.orderId", itemOrder.getOrderId());
			Integer itemId;
			for(OrderDetails orderDetails : orderDetailsList ) {
				itemId = orderDetails.getCompositeKey().getItemId();
				Item it =gs.getByPrimaryKey(Item.class, orderDetails.getCompositeKey().getItemId());
				itemMap.put(itemId, it);
			}
			itemOrderMap.put(itemOrder, orderDetailsList);
		}
		
		
		req.setAttribute("itemOrderMap", itemOrderMap);
		req.setAttribute("itemMap", itemMap);
		req.setAttribute("OrderStatusMap", Mapping.OrderStatusMap);
	}
	

	
	
	private String updateOrder(HttpServletRequest req, HttpServletResponse res) throws IOException{
		Integer orderId = Integer.parseInt(req.getParameter("orderId"));
		ItemOrder itemOrder = gs.getByPrimaryKey(ItemOrder.class,orderId );
		HttpSession session = req.getSession();
		Integer mbrId = (Integer) session.getAttribute("mbrId");

		
		Integer buyMbrId = itemOrder.getBuyMbrId();
		Notice toBuyerNotice = new Notice();	
		Integer sellMbrId = itemOrder.getSellMbrId();
		Notice toSellerNnotice = new Notice();
				
        
        switch(itemOrder.getOrderStatus()) {
        //未付款 對應動作 付款
        	case 0:
        		toBuyerNotice.setHead("付款成功通知");
        		toSellerNnotice.setHead("買家付款通知");
        		toBuyerNotice.setContent("感謝您的購買，已通知賣家出貨");
        		toSellerNnotice.setContent("買家已付款，請盡快出貨");
        		toBuyerNotice.setLink("/front_end/itemorder/ItemOrderServlet.check?action=buyer&status=1");
        		toSellerNnotice.setLink("/front_end/itemorder/ItemOrderServlet.check?action=seller&status=1");
        		break;
		//未出貨 對應動作 出貨
        	case 1:
        		toBuyerNotice.setHead("賣家出貨通知");
        		toSellerNnotice.setHead("出貨成功通知");
        		toBuyerNotice.setContent("感謝您的耐心等候，賣家已出貨");
        		toSellerNnotice.setContent("感謝您的協助，已通知買家出貨");
        		toBuyerNotice.setLink("/front_end/itemorder/ItemOrderServlet.check?action=buyer&status=2");
        		toSellerNnotice.setLink("/front_end/itemorder/ItemOrderServlet.check?action=seller&status=2");
        		break;
		//未收貨 對應動作 完成訂單
        	case 2:
        		toBuyerNotice.setHead("訂單完成通知");
        		toSellerNnotice.setHead("訂單完成通知");
        		toBuyerNotice.setContent("本次交易結束，請評價訂單");
        		toSellerNnotice.setContent("本次交易結束，請評價訂單");
        		toBuyerNotice.setLink("/front_end/itemorder/ItemOrderServlet.check?action=buyer&status=3");
        		toSellerNnotice.setLink("/front_end/itemorder/ItemOrderServlet.check?action=seller&status=3");
        		break;
        }
        if( itemOrder.getOrderStatus() < 3 ) {
        	toBuyerNotice.setType("訂單通知");
    		toSellerNnotice.setType("訂單通知");
    		toBuyerNotice.setImageLink("/images/cart/placeOrder.png");
    		toSellerNnotice.setImageLink("/images/cart/placeOrder.png");
    		
    		noticeDAO.insert(toSellerNnotice, sellMbrId);
    		noticeDAO.insert(toBuyerNotice, buyMbrId);
        }
        
		
		
		if(itemOrder.getOrderStatus() < 3 ) {
			itemOrder.setOrderStatus(itemOrder.getOrderStatus()+1);
		}
		gs.update(itemOrder);
		
		if(mbrId.equals(sellMbrId)) {
			return "/front_end/itemorder/ItemOrderServlet.check?action=seller&status="+itemOrder.getOrderStatus();
		}else {
			return "/front_end/itemorder/ItemOrderServlet.check?action=buyer&status="+itemOrder.getOrderStatus();
			
		}
	}
	
	private String cancelOrder(HttpServletRequest req, HttpServletResponse res) throws IOException{
		Integer orderId = Integer.parseInt(req.getParameter("orderId"));
		ItemOrder itemOrder = gs.getByPrimaryKey(ItemOrder.class,orderId );
		
		HttpSession session = req.getSession();
		Integer mbrId = (Integer) session.getAttribute("mbrId");
		
		if( itemOrder.getPayType().equals(2)){
			
			Timestamp currentTime2 = new Timestamp(System.currentTimeMillis());
			BalanceHistory balanceHistory = new BalanceHistory();
			balanceHistory.setMbrId(itemOrder.getBuyMbrId());
			balanceHistory.setOrderId(itemOrder.getOrderId());//從訂單取
			balanceHistory.setWrId(null);
			//異動時間1/訂單完成(+) 2/訂單確認(-)
			balanceHistory.setChangeDate(currentTime2);			
			balanceHistory.setChangeValue(itemOrder.getFinalAmount()*1);
			BHSvc.addBH(balanceHistory);   
			
			Members mem=memSvc.getByPrimaryKey(itemOrder.getBuyMbrId());

			Integer newPoint = mem.getMbrPoint()+itemOrder.getFinalAmount();
			mem.setMbrPoint(newPoint);
			memSvc.updateMembers(mem);
		}
		
		itemOrder.setOrderStatus(4);
		gs.update(itemOrder);
		
		Integer buyMbrId = itemOrder.getBuyMbrId();
		Notice toBuyerNotice = new Notice();	
		Integer sellMbrId = itemOrder.getSellMbrId();
		Notice toSellerNnotice = new Notice();
		toBuyerNotice.setType("訂單通知");
		toSellerNnotice.setType("訂單通知");
		toBuyerNotice.setHead("訂單已取消");
		toSellerNnotice.setHead("訂單已取消");
		toBuyerNotice.setContent("訂單已取消");
		toSellerNnotice.setContent("訂單已取消");
		toBuyerNotice.setLink("/front_end/itemorder/ItemOrderServlet.check?action=buyer&status=4");
		toSellerNnotice.setLink("/front_end/itemorder/ItemOrderServlet.check?action=seller&status=4");
		toBuyerNotice.setImageLink("/images/cart/placeOrder.png");
		toSellerNnotice.setImageLink("/images/cart/placeOrder.png");
		noticeDAO.insert(toSellerNnotice, sellMbrId);
		noticeDAO.insert(toBuyerNotice, buyMbrId);
		
		if(mbrId.equals(sellMbrId)) {
			return "/front_end/itemorder/ItemOrderServlet.check?action=seller&status="+itemOrder.getOrderStatus();
		}else {
			return "/front_end/itemorder/ItemOrderServlet.check?action=buyer&status="+itemOrder.getOrderStatus();
			
		}
		
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
        
        groupedByMbrId.forEach(( mbrId, detailsList) -> {
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
            
            Integer orderId = (Integer) gs.insert(itemOrder);
            itemOrderList.add(itemOrder);
            detailsList.forEach(orderDetail ->{
            	orderDetail.getCompositeKey().setOrderId(orderId);
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
//    	連到訂單頁面的link
        notice.setLink("/front_end/itemorder/ItemOrderServlet.check?action=buyer");
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
//			PointHistory pointHistory = new PointHistory();
			
			//取訂單編號
//			if(!req.getParameter("orderId").trim().isEmpty() ) {
//				int orderId =Integer.parseInt(req.getParameter("orderId"));
//			}
//			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			//==============================整合======================================
			System.out.println("===========pointHistorytmp===========");
			for( ItemOrder ito : itemOrderList) {
				int finalAmount = ito.getFinalAmount();
	        	double total = (double) totalPay;
	        	int result = (int) Math.round((double) mbrPoint * finalAmount / total);
	        	PointHistory pointHistorytmp = new PointHistory();
	        	pointHistorytmp.setMbrId(ito.getBuyMbrId());
	        	pointHistorytmp.setOrderId(ito.getOrderId());
	        	pointHistorytmp.setChangeDate(ito.getOrderDate());			
	        	pointHistorytmp.setChangeValue(result);
	        	PHSvc.addPH(pointHistorytmp);
	        	System.out.println(pointHistorytmp);
			}
			System.out.println("===========pointHistorytmp===========");
			
			
			
			//==============================整合======================================
			
			
//			pointHistory.setMbrId(mbrId);
//			pointHistory.setOrderId(1);//從訂單取
			//異動時間1/訂單完成(+) 2/訂單確認(-)
//			pointHistory.setChangeDate(currentTime);			
//			pointHistory.setChangeValue(mbrPoint*-1);
			
//			int pointHistoryPK = PHSvc.addPH(pointHistory);
			
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
//			BalanceHistory balanceHistory = new BalanceHistory();
			
			//取訂單編號
//			if(!req.getParameter("orderId").trim().isEmpty() ) {
//				int orderId =Integer.parseInt(req.getParameter("orderId"));
//			}
			
//			Timestamp currentTime2 = new Timestamp(System.currentTimeMillis());
			
			for( ItemOrder ito : itemOrderList) {
				BalanceHistory balanceHistory = new BalanceHistory();
				balanceHistory.setMbrId(ito.getBuyMbrId());
				balanceHistory.setOrderId(ito.getOrderId());//從訂單取
				balanceHistory.setWrId(null);
				//異動時間1/訂單完成(+) 2/訂單確認(-)
				balanceHistory.setChangeDate(ito.getOrderDate());			
				balanceHistory.setChangeValue(ito.getFinalAmount()*-1);
				BHSvc.addBH(balanceHistory);    
			}
			
			 
			
//			balanceHistory.setMbrId(mbrId);
//			balanceHistory.setOrderId(1);//從訂單取
//			balanceHistory.setWrId(null);
			//異動時間1/訂單完成(+) 2/訂單確認(-)
//			balanceHistory.setChangeDate(currentTime2);			
//			balanceHistory.setChangeValue(totalPay*-1);
			
//			int balanceHistoryPK = BHSvc.addBH(balanceHistory);            	
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
	
	private void turnToPayAndAddress(HttpServletRequest req, HttpServletResponse res) throws IOException{
		HttpSession session = req.getSession();
		Integer orderId = Integer.valueOf(req.getParameter("orderId"));
		req.setAttribute("itemOrder", gs.getByPrimaryKey(ItemOrder.class, orderId));
		req.setAttribute("orderdetailsList", gs.getBy( OrderDetails.class, "compositeKey.orderId", orderId ));
		List<ShipSetting> shipSettingList = gs.getBy(ShipSetting.class,"mbrId",(Integer)session.getAttribute("mbrId"));
		req.setAttribute("shipSettingList", shipSettingList);
	}
	
	private void payAndAddress(HttpServletRequest req, HttpServletResponse res) throws IOException{
		HttpSession session = req.getSession();
		Integer orderId = Integer.valueOf(req.getParameter("orderId"));
		
//		String cardPart1 = req.getParameter("cardPart1");
//      String cardPart2 = req.getParameter("cardPart2");
//      String cardPart3 = req.getParameter("cardPart3");
//      String cardPart4 = req.getParameter("cardPart4");
//      String expirationDate = req.getParameter("expirationDate");
//      String cvv = req.getParameter("cvv");
        String receiveName = req.getParameter("receiveName");
        String receivePhone = req.getParameter("receivePhone");
        
        String county = req.getParameter("county");
        String district = req.getParameter("district");
        String address = req.getParameter("address");
        String zipcode = req.getParameter("zipcode");
        String fullAddress = zipcode + county + district + address;
        
        String remarks = req.getParameter("remarks");
        //通知賣家
        String sellMbrId = req.getParameter("sellMbrId");
        
        ItemOrder itemOrder = gs.getByPrimaryKey(ItemOrder.class,orderId );
        itemOrder.setReceiveName(receiveName);
        itemOrder.setReceivePhone(receivePhone);
        itemOrder.setReceiveAddress(fullAddress);
        itemOrder.setRemarks(remarks);
//        itemOrder.setOrderStatus(1);    呼叫
        gs.update(itemOrder);
        updateOrder(req,res);
        
	}
	
	private void turnToDetails(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
	    Integer orderId = Integer.parseInt(req.getParameter("orderId"));
	    ItemOrder itemOrder = gs.getByPrimaryKey(ItemOrder.class,orderId );
	    List<OrderDetails> orderDetailsList = gs.getBy(OrderDetails.class, "compositeKey.orderId", itemOrder.getOrderId());
	    
		req.setAttribute("itemOrder", itemOrder);
		req.setAttribute("orderDetailsList", orderDetailsList);
		req.setAttribute("OrderStatusMap", Mapping.OrderStatusMap);
	}
	
	private void turnToAssignRating(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		Integer orderId = Integer.parseInt(req.getParameter("orderId"));
		ItemOrder itemOrder = gs.getByPrimaryKey(ItemOrder.class,orderId );
		List<OrderDetails> orderDetailsList = gs.getBy(OrderDetails.class, "compositeKey.orderId", itemOrder.getOrderId());
		
		req.setAttribute("itemOrder", itemOrder);
		req.setAttribute("orderDetailsList", orderDetailsList);
		req.setAttribute("OrderStatusMap", Mapping.OrderStatusMap);
	}
	
	private void assignRatingn(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		Integer orderId = Integer.parseInt(req.getParameter("orderId"));
		ItemOrder itemOrder = gs.getByPrimaryKey(ItemOrder.class,orderId );
		List<OrderDetails> orderDetailsList = gs.getBy(OrderDetails.class, "compositeKey.orderId", itemOrder.getOrderId());
		
		String content = req.getParameter("content");
		String sellerRatingString = req.getParameter("sellerRating");
		String buyerRatingString = req.getParameter("buyerRating");
		System.out.println(content);
		
		Integer sellerRating = null;
		Integer buyerRating = null;

		if (sellerRatingString != null && !sellerRatingString.isEmpty()) {
		    sellerRating = Integer.parseInt(sellerRatingString);
		    itemOrder.setSellStar(sellerRating);
		    itemOrder.setSellerRatingDesc(content);
		    System.out.println(sellerRating);
		}

		if (buyerRatingString != null && !buyerRatingString.isEmpty()) {
		    buyerRating = Integer.parseInt(buyerRatingString);
		    itemOrder.setBuyStar(buyerRating);
		    itemOrder.setBuyerRatingDesc(content);
		    System.out.println(buyerRating);
		}
    	//新增
    	gs.update(itemOrder);
    	
        // 處理新增操作的邏輯
    	res.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = res.getWriter();
	    out.write("OK");
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	
//	private void buyerStatus(HttpServletRequest req, HttpServletResponse res,Integer status) throws IOException{
//	String buyerParam = req.getParameter("buyer");
//	Integer buyerId = Integer.parseInt(buyerParam);
//	
//	Map<ItemOrder,List<OrderDetails>> itemOrderMap = new LinkedHashMap<>();
//	
//	QueryCondition qc = new QueryCondition();
//	qc.toMap("and", "buyMbrId", "=", buyerId);
//	qc.toMap("and", "orderStatus", "=", status);				
//	List<ItemOrder> itemOrderList = gs.getByQueryConditions(ItemOrder.class, qc.getConditionList());
//	
//	for( ItemOrder itemOrder : itemOrderList) {
//		List<OrderDetails> orderDetailsList = gs.getBy(OrderDetails.class, "compositeKey.orderId", itemOrder.getOrderId());
//		itemOrderMap.put(itemOrder, orderDetailsList);
//	}
//	
//	req.setAttribute("buyer",buyerId);
//	req.setAttribute("itemOrderMap", itemOrderMap);
//}
//
//
//private void seller(HttpServletRequest req, HttpServletResponse res) throws IOException{
//	String sellerParam = req.getParameter("seller");
//	Integer sellerId = Integer.parseInt(sellerParam);
//	
//	Map<ItemOrder,List<OrderDetails>> itemOrderMap = new LinkedHashMap<>();
//	
//	List<ItemOrder> itemOrderList = gs.getBy(ItemOrder.class, "sellMbrId", sellerId);				
//	for( ItemOrder itemOrder : itemOrderList) {
//		List<OrderDetails> orderDetailsList = gs.getBy(OrderDetails.class, "compositeKey.orderId", itemOrder.getOrderId());
//		itemOrderMap.put(itemOrder, orderDetailsList);
//	}
//	req.setAttribute("seller",sellerId);
//	req.setAttribute("itemOrderMap", itemOrderMap);
//}
//
//
//private void sellerStatus(HttpServletRequest req, HttpServletResponse res,Integer status) throws IOException{
//	String seller = req.getParameter("seller");
//	Integer sellerId = Integer.parseInt(seller);
//	
//	Map<ItemOrder,List<OrderDetails>> itemOrderMap = new LinkedHashMap<>();
//	
//	QueryCondition qc = new QueryCondition();
//	qc.toMap("and", "sellMbrId", "=", sellerId);
//	qc.toMap("and", "orderStatus", "=", status);				
//	List<ItemOrder> itemOrderList = gs.getByQueryConditions(ItemOrder.class, qc.getConditionList());
//	
//	for( ItemOrder itemOrder : itemOrderList) {
//		List<OrderDetails> orderDetailsList = gs.getBy(OrderDetails.class, "compositeKey.orderId", itemOrder.getOrderId());
//		itemOrderMap.put(itemOrder, orderDetailsList);
//	}
//	
//	req.setAttribute("seller",sellerId);
//	req.setAttribute("itemOrderMap", itemOrderMap);
//}
}
