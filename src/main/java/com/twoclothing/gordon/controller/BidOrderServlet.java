package com.twoclothing.gordon.controller;

import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.model.abid.bidorder.BidOrder;
import com.twoclothing.gordon.service.BidOrderServiceImpl;
import com.twoclothing.gordon.service.ShipSettingServiceImpl;
@WebServlet("/bidorder/BidOrder.do")
public class BidOrderServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		BidOrderServiceImpl bidOrderServiceImpl = new BidOrderServiceImpl();
		
		/***********************新增訂單*************************/
		/***********************新增訂單*************************/
		/***********************新增訂單*************************/
		/***********************新增訂單*************************/
		/***********************新增訂單*************************/

		if ("add_BidOrder".equals(action)) {
		    /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			Integer bidItemId =Integer.parseInt(req.getParameter("bidItemId")) ;
			Integer buyMbrId =Integer.parseInt(req.getParameter("buyMbrId")) ;
			Integer sellMbrId =Integer.parseInt(req.getParameter("sellMbrId")) ;
			Integer amount = Integer.parseInt(req.getParameter("amount")) ;
//			String dateStr = req.getParameter("orderDate");
			
			Date currentDate = new Date();
			Timestamp orderDate = new Timestamp(currentDate.getTime());

//			if (dateStr != null && !dateStr.isEmpty()) {
//			    try {
//			        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			        java.util.Date utilDate = dateFormat.parse(dateStr);
//			        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//			        orderDate = new Timestamp(sqlDate.getTime());
//			    } catch (ParseException e) {
//			        e.printStackTrace();
//			    }
//			}
			BidOrder bidOrder =new BidOrder(); 
			
			bidOrder.setBidItemId(bidItemId);
			bidOrder.setBuyMbrId(buyMbrId);
			bidOrder.setSellMbrId(sellMbrId);
			bidOrder.setAmount(amount);
			bidOrder.setOrderDate(orderDate);
			bidOrder.setOrderStatus(0);
	        /***************************2.開始新增資料***************************************/
			bidOrderServiceImpl.addBidOrder(bidOrder);
	        /***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("BidOrder", bidOrder);
			
			String bidOrderAction = "addBidOrder_so_AddNotify";
			req.setAttribute("BidOrderAction", bidOrderAction);
			
			String url = "//bidordernotify/BidOrderNotify.do";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		
			
			
			
			//==========================以後這裡跳轉發通知
//==========================以後這裡跳轉發通知
//==========================以後這裡跳轉發通知
//==========================以後這裡跳轉發通知
		}
			/***************************後台pk查詢 **********************/
			/***************************pk查詢 **********************/
			/***************************pk查詢 **********************/
			/***************************pk查詢 **********************/
			/***************************pk查詢 **********************/

			if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

				Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
				req.setAttribute("errorMsgs", errorMsgs);

				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String str = req.getParameter("bidOrderId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("bidOrderId", "請輸入訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/bidorder/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
			
				Integer bidOrderId = null;
				try {
					bidOrderId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("bidOrderId", "訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/bidorder/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/

				BidOrder bidOrder = bidOrderServiceImpl.getByPrimaryKey(bidOrderId);
				
				if (bidOrder == null) {
					errorMsgs.put("bidOrderId", "查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/bidorder/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("BidOrder", bidOrder);
				String url = "/back_end/bidorder/listOneBidOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
	
			}
			
			/*************************** 後台update ****************************************/
			/*************************** update ****************************************/
			/*************************** update ****************************************/
			/*************************** update ****************************************/
			/*************************** update ****************************************/

			if ("getOne_For_Update".equals(action)) {
				Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
				req.setAttribute("errorMsgs", errorMsgs);

				/*************************** 1.接收請求參數 ****************************************/

				Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));

				/*************************** 2.開始查詢資料 ****************************************/

				BidOrder bidOrder = bidOrderServiceImpl.getByPrimaryKey(bidOrderId);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String param = "?bidOrderId=" + bidOrder.getBidOrderId() + 
						"&bidItemId=" + bidOrder.getBidItemId() +
						"&buyMbrId="+ bidOrder.getBuyMbrId() + 
						"&sellMbrId=" + bidOrder.getSellMbrId() + 
						"&buyStar=" + bidOrder.getBuyStar()	+ 
						"&buyerRatingDesc=" + bidOrder.getBuyerRatingDesc() + 
						"&sellStar=" + bidOrder.getSellStar() + 
						"&sellerRatingDesc=" + bidOrder.getSellerRatingDesc() + 
						"&orderDate=" + bidOrder.getOrderDate() + 
						"&payType=" + bidOrder.getPayType() + 
						"&payInfo=" + bidOrder.getPayInfo() + 
						"&amount=" + bidOrder.getAmount() + 
						"&orderStatus=" + bidOrder.getOrderStatus() + 
						"&receiveAddress=" + bidOrder.getReceiveAddress() + 
						"&receiveName=" + bidOrder.getReceiveName() + 
						"&receivePhone=" + bidOrder.getReceivePhone() + 
						"&remarks=" + bidOrder.getRemarks();
				String url = "/back_end/bidorder/update_BidOrder_input.jsp"+ param;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
			}
			/*************************** 後台update ****************************************/
			/*************************** update ****************************************/
			/*************************** update ****************************************/
			/*************************** update ****************************************/
			/*************************** update ****************************************/

			
			if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
				
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				
				Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));
//////////////////////////////TEST
//				Integer bidItemId = null;
//				String bidItemIdStr = req.getParameter("bidItemId");
//				if (bidItemIdStr != null && !bidItemIdStr.isEmpty()) {
//				    bidItemId = Integer.parseInt(bidItemIdStr);
//				}
//				Integer buyMbrId = null;
//				String buyMbrIdStr = req.getParameter("buyMbrId");
//				if (buyMbrIdStr != null && !buyMbrIdStr.isEmpty()) {
//					buyMbrId = Integer.parseInt(buyMbrIdStr);
//				}
//				Integer sellMbrId = null;
//				String sellMbrIdStr = req.getParameter("sellMbrId");
//				if (sellMbrIdStr != null && !sellMbrIdStr.isEmpty()) {
//					sellMbrId = Integer.parseInt(sellMbrIdStr);
//				}
//				Integer amount = null;
//				String amountStr = req.getParameter("amount");
//				if (amountStr != null && !amountStr.isEmpty()) {
//					amount = Integer.parseInt(amountStr);
//				}
//				Timestamp orderDate = null;
//				
//				
//				String dateStr = req.getParameter("orderDate");
//
//				if (dateStr != null && !dateStr.isEmpty()) {
//				    try {
//				        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//				        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, formatter);
//				         orderDate = Timestamp.valueOf(localDateTime);
//				    } catch (DateTimeParseException e) {
//				        e.printStackTrace();
//				    }
//				}
////////////////////////////TEST
				
				
				
				
				Integer buyStar  = null;
				try { 
					buyStar = Integer.valueOf(req.getParameter("buyStar").trim());
				}catch (NumberFormatException e) {
					errorMsgs.put("buyStar","請填數字");
				}
				System.out.println(buyStar);
				
				String buyerRatingDesc = req.getParameter("buyerRatingDesc");
				if (buyerRatingDesc != null) {
					buyerRatingDesc = buyerRatingDesc.trim();
				} 
				System.out.println(buyerRatingDesc);

				
				Integer sellStar  = null;
				try { 
					sellStar = Integer.valueOf(req.getParameter("sellStar").trim());
				}catch (NumberFormatException e) {
					errorMsgs.put("sellstar","請填數字");
				}
				System.out.println(sellStar);

				String sellerRatingDesc = req.getParameter("sellerRatingDesc");
				if (sellerRatingDesc != null) {
					sellerRatingDesc = sellerRatingDesc.trim();
				} 
				System.out.println(sellerRatingDesc);

				Integer payType  = null;
				try { 
					payType = Integer.valueOf(req.getParameter("payType").trim());
				}catch (NumberFormatException e) {
					errorMsgs.put("payType","付款方式請選擇");
				}
				System.out.println(payType);

				String payInfo  = null;
				try { 
					payInfo = req.getParameter("payInfo").trim();
				}catch (NumberFormatException e) {
					errorMsgs.put("payInfo","請勿空白");
				}
				System.out.println(payInfo);

				Integer orderStatus  = null;
				try { 
					orderStatus = Integer.valueOf(req.getParameter("orderStatus").trim());
				}catch (NumberFormatException e) {
					errorMsgs.put("orderStatus","付款方式請選擇");
				}
				System.out.println(orderStatus);

				String receiveAddress  = null;
				try { 
					receiveAddress = req.getParameter("receiveAddress").trim();
				}catch (NumberFormatException e) {
					errorMsgs.put("receiveAddress","請勿空白");
				}
				System.out.println(receiveAddress);

				String receiveName  = null;
				try { 
					receiveName = req.getParameter("receiveName").trim();
				}catch (NumberFormatException e) {
					errorMsgs.put("receiveName","請勿空白");
				}
				System.out.println(receiveName);

				String receivePhone  = null;
				try { 
					receivePhone = req.getParameter("receivePhone").trim();
				}catch (NumberFormatException e) {
					errorMsgs.put("receivePhone","請勿空白");
				}
				System.out.println(receivePhone);

				String remarks = req.getParameter("remarks");
				if (remarks != null) {
				    remarks = remarks.trim();
				} 
				System.out.println(remarks);

				
		if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/bidorder/update_BidOrder_input.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
		BidOrder bidOrder1 =new BidOrder();
//////////////////////////////TEST		
//		bidOrder1.setBidItemId(bidItemId);
//		bidOrder1.setBuyMbrId(buyMbrId);
//		bidOrder1.setSellMbrId(sellMbrId);
//		bidOrder1.setAmount(amount);
//		bidOrder1.setOrderDate(orderDate);
//		
//		
//////////////////////////////TEST		
		
				
				bidOrder1.setBidOrderId(bidOrderId);
				bidOrder1.setBuyStar(buyStar);
				bidOrder1.setBuyerRatingDesc(buyerRatingDesc);
				bidOrder1.setSellStar(sellStar);
				bidOrder1.setSellerRatingDesc(sellerRatingDesc);
				bidOrder1.setPayType(payType);
				bidOrder1.setPayInfo(payInfo);
				bidOrder1.setOrderStatus(orderStatus);
				bidOrder1.setReceiveAddress(receiveAddress);
				bidOrder1.setReceiveName(receiveName);
				bidOrder1.setReceivePhone(receivePhone);
				bidOrder1.setRemarks(remarks);
				
				
				/***************************2.開始修改資料*****************************************/
				BidOrder bidOrder = bidOrderServiceImpl.updateAll(bidOrder1);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("BidOrder", bidOrder); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back_end/bidorder/listOneBidOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
			
			}
			
			/***********************刪除*************************/
			/***********************刪除*************************/
			/***********************刪除*************************/
			/***********************刪除*************************/
			/***********************刪除*************************/
			if ("delete".equals(action)) {
				
				/***************************1.接收請求參數***************************************/
				
				Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));

				
			    /***************************2.開始刪除資料***************************************/
				
				bidOrderServiceImpl.deleteBidOrder(bidOrderId);
				

			    /***************************3.刪除完成,準備轉交(Send the Success view)***********/
	//原來的		    String url = "/MemberCentre.jsp";
	//ok		    String url = "/shipsetting/Shipsetting.do?action=getAll_For_MbrId&mbrId="+mbrId;
	//ok		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
	//ok			successView.forward(req, res);
				
