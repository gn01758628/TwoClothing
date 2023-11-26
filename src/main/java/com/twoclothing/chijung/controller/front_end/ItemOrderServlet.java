package com.twoclothing.chijung.controller.front_end;

import java.io.BufferedReader;
import java.io.IOException;
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
import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.aproduct.itemorder.ItemOrder;
import com.twoclothing.model.aproduct.orderdetails.OrderDetails;
import com.twoclothing.model.aproduct.orderdetails.OrderDetails.OrderDetailsCompositeDetail;
import com.twoclothing.model.members.Members;
import com.twoclothing.utils.generic.GenericService;
import com.twoclothing.utils.generic.QueryCondition;

@WebServlet("/front_end/itemorder/itemorder.check")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ItemOrderServlet extends HttpServlet{
	
	
	
	private GenericService gs;

//	@Override
	public void init() throws ServletException {
		this.gs = gs.getInstance();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		
		switch (action) {
			case "getMembersItems":
				List<Members> memberList = gs.getAll(Members.class);
				List<Item> itemList = gs.getAll(Item.class);
				
				req.setAttribute("memberList", memberList);
				req.setAttribute("itemList", itemList);
				forwardPath = "/front_end/itemorder/index.jsp";
				break;
			case "buyer":	
				buyerAll(req, res);
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
	
	
	private void buyerAll(HttpServletRequest req, HttpServletResponse res) throws IOException{
//		String buyerParam = req.getParameter("buyer");
//		Integer buyerId = Integer.parseInt(buyerParam);
//		
//		Map<ItemOrder,List<OrderDetails>> itemOrderMap = new LinkedHashMap<>();
//		
//		List<ItemOrder> itemOrderList = gs.getBy(ItemOrder.class, "buyMbrId", buyerId);				
//		for( ItemOrder io : itemOrderList) {
//			List<OrderDetails> orderDetailsList = gs.getBy(OrderDetails.class, "compositeKey.orderId", io.getOrderId());
//			itemOrderMap.put(io, orderDetailsList);
//		}
//		req.setAttribute("buyer",buyerId);
//		req.setAttribute("itemOrderMap", itemOrderMap);
	}
	
	private void buyerStatus(HttpServletRequest req, HttpServletResponse res,Integer status) throws IOException{
		String buyerParam = req.getParameter("buyer");
		Integer buyerId = Integer.parseInt(buyerParam);
		
		Map<ItemOrder,List<OrderDetails>> itemOrderMap = new LinkedHashMap<>();
		
		QueryCondition qc = new QueryCondition();
		qc.toMap("and", "buyMbrId", "=", buyerId);
		qc.toMap("and", "orderStatus", "=", status);				
		List<ItemOrder> itemOrderList = gs.getByQueryConditions(ItemOrder.class, qc.getConditionList());
		
		for( ItemOrder io : itemOrderList) {
			List<OrderDetails> orderDetailsList = gs.getBy(OrderDetails.class, "compositeKey.orderId", io.getOrderId());
			itemOrderMap.put(io, orderDetailsList);
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
//		for( ItemOrder io : itemOrderList) {
//			List<OrderDetails> orderDetailsList = gs.getBy(OrderDetails.class, "compositeKey.orderId", io.getOrderId());
//			itemOrderMap.put(io, orderDetailsList);
//		}
//		req.setAttribute("seller",sellerId);
//		req.setAttribute("itemOrderMap", itemOrderMap);
	}
	
	
	private void sellerStatus(HttpServletRequest req, HttpServletResponse res,Integer status) throws IOException{
		String sellerParam = req.getParameter("seller");
		Integer sellerId = Integer.parseInt(sellerParam);
		
		Map<ItemOrder,List<OrderDetails>> itemOrderMap = new LinkedHashMap<>();
		
		QueryCondition qc = new QueryCondition();
		qc.toMap("and", "sellMbrId", "=", sellerId);
		qc.toMap("and", "orderStatus", "=", status);				
		List<ItemOrder> itemOrderList = gs.getByQueryConditions(ItemOrder.class, qc.getConditionList());
		
		for( ItemOrder io : itemOrderList) {
			List<OrderDetails> orderDetailsList = gs.getBy(OrderDetails.class, "compositeKey.orderId", io.getOrderId());
			itemOrderMap.put(io, orderDetailsList);
		}
		
		req.setAttribute("seller",sellerId);
		req.setAttribute("itemOrderMap", itemOrderMap);
	}
	
	
	private void updateOrder(HttpServletRequest req, HttpServletResponse res) throws IOException{
		Integer orderId = Integer.parseInt(req.getParameter("orderId"));
		ItemOrder io = gs.getByPrimaryKey(ItemOrder.class,orderId );
		if(io.getOrderStatus() < 3 ) {
			io.setOrderStatus(io.getOrderStatus()+1);
		}
		gs.update(io);
	}
	
	private void cancelOrder(HttpServletRequest req, HttpServletResponse res) throws IOException{
		Integer orderId = Integer.parseInt(req.getParameter("orderId"));
		ItemOrder io = gs.getByPrimaryKey(ItemOrder.class,orderId );
		io.setOrderStatus(4);
		gs.update(io);
	}
	
	
	
	
	
	
	
	private void addOrder(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// 設置響應的內容類型
	    res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
	    
	    HttpSession session = req.getSession();
	    Integer buyerId = (Integer)session.getAttribute("mbrId");
	    
	    String itemIdStr = req.getParameter("itemId");
        String quantityStr = req.getParameter("quantity");
        
        Integer payment = Integer.parseInt(req.getParameter("payment"));
        String receiveName = req.getParameter("receiveName");
        String receivePhone = req.getParameter("receivePhone");
        String receiveAddress = req.getParameter("receiveAddress");
        Integer mytotal = Integer.parseInt(req.getParameter("mytotal"));
        Integer count = Integer.parseInt(req.getParameter("count"));
        
        String eachCountStr = req.getParameter("eachCount");
        
        Integer mbrPoint = Integer.parseInt(req.getParameter("mbrPoint"));
        Integer cpnId = Integer.parseInt(req.getParameter("cpnId"));
        Integer totalPay = Integer.parseInt(req.getParameter("totalPay"));
        
        System.out.println("Buyer ID: " + buyerId);
        System.out.println("Item ID String: " + itemIdStr);
        System.out.println("Quantity String: " + quantityStr);
        System.out.println("Payment: " + payment);
        System.out.println("Receive Name: " + receiveName);
        System.out.println("Receive Phone: " + receivePhone);
        System.out.println("Receive Address: " + receiveAddress);
        System.out.println("My Total: " + mytotal);
        System.out.println("Count: " + count);
        System.out.println("Each Count String: " + eachCountStr);
        System.out.println("Member Point: " + mbrPoint);
        System.out.println("Coupon ID: " + cpnId);
        System.out.println("Total Pay: " + totalPay);


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
        	System.out.println(item);
        	orderDetails.setPrice(item.getPrice());
        	orderDetails.setQuantity(quantity);
        	orderDetails.setDiscountPrice(eachCount);
        	orderDetails.setBuyingPrice((orderDetails.getPrice()*orderDetails.getQuantity())-orderDetails.getDiscountPrice());
        	orderDetailsList.add(orderDetails);
        	
        }
        System.out.println("=====================================");
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
            detailsList.forEach(System.out::println);
            System.out.println(itemOrder);
            
            System.out.println("+++++++++++++++++++++++++++++++++++++");
            
        });
        
        
        
        
        
        
        

//        for (String item : parts) {
//        	Integer itemId = Integer.parseInt(item);
//        	OrderDetails orderDetails = new OrderDetails();
//        	OrderDetailsCompositeDetail orderDetailsCompositeDetail = new OrderDetailsCompositeDetail();
//        	orderDetailsCompositeDetail.setItemId(itemId);
//        	orderDetails.setCompositeKey(orderDetailsCompositeDetail);
//        	Item tmp = gs.getByPrimaryKey(Item.class, Integer.parseInt(item));
//        	//暫存 數量到了取出來更新
//        	orderDetails.setPrice(tmp.getPrice());
//        	itemList.add(tmp);
//            System.out.println(tmp);
//        }
//        for (String item : parts) {
//        	Integer tmp = Integer.parseInt(item);
//        	quantityList.add(tmp);
//            System.out.println(tmp);
//        }
//        for (String item : parts) {
//        	Integer tmp = Integer.parseInt(item);
//        	eachCountList.add(tmp);
//            System.out.println(tmp);
//        }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

//	    // 獲取POST過來的JSON數據
//	    BufferedReader reader = req.getReader();
//	    StringBuilder jsonRequest = new StringBuilder();
//	    String line;
//	    while ((line = reader.readLine()) != null) {
//	        jsonRequest.append(line);
//	    }
//
//	    // 將JSON數據轉換為Java對象
//	    Gson gson = new Gson();
//	    JsonObject jsonObject = gson.fromJson(jsonRequest.toString(), JsonObject.class);
//		
//	    Map<Integer,List<OrderDetails>> orderdetails = new HashMap<>();
//	    
//	    OrderDetails od;
//	    OrderDetailsCompositeDetail odc;
//	    
//	    // 印出JsonObject中的數據
////	    System.out.println("Received JSON Data: " + jsonObject.toString());
//
//	    // 獲取JsonObject中的特定屬性
//	    Integer buyerId = jsonObject.get("buyerId").getAsInt(); // 將"propertyName"替換為你想要獲取的屬性名稱
////	    System.out.println("Value of specific property: " + buyerId);
//	    JsonArray cartData = jsonObject.getAsJsonArray("cartData");
////	    System.out.println("Value of specific property: " + cartData);
//	    //這段之後由購物車處理  到時購物車會直接傳使用完優惠券以及點數折扣的商品明細相關資料過來   屆時我接收處理完的訂單明細 生成對應訂單及明細即可
//	    //以下為測試資料生成
//	    for (JsonElement detail : cartData) {
//	    	List<OrderDetails> detailList = new ArrayList<>();
//	        JsonObject cartItem = detail.getAsJsonObject();
//	        Integer sellerId = cartItem.get("sellerId").getAsInt();
//	        Integer itemId = cartItem.get("itemId").getAsInt();
//	        Integer amount = cartItem.get("amount").getAsInt();
//	        
//	        //訂單明細設定
//	        od = new OrderDetails();
//	        odc = new OrderDetailsCompositeDetail();
//	        od.setCompositeKey(odc);
//	        od.getCompositeKey().setItemId(itemId);
//	        Item it = gs.getByPrimaryKey(Item.class, itemId);
//	        Integer itprice = it.getPrice();
//	        od.setPrice(itprice);
//	        od.setQuantity(amount);
//	        od.setDiscountPrice(0);
//	        od.setBuyingPrice(od.getPrice()*od.getQuantity()-od.getDiscountPrice());
//	        
//	        if(orderdetails.containsKey(sellerId)) {
//	        	detailList = orderdetails.get(sellerId);
//	        }
//	        
//	        detailList.add(od);
//	        orderdetails.put(sellerId, detailList);
//	        
//	       
//
//	    }
//	    //之後補上 +商品小計  +運費價 -點數折抵 -優惠券折抵
//	    for (Map.Entry<Integer, List<OrderDetails>> entry : orderdetails.entrySet()) {
//	        Integer sellerId = entry.getKey();
//	        List<OrderDetails> orderDetailList = entry.getValue();
//	        ItemOrder itOrder = new ItemOrder();
////	        System.out.println("Seller ID: " + sellerId);
//	        Integer oriPrice =  0;
//	        Integer orderPrice =  0;
//	        
//	        for (OrderDetails orderDetail : orderDetailList) {
//	        	oriPrice += (orderDetail.getPrice()*orderDetail.getQuantity());
//	        	orderPrice += orderDetail.getBuyingPrice();
//	        }
//	        itOrder.setBuyMbrId(buyerId);
//	        itOrder.setSellMbrId(sellerId);
//	        itOrder.setAmount(oriPrice);
//	        itOrder.setFinalAmount(orderPrice);
//	        itOrder.setOrderStatus(0);
//	        itOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
//	        Integer PK = (Integer) gs.insert(itOrder);
//	        for (OrderDetails orderDetail : orderDetailList) {
//	        	orderDetail.getCompositeKey().setOrderId(PK);
//	        	gs.insert(orderDetail);
//	        }
//	    }
//	    
//	    // 建立一個JSON物件，表示成功的回應
//        JsonObject jsonResponse = new JsonObject();
//        jsonResponse.addProperty("status", "success");
//        jsonResponse.addProperty("message", "訂單新增成功");
//        res.getWriter().write(jsonResponse.toString());
	}
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
