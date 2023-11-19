package com.twoclothing.gordon.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.twoclothing.chenghan.service.BidItemServiceImpl;
import com.twoclothing.gordon.service.BidOrderRatingImageServiceImpl;
import com.twoclothing.gordon.service.BidOrderServiceImpl;
import com.twoclothing.gordon.service.MembersServiceImpl;
import com.twoclothing.gordon.service.ShipSettingServiceImpl;
import com.twoclothing.huiwen.service.BalanceHistoryServiceImpl;
import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.bidorder.BidOrder;
import com.twoclothing.model.balancehistory.BalanceHistory;
import com.twoclothing.model.members.Members;
import com.twoclothing.model.shipsetting.ShipSetting;
@MultipartConfig
@WebServlet("/bidorder/BidOrder.do")
public class BidOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		BidOrderRatingImageServiceImpl bidOrderRatingImageServiceImpl = new BidOrderRatingImageServiceImpl();
		BidOrderServiceImpl bidOrderServiceImpl = new BidOrderServiceImpl();
		MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
		ShipSettingServiceImpl shipSettingServiceImpl = new ShipSettingServiceImpl();
		BalanceHistoryServiceImpl balanceHistoryServiceImpl = new BalanceHistoryServiceImpl();
		BidItemServiceImpl bidItemServiceImpl = new BidItemServiceImpl();

		/*********************** 新增訂單 *************************/
		/*********************** 新增訂單 *************************/
		/*********************** 新增訂單 *************************/
		/*********************** 新增訂單 *************************/
		/*********************** 新增訂單 *************************/

		if ("add_BidOrder".equals(action)) {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer bidItemId = Integer.parseInt(req.getParameter("bidItemId"));
			Integer buyMbrId = Integer.parseInt(req.getParameter("buyMbrId"));
			Integer sellMbrId = Integer.parseInt(req.getParameter("sellMbrId"));
			Integer amount = Integer.parseInt(req.getParameter("amount"));
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
			BidOrder bidOrder = new BidOrder();

			bidOrder.setBidItemId(bidItemId);
			bidOrder.setBuyMbrId(buyMbrId);
			bidOrder.setSellMbrId(sellMbrId);
			bidOrder.setAmount(amount);
			bidOrder.setOrderDate(orderDate);
			bidOrder.setOrderStatus(0);
			/*************************** 2.開始新增資料 ***************************************/
			bidOrderServiceImpl.addBidOrder(bidOrder);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("BidOrder", bidOrder);

//	

			String url = "/bidordernotify/BidOrderNotify.do?action=addBidOrder_so_AddNotify";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

			// ==========================以後這裡跳轉發通知
//==========================以後這裡跳轉發通知
//==========================以後這裡跳轉發通知
//==========================以後這裡跳轉發通知
		}
		/*************************** 後台pk查詢 **********************/
		/*************************** pk查詢 **********************/
		/*************************** pk查詢 **********************/
		/*************************** pk查詢 **********************/
		/*************************** pk查詢 **********************/

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
			String param = "?bidOrderId=" + bidOrder.getBidOrderId() + "&bidItemId=" + bidOrder.getBidItemId()
					+ "&buyMbrId=" + bidOrder.getBuyMbrId() + "&sellMbrId=" + bidOrder.getSellMbrId() + "&buyStar="
					+ bidOrder.getBuyStar() + "&buyerRatingDesc=" + bidOrder.getBuyerRatingDesc() + "&sellStar="
					+ bidOrder.getSellStar() + "&sellerRatingDesc=" + bidOrder.getSellerRatingDesc() + "&orderDate="
					+ bidOrder.getOrderDate() + "&payType=" + bidOrder.getPayType() + "&payInfo="
					+ bidOrder.getPayInfo() + "&amount=" + bidOrder.getAmount() + "&orderStatus="
					+ bidOrder.getOrderStatus() + "&receiveAddress=" + bidOrder.getReceiveAddress() + "&receiveName="
					+ bidOrder.getReceiveName() + "&receivePhone=" + bidOrder.getReceivePhone() + "&remarks="
					+ bidOrder.getRemarks();
			String url = "/back_end/bidorder/update_BidOrder_input.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

		}
		/*************************** 後台update ****************************************/
		/*************************** update ****************************************/
		/*************************** update ****************************************/
		/*************************** update ****************************************/
		/*************************** update ****************************************/

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
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

			Integer buyStar = null;
			try {
				buyStar = Integer.valueOf(req.getParameter("buyStar").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("buyStar", "請填數字");
			}
			System.out.println(buyStar);

			String buyerRatingDesc = req.getParameter("buyerRatingDesc");
			if (buyerRatingDesc != null) {
				buyerRatingDesc = buyerRatingDesc.trim();
			}
			System.out.println(buyerRatingDesc);

			Integer sellStar = null;
			try {
				sellStar = Integer.valueOf(req.getParameter("sellStar").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("sellstar", "請填數字");
			}
			System.out.println(sellStar);

			String sellerRatingDesc = req.getParameter("sellerRatingDesc");
			if (sellerRatingDesc != null) {
				sellerRatingDesc = sellerRatingDesc.trim();
			}
			System.out.println(sellerRatingDesc);

			Integer payType = null;
			try {
				payType = Integer.valueOf(req.getParameter("payType").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("payType", "付款方式請選擇");
			}
			System.out.println(payType);

			String payInfo = null;
			try {
				payInfo = req.getParameter("payInfo").trim();
			} catch (NumberFormatException e) {
				errorMsgs.put("payInfo", "請勿空白");
			}
			System.out.println(payInfo);

			Integer orderStatus = null;
			try {
				orderStatus = Integer.valueOf(req.getParameter("orderStatus").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("orderStatus", "付款方式請選擇");
			}
			System.out.println(orderStatus);

			String receiveAddress = null;
			try {
				receiveAddress = req.getParameter("receiveAddress").trim();
			} catch (NumberFormatException e) {
				errorMsgs.put("receiveAddress", "請勿空白");
			}
			System.out.println(receiveAddress);

			String receiveName = null;
			try {
				receiveName = req.getParameter("receiveName").trim();
			} catch (NumberFormatException e) {
				errorMsgs.put("receiveName", "請勿空白");
			}
			System.out.println(receiveName);

			String receivePhone = null;
			try {
				receivePhone = req.getParameter("receivePhone").trim();
			} catch (NumberFormatException e) {
				errorMsgs.put("receivePhone", "請勿空白");
			}
			System.out.println(receivePhone);

			String remarks = req.getParameter("remarks");
			if (remarks != null) {
				remarks = remarks.trim();
			}
			System.out.println(remarks);

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/bidorder/update_BidOrder_input.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			BidOrder bidOrder1 = new BidOrder();
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

			/*************************** 2.開始修改資料 *****************************************/
			BidOrder bidOrder = bidOrderServiceImpl.updateAll(bidOrder1);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("BidOrder", bidOrder); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back_end/bidorder/listOneBidOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

		}

		/*********************** 刪除 *************************/
		/*********************** 刪除 *************************/
		/*********************** 刪除 *************************/
		/*********************** 刪除 *************************/
		/*********************** 刪除 *************************/
		if ("delete".equals(action)) {

			/*************************** 1.接收請求參數 ***************************************/

			Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));

			/*************************** 2.開始刪除資料 ***************************************/

			bidOrderServiceImpl.deleteBidOrder(bidOrderId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			// 原來的 String url = "/MemberCentre.jsp";
			// ok String url =
			// "/shipsetting/Shipsetting.do?action=getAll_For_MbrId&mbrId="+mbrId;
			// ok RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			// listOneEmp.jsp
			// ok successView.forward(req, res);

//			    String url1 = "/bidorder/BidOrder.do?action=getAll_For_MbrId&bidOrderId="+bidOrderId;

//			    String url1 = "/back_end/bidorder/listAllBidOrder.jsp";
//
//				res.sendRedirect(url1);

			String url = "/back_end/bidorder/listAllBidOrder.jsp";

			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

		}

		/*********************** 查買家競標訂單 *************************/
		/*********************** 查買家競標訂單 *************************/
		/*********************** 查買家競標訂單 *************************/
		/*********************** 查買家競標訂單 *************************/
		/*********************** 查買家競標訂單 *************************/

		if ("buyBidOrder".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/

			Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));

			/*************************** 2.開始查詢資料 ****************************************/

			List<BidOrder> bidOrder = bidOrderServiceImpl.getAllBuyMbrId(buyMbrId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("BidOrder", bidOrder);
			String url = "/front_end/bidorder/buyBidorder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		/*********************** 查買家競標待付款 *************************/
		/*********************** 查買家競標待付款 *************************/
		/*********************** 查買家競標待付款 *************************/
		/*********************** 查買家競標待付款 *************************/
		/*********************** 查買家競標待付款 *************************/
		if ("buyBidOrder0".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/

			Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
			Integer OrderStatus = 0;
			/*************************** 2.開始查詢資料 ****************************************/
			List<BidOrder> bidOrder = bidOrderServiceImpl.getAllOrderStatusAndBuyer(OrderStatus, buyMbrId);
			

//			Map<Integer, String> bidOrderMap = new HashMap<>();
//
//		    for (BidOrder bidOrder1 : bidOrder) {
//		        Integer bidItemId = bidOrder1.getBidItemId();
//		        Integer bidOrderId = bidOrder1.getBidOrderId();
//		        BidItem bidItem = bidItemServiceImpl.getBidItemByBidItemId(bidItemId);
//		        String bidName = bidItem.getBidName();
//		        
//		        bidOrderMap.put(bidOrderId, bidName);
//		    }
//		    req.setAttribute("BidOrderMap", bidOrderMap);

			

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
		    req.setAttribute("BidOrder", bidOrder);
			String url = "/front_end/bidorder/buyBidorder0.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		/*********************** 買家競標付款的查詢**************************/
		/*********************** 買家競標付款的查詢**************************/
		/*********************** 買家競標付款的查詢**************************/
		/*********************** 買家競標付款的查詢**************************/
		/*********************** 買家競標付款的查詢**************************/
	
		if ("pay_And_Address".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/

			Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));
			Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
			Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
			Integer bidItemId = Integer.valueOf(req.getParameter("bidItemId"));
			
			/*************************** 2.開始查詢資料 ****************************************/

			
			String receiveName = null; 
		    String receivePhone = null; 
		    String receiveAddress = null;

			
			List<ShipSetting> shipSetting = shipSettingServiceImpl.getByMbrId(buyMbrId);
			for (ShipSetting setting : shipSetting) {
			     receiveName = setting.getReceiveName(); 
			     receivePhone = setting.getReceivePhone(); 
			     receiveAddress = setting.getReceiveAddress();
			}
			
			String param = "?bidOrderId=" + bidOrderId + 
					"&bidOrderId=" + bidOrderId +
					"&buyMbrId="+ buyMbrId + 
					"&sellMbrId=" + sellMbrId + 
					"&bidItemId=" + bidItemId	+ 
					"&receiveName=" + receiveName + 
					"&receivePhone=" + receivePhone + 
					"&receiveAddress=" + receiveAddress;
			
////////////////////////////////////////////////////////////////////////////////////			
			

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			HttpSession session = req.getSession();
			session.setAttribute("shipSetting", shipSetting);

			
			

			String url = "/front_end/bidorder/payAndAddress.jsp"+param;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

		}
		/*********************** 買家競標付款的update***************************/
		/*********************** 買家競標付款的update***************************/
		/*********************** 買家競標付款的update***************************/
		/*********************** 買家競標付款的update***************************/
		/*********************** 買家競標付款的update***************************/

		
		if ("set_Pay_And_Address".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/


			
			Integer amount = Integer.valueOf(req.getParameter("amount"));
			Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));
			Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
			Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
			

			
			
			
			System.out.println(amount);
			System.out.println(bidOrderId);
			System.out.println(buyMbrId);
			
			
			String payTypeParam = req.getParameter("payType").trim();
			Integer payType = null;

			if (payTypeParam != null && !payTypeParam.isEmpty()) {
			    try {
			        payType = Integer.valueOf(payTypeParam);
			    } catch (NumberFormatException e) {
			        errorMsgs.put("payType", "請選擇付款方式");
			    }
			} 
			String payInfo = null;
			switch (payType) {
		    case 0:
		        
		        payInfo = "信用卡";
		        break;
		    case 1:
		        payInfo = "轉帳";
		        break;
		    case 2:
		        payInfo = "虛擬錢包";		        
		        break;

		}
			
			Integer orderStatus = 1;
			


		
			String receiveAddress = req.getParameter("receiveAddress");
			if (receiveAddress != null && !receiveAddress.trim().isEmpty()) {
			    // 如果 receiveName 不是 null 並且不是空白
			} else {
			    errorMsgs.put("receiveAddress", "請勿空白1");
			}

			String receiveName = req.getParameter("receiveName");
			if (receiveName != null && !receiveName.trim().isEmpty()) {
			    // 如果 receiveName 不是 null 並且不是空白
			} else {
			    errorMsgs.put("receiveName", "請勿空白1");
			}

			
			String receivePhone = req.getParameter("receivePhone");
			if (receivePhone != null && !receivePhone.trim().isEmpty()) {
			    // 如果 receiveName 不是 null 並且不是空白
			} else {
			    errorMsgs.put("receivePhone", "請勿空白1");
			}
			
			String remarks = req.getParameter("remarks").trim();
					

			
			BidOrder bidOrder =  new BidOrder();
			bidOrder.setBidOrderId(bidOrderId);
			bidOrder.setBuyMbrId(buyMbrId);
			bidOrder.setPayType(payType);
			bidOrder.setPayInfo(payInfo);
			bidOrder.setOrderStatus(orderStatus);
			bidOrder.setReceiveAddress(receiveAddress);
			bidOrder.setReceiveName(receiveName);
			bidOrder.setReceivePhone(receivePhone);
			bidOrder.setRemarks(remarks);
			
	