//			    String url1 = "/bidorder/BidOrder.do?action=getAll_For_MbrId&bidOrderId="+bidOrderId;
				
//			    String url1 = "/back_end/bidorder/listAllBidOrder.jsp";
//
//				res.sendRedirect(url1);
				
				String url = "/back_end/bidorder/listAllBidOrder.jsp";
				
			
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
			
			}
			
			/***********************查買家競標訂單*************************/
			/***********************查買家競標訂單*************************/
			/***********************查買家競標訂單*************************/
			/***********************查買家競標訂單*************************/
			/***********************查買家競標訂單*************************/
			
			if ("buyBidOrder".equals(action)) { 
				/***************************1.接收請求參數***************************************/

				Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				
				List<BidOrder> bidOrder = bidOrderServiceImpl.getAllBuyMbrId(buyMbrId);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("BidOrder", bidOrder);
				String url = "/front_end/bidorder/buyBidorder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
	
			}
			
			/***********************查買家競標待付款*************************/
			/***********************查買家競標待付款*************************/
			/***********************查買家競標待付款*************************/
			/***********************查買家競標待付款*************************/
			/***********************查買家競標待付款*************************/
			if ("confirmPayment".equals(action)) { 
				/***************************1.接收請求參數***************************************/
				
				Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
				Integer OrderStatus = 0; 
				/*************************** 2.開始查詢資料 ****************************************/
				
				List<BidOrder> bidOrderList = bidOrderServiceImpl.getAllBuyMbrId(buyMbrId);
//				BidOrder firstBidOrder = bidOrder.get(0);
//				firstBidOrder.getOrderStatus();
//				for (BidOrder bidOrder : bidOrderList) {
//					bidOrder.getOrderStatus();
//				}
				List<BidOrder> bidOrder = bidOrderServiceImpl.getAllOrderStatusAndBuyer(OrderStatus, buyMbrId);
				

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("BidOrder", bidOrder);
				String url = "/front_end/bidorder/confirmPayment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
			}
			
			/***********************查賣家競標訂單*************************/
			/***********************查賣家競標訂單*************************/
			/***********************查賣家競標訂單*************************/
			/***********************查賣家競標訂單*************************/
			/***********************查賣家競標訂單*************************/

			if ("sellBidOrder".equals(action)) { 
				/***************************1.接收請求參數***************************************/
				
				Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				
				List<BidOrder> bidOrder = bidOrderServiceImpl.getAllSellMbrId(sellMbrId);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("BidOrder", bidOrder);
				String url = "/front_end/bidorder/sellBidorder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
			}
			
			
			
			
	}


}