//	買家付款
			if(payType.equals(2)) {
///////////買家扣款////////////////////////////////////////////				
				Members members = membersServiceImpl.getByPrimaryKey(buyMbrId);
				Integer balance = members.getBalance();
				if(balance>amount) {
					
				balance= balance-amount;
				members.setBalance(balance);
				
				System.out.println(balance);
				membersServiceImpl.updateMembers(members);
				HttpSession session = req.getSession();
				session.setAttribute("user", members);
////////放入會員錢包異動紀錄	
				
				BalanceHistory balanceHistory = new  BalanceHistory();
				balanceHistory.setBidOrderId(bidOrderId);
				balanceHistory.setMbrId(buyMbrId);
				Date currentDate = new Date();
				Timestamp changeDate = new Timestamp(currentDate.getTime());
				balanceHistory.setChangeDate(changeDate);
				balanceHistory.setChangeValue(-amount);
				balanceHistoryServiceImpl.addBH(balanceHistory);
				
///////////買家////////////////////////////////////////////


				}else {
					errorMsgs.put("balance", "餘額不足");
				}
			}
			
				if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/bidorder/payAndAddress.jsp");
			failureView.forward(req, res);
			
				return;// 程式中斷
			}
			/***************************2.開始修改資料*****************************************/
			bidOrderServiceImpl.updateAll(bidOrder);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

//			String url = "/MemberCentre.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//			successView.forward(req, res);
////////////////////////////////////////////////////////////////////	
			HttpSession session = req.getSession();
			session.removeAttribute("shipSetting");
////////////////////////////////////////////////////////////////////

			String url = "/bidordernotify/BidOrderNotify.do?Action=set_Pay_And_Address&sellMbrId="+sellMbrId+"&buyMbrId="+buyMbrId+"&bidOrderId="+bidOrderId;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		
		/*********************** 買家競標待出貨查詢***************************/
		/*********************** 買家競標待出貨查詢***************************/
		/*********************** 買家競標待出貨查詢***************************/
		/*********************** 買家競標待出貨查詢***************************/
		/*********************** 買家競標待出貨查詢***************************/
		
		if ("buyBidOrder1".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			
			Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
			Integer OrderStatus = 1;
			/*************************** 2.開始查詢資料 ****************************************/
			
			List<BidOrder> bidOrder = bidOrderServiceImpl.getAllOrderStatusAndBuyer(OrderStatus, buyMbrId);
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("BidOrder", bidOrder);
			String url = "/front_end/bidorder/buyBidorder1.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
		
		/*********************** 買家競標待收貨查詢***************************/
		/*********************** 買家競標待收貨查詢***************************/
		/*********************** 買家競標待收貨查詢***************************/
		/*********************** 買家競標待收貨查詢***************************/
		/*********************** 買家競標待收貨查詢***************************/
		
		if ("buyBidOrder2".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/

			Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
			Integer OrderStatus = 2;
			/*************************** 2.開始查詢資料 ****************************************/

			List<BidOrder> bidOrder = bidOrderServiceImpl.getAllOrderStatusAndBuyer(OrderStatus, buyMbrId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("BidOrder", bidOrder);
			String url = "/front_end/bidorder/buyBidorder2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
		
		/*********************** 買家競標收貨***************************/
		/*********************** 買家競標收貨***************************/
		/*********************** 買家競標收貨***************************/
		/*********************** 買家競標收貨***************************/
		/*********************** 買家競標收貨***************************/
		
		if ("buyer_auction_receipt".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/

//			String data = req.getParameter("data");
//			String[] values = data.split(",");
//			
//			    Integer amount = Integer.valueOf(values[0]);
//			    Integer sellMbrId = Integer.valueOf(values[1]);
//			    Integer buyMbrId = Integer.valueOf(values[2]);
//			    Integer bidOrderId = Integer.valueOf(values[3]);
//			
			Integer amount = Integer.valueOf(req.getParameter("amount"));
			Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
			Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
			Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));
			Integer OrderStatus = 3;
			/*************************** 2.開始查詢資料 ****************************************/

			BidOrder bidOrder = bidOrderServiceImpl.getByPrimaryKey(bidOrderId);
		
			bidOrder.setOrderStatus(OrderStatus);
			bidOrderServiceImpl.updateAll(bidOrder);
			
			
	///////////賣家////////////////////////////////////////////
			
					Members members = membersServiceImpl.getByPrimaryKey(sellMbrId);
					Integer balance = members.getBalance();
					balance = members.getBalance();
					balance= balance+amount;
					members.setBalance(balance);
					membersServiceImpl.updateMembers(members);
	////////放入會員錢包異動紀錄				
					BalanceHistory balanceHistory = new  BalanceHistory();
					balanceHistory = new  BalanceHistory();
					balanceHistory.setBidOrderId(bidOrderId);
					balanceHistory.setMbrId(sellMbrId);
					Date currentDate = new Date();
					Timestamp changeDate = new Timestamp(currentDate.getTime());
					balanceHistory.setChangeDate(changeDate);
					balanceHistory.setChangeValue(amount);
					balanceHistoryServiceImpl.addBH(balanceHistory);
	///////////賣家////////////////////////////////////////////

			
			String url = "/bidordernotify/BidOrderNotify.do?action=buyer_auction_receipt&sellMbrId="+sellMbrId+"&buyMbrId="+buyMbrId+"&bidOrderId="+bidOrderId;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
		
		/*********************** 前往買家競標評價頁面***************************/
		/*********************** 前往買家競標評價頁面***************************/
		/*********************** 前往買家競標評價頁面***************************/
		/*********************** 前往買家競標評價頁面***************************/
		/*********************** 前往買家競標評價頁面***************************/
		
		if ("buy_Bidorder_Rating".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			
			Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));
			
			/*************************** 2.開始查詢資料 ****************************************/
			
			BidOrder bidOrder = bidOrderServiceImpl.getByPrimaryKey(bidOrderId);
			
			
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("BidOrder", bidOrder);
			
			String url = "/front_end/bidorder/buyBidorderRating.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
		
		/*********************** 存入買家競標評價***************************/
		/*********************** 存入買家競標評價***************************/
		/*********************** 存入買家競標評價***************************/
		/*********************** 存入買家競標評價***************************/
		/*********************** 存入買家競標評價***************************/
//TODO
		if ("buy_Rating_in".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
			Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
			Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));
			Integer buyStar = Integer.valueOf(req.getParameter("buyStar"));
			String buyerratingdesc = req.getParameter("buyerRatingDesc");
			Part imagePart = req.getPart("image");
	        
	        byte[] image = readImageData(imagePart);
			/*************************** 2.開始查詢資料 ****************************************/
			
			BidOrder bidOrder = bidOrderServiceImpl.getByPrimaryKey(bidOrderId);
			bidOrder.setBuyStar(buyStar);
			bidOrder.setBuyerRatingDesc(buyerratingdesc);
			
			
			bidOrderServiceImpl.updateAll(bidOrder);
			bidOrderRatingImageServiceImpl.addBidOrderRatingImage(bidOrderId, image);
			
			Members members = membersServiceImpl.getByPrimaryKey(sellMbrId);
			Integer buyStarOld = members.getSellStar();
			buyStar += buyStarOld;
			members.setSellStar(buyStar);
			
			
			Integer sellRating =  members.getSellRating();
			sellRating += 1;
			members.setSellRating(sellRating);
			membersServiceImpl.updateMembers(members);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			
			
			String url = "/bidorder/BidOrder.do?action=buyBidOrder3&buyMbrId="+buyMbrId;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
		
		
		
		
		
		
		/*********************** 買家競標交易完成查詢***************************/
		/*********************** 買家競標交易完成查詢***************************/
		/*********************** 買家競標交易完成查詢***************************/
		/*********************** 買家競標交易完成查詢***************************/
		/*********************** 買家競標交易完成查詢***************************/
		
		if ("buyBidOrder3".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			
			Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
			Integer OrderStatus = 3;
			/*************************** 2.開始查詢資料 ****************************************/
			
			List<BidOrder> bidOrder = bidOrderServiceImpl.getAllOrderStatusAndBuyer(OrderStatus, buyMbrId);
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("BidOrder", bidOrder);
			String url = "/front_end/bidorder/buyBidorder3.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
		
		/*********************** 買家競標未付款取消交易取消交易***************************/
		/*********************** 買家競標未付款取消交易取消交易***************************/
		/*********************** 買家競標未付款取消交易取消交易***************************/
		/*********************** 買家競標未付款取消交易取消交易***************************/
		/*********************** 買家競標未付款取消交易取消交易***************************/
		
		
		if ("buy_Cancel_Order_No_Pay".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			
			Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
			Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
			Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));
			Integer OrderStatus = 4;
			/*************************** 2.開始查詢資料 ****************************************/
			
			BidOrder bidOrder = bidOrderServiceImpl.getByPrimaryKey(bidOrderId);
			
			bidOrder.setOrderStatus(OrderStatus);
			bidOrderServiceImpl.updateAll(bidOrder);
			
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			String url = "/bidordernotify/BidOrderNotify.do?action=buy_Cancel_Order&sellMbrId="+sellMbrId+"&buyMbrId="+buyMbrId+"&bidOrderId="+bidOrderId;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
		/*********************** 買家競標已付款取消交易取消交易***************************/
		/*********************** 買家競標已付款取消交易取消交易***************************/
		/*********************** 買家競標已付款取消交易取消交易***************************/
		/*********************** 買家競標已付款取消交易取消交易***************************/
		/*********************** 買家競標已付款取消交易取消交易***************************/
		
		
		if ("buy_Cancel_Order".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			
			Integer amount = Integer.valueOf(req.getParameter("amount"));
			Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
			Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
			Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));
			Integer OrderStatus = 4;
			/*************************** 2.開始查詢資料 ****************************************/
			
			BidOrder bidOrder = bidOrderServiceImpl.getByPrimaryKey(bidOrderId);
			
			bidOrder.setOrderStatus(OrderStatus);
			bidOrderServiceImpl.updateAll(bidOrder);
			
			
			
	///////////買家退款////////////////////////////////////////////				
					Members members = membersServiceImpl.getByPrimaryKey(buyMbrId);
					Integer balance = members.getBalance();
					
						
					balance= balance+amount;
					members.setBalance(balance);
					
					System.out.println(balance);
					membersServiceImpl.updateMembers(members);
					HttpSession session = req.getSession();
					session.setAttribute("user", members);
	////////放入會員錢包異動紀錄	
					
					BalanceHistory balanceHistory = new  BalanceHistory();
					balanceHistory.setBidOrderId(bidOrderId);
					balanceHistory.setMbrId(buyMbrId);
					Date currentDate = new Date();
					Timestamp changeDate = new Timestamp(currentDate.getTime());
					balanceHistory.setChangeDate(changeDate);
					balanceHistory.setChangeValue(amount);
					balanceHistoryServiceImpl.addBH(balanceHistory);
					
	///////////買家////////////////////////////////////////////
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			String url2 = "/bidordernotify/BidOrderNotify.do?action=buy_Cancel_Order&sellMbrId="+sellMbrId+"&buyMbrId="+buyMbrId+"&bidOrderId="+bidOrderId;
			RequestDispatcher successView2 = req.getRequestDispatcher(url2); // 成功轉交 listOneEmp.jsp
			successView2.forward(req, res);
			
			
		}
		
		/*********************** 買家競標交易不成立查詢***************************/
		/*********************** 買家競標交易不成立查詢***************************/
		/*********************** 買家競標交易不成立查詢***************************/
		/*********************** 買家競標交易不成立查詢***************************/
		/*********************** 買家競標交易不成立查詢***************************/
		
		if ("buyBidOrder4".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			
			Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
			Integer OrderStatus = 4;
			/*************************** 2.開始查詢資料 ****************************************/
			
			List<BidOrder> bidOrder = bidOrderServiceImpl.getAllOrderStatusAndBuyer(OrderStatus, buyMbrId);
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("BidOrder", bidOrder);
			String url = "/front_end/bidorder/buyBidorder4.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
		

		
		

		
		
		
		
		
		
		

		/*********************** 查賣家競標訂單 *************************/
		/*********************** 查賣家競標訂單 *************************/
		/*********************** 查賣家競標訂單 *************************/
		/*********************** 查賣家競標訂單 *************************/
		/*********************** 查賣家競標訂單 *************************/

		if ("sellBidOrder".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/

			Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));

			/*************************** 2.開始查詢資料 ****************************************/

			List<BidOrder> bidOrder = bidOrderServiceImpl.getAllSellMbrId(sellMbrId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("BidOrder", bidOrder);
			String url = "/front_end/bidorder/sellBidorder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

			
		}
		/*********************** 賣家查買家待付款 *************************/
		/*********************** 賣家查買家待付款 *************************/
		/*********************** 賣家查買家待付款 *************************/
		/*********************** 賣家查買家待付款 *************************/
		/*********************** 賣家查買家待付款 *************************/

		if ("sellBidOrder0".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			
			Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
			Integer OrderStatus = 0;
			/*************************** 2.開始查詢資料 ****************************************/
			
			List<BidOrder> bidOrder = bidOrderServiceImpl.getAllOrderStatusAndSeller(OrderStatus, sellMbrId);
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("BidOrder", bidOrder);
			String url = "/front_end/bidorder/sellBidorder0.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
		/*********************** 查賣家待出貨 *************************/
		/*********************** 查賣家待出貨 *************************/
		/*********************** 查賣家待出貨 *************************/
		/*********************** 查賣家待出貨 *************************/
		/*********************** 查賣家待出貨 *************************/
		
		if ("sellBidOrder1".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			
			Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
			Integer OrderStatus = 1;
			/*************************** 2.開始查詢資料 ****************************************/
			
			List<BidOrder> bidOrder = bidOrderServiceImpl.getAllOrderStatusAndSeller(OrderStatus, sellMbrId);
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("BidOrder", bidOrder);
			String url = "/front_end/bidorder/sellBidorder1.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
		/*********************** 賣家出貨 *************************/
		/*********************** 賣家出貨 *************************/
		/*********************** 賣家出貨 *************************/
		/*********************** 賣家出貨 *************************/
		/*********************** 賣家出貨 *************************/
		
		if ("shipped".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			
			Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
			Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
			Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));
			Integer OrderStatus = 2;
			/*************************** 2.開始查詢資料 ****************************************/
			BidOrder bidOrder = bidOrderServiceImpl.getByPrimaryKey(bidOrderId);
			
			bidOrder.setOrderStatus(OrderStatus);
			bidOrderServiceImpl.updateAll(bidOrder);
			
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			String url = "/bidordernotify/BidOrderNotify.do?action=shipped&sellMbrId="+sellMbrId+"&buyMbrId="+buyMbrId+"&bidOrderId="+bidOrderId;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
		
		
		/*********************** 買家競標已付款賣家取消交易***************************/
		/*********************** 買家競標已付款賣家取消交易***************************/
		/*********************** 買家競標已付款賣家取消交易***************************/
		/*********************** 買家競標已付款賣家取消交易***************************/
		/*********************** 買家競標已付款賣家取消交易***************************/
		
		
		if ("sell_Cancel_Order".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			
			Integer amount = Integer.valueOf(req.getParameter("amount"));
			Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
			Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
			Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));
			Integer OrderStatus = 4;
			/*************************** 2.開始查詢資料 ****************************************/
			
			BidOrder bidOrder = bidOrderServiceImpl.getByPrimaryKey(bidOrderId);
			
			bidOrder.setOrderStatus(OrderStatus);
			bidOrderServiceImpl.updateAll(bidOrder);
			
			
			
	///////////買家退款////////////////////////////////////////////				
					Members members = membersServiceImpl.getByPrimaryKey(buyMbrId);
					Integer balance = members.getBalance();
					
						
					balance= balance+amount;
					members.setBalance(balance);
					
					System.out.println(balance);
					membersServiceImpl.updateMembers(members);
					HttpSession session = req.getSession();
					session.setAttribute("user", members);
	////////放入會員錢包異動紀錄	
					
					BalanceHistory balanceHistory = new  BalanceHistory();
					balanceHistory.setBidOrderId(bidOrderId);
					balanceHistory.setMbrId(buyMbrId);
					Date currentDate = new Date();
					Timestamp changeDate = new Timestamp(currentDate.getTime());
					balanceHistory.setChangeDate(changeDate);
					balanceHistory.setChangeValue(amount);
					balanceHistoryServiceImpl.addBH(balanceHistory);
					
	///////////買家////////////////////////////////////////////
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			String url = "/bidordernotify/BidOrderNotify.do?action=sell_Cancel_Order&sellMbrId="+sellMbrId+"&buyMbrId="+buyMbrId+"&bidOrderId="+bidOrderId;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
		
		/*********************** 查賣家等買家收貨 *************************/
		/*********************** 查賣家等買家收貨 *************************/
		/*********************** 查賣家等買家收貨 *************************/
		/*********************** 查賣家等買家收貨 *************************/
		/*********************** 查賣家等買家收貨 *************************/
		/*********************** 查賣家等買家收貨 *************************/
		
		if ("sellBidOrder2".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			
			Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
			Integer OrderStatus = 2;
			/*************************** 2.開始查詢資料 ****************************************/
			
			List<BidOrder> bidOrder = bidOrderServiceImpl.getAllOrderStatusAndSeller(OrderStatus, sellMbrId);
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("BidOrder", bidOrder);
			String url = "/front_end/bidorder/sellBidorder2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
		/*********************** 查賣家競標訂單已完成 *************************/
		/*********************** 查賣家競標訂單已完成 *************************/
		/*********************** 查賣家競標訂單已完成 *************************/
		/*********************** 查賣家競標訂單已完成 *************************/
		/*********************** 查賣家競標訂單已完成 *************************/
		
		if ("sellBidOrder3".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			
			Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
			Integer OrderStatus = 3;
			/*************************** 2.開始查詢資料 ****************************************/
			
			List<BidOrder> bidOrder = bidOrderServiceImpl.getAllOrderStatusAndSeller(OrderStatus, sellMbrId);
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("BidOrder", bidOrder);
			String url = "/front_end/bidorder/sellBidorder3.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
		/*********************** 前往賣家競標評價頁面***************************/
		/*********************** 前往賣家競標評價頁面***************************/
		/*********************** 前往賣家競標評價頁面***************************/
		/*********************** 前往賣家競標評價頁面***************************/
		/*********************** 前往賣家競標評價頁面***************************/
		//TODO	
		if ("sell_Bidorder_Rating".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			
			Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));
			
			/*************************** 2.開始查詢資料 ****************************************/
			
			BidOrder bidOrder = bidOrderServiceImpl.getByPrimaryKey(bidOrderId);
			
			
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("BidOrder", bidOrder);
			
			String url = "/front_end/bidorder/sellBidorderRating.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
		
		/*********************** 存入賣家競標評價***************************/
		/*********************** 存入賣家競標評價***************************/
		/*********************** 存入賣家競標評價***************************/
		/*********************** 存入賣家競標評價***************************/
		/*********************** 存入賣家競標評價***************************/
//TODO
		if ("sell_Rating_in".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));

			Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
			Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));
			Integer sellStar = Integer.valueOf(req.getParameter("sellStar"));
			String sellerratingdesc = req.getParameter("sellerRatingDesc");
			
	        System.out.println(sellStar);
			/*************************** 2.開始查詢資料 ****************************************/
			
			BidOrder bidOrder = bidOrderServiceImpl.getByPrimaryKey(bidOrderId);
			bidOrder.setSellStar(sellStar);
			bidOrder.setSellerRatingDesc(sellerratingdesc);
			
			
			bidOrderServiceImpl.updateAll(bidOrder);
			
			Members members = membersServiceImpl.getByPrimaryKey(buyMbrId);
			Integer sellStarOld = members.getBuyStar();
			sellStar += sellStarOld;
			members.setBuyStar(sellStar);
			
			Integer buyRating =  members.getBuyRating();
			buyRating += 1;
			members.setBuyRating(buyRating);
			membersServiceImpl.updateMembers(members);
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			
			
			String url = "/bidorder/BidOrder.do?action=sellBidOrder3&sellMbrId="+sellMbrId;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
		/*********************** 查賣家競標訂單不成立 *************************/
		/*********************** 查賣家競標訂單不成立 *************************/
		/*********************** 查賣家競標訂單不成立 *************************/
		/*********************** 查賣家競標訂單不成立 *************************/
		/*********************** 查賣家競標訂單不成立 *************************/
		
		if ("sellBidOrder4".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			
			Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
			Integer OrderStatus = 4;
			/*************************** 2.開始查詢資料 ****************************************/
			
			List<BidOrder> bidOrder = bidOrderServiceImpl.getAllOrderStatusAndSeller(OrderStatus, sellMbrId);
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("BidOrder", bidOrder);
			String url = "/front_end/bidorder/sellBidorder4.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		}

	}
	
	
	
	private byte[] readImageData(Part imagePart) throws IOException {
        InputStream inputStream = imagePart.getInputStream();
        byte[] imageData = new byte[(int) imagePart.getSize()];
        inputStream.read(imageData);
        return imageData;
    }

}
