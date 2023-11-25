package com.twoclothing.gordon.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.gordon.service.BidOrderNotifyServiceImpl;
import com.twoclothing.gordon.service.BidOrderServiceImpl;
import com.twoclothing.model.abid.bidorder.BidOrder;
import com.twoclothing.redismodel.notice.Notice;
import com.twoclothing.redismodel.notice.NoticeJedisDAO;

@WebServlet("/bidordernotify/BidOrderNotify.do")
public class BidOrderNotifyServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		BidOrderNotifyServiceImpl bidOrderNotifyServiceImpl = new BidOrderNotifyServiceImpl();
		BidOrderServiceImpl bidOrderServiceImpl = new BidOrderServiceImpl();
		Date currentDate = new Date();

		String action = req.getParameter("action");
		String bidOrderAction = (String) req.getAttribute("BidOrderAction");
		/***********************等待買家付款, 請完成訂單付款  *************************/
		/***********************等待買家付款, 請完成訂單付款  *************************/
		/***********************等待買家付款, 請完成訂單付款  *************************/
		/***********************等待買家付款, 請完成訂單付款  *************************/
		/***********************等待買家付款, 請完成訂單付款  *************************/

		if ("addBidOrder_so_AddNotify".equals(action)) {

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			BidOrder bidOrderInput = (BidOrder) req.getAttribute("BidOrder");
			Integer bidItemId = null;
			Integer buyMbrId = null;
			Integer sellMbrId = null;
			Timestamp notifyDate = null;
			Integer bidOrderId = null;

			if (bidOrderInput != null) {
				bidItemId = bidOrderInput.getBidItemId();
				buyMbrId = bidOrderInput.getBuyMbrId();
				sellMbrId = bidOrderInput.getSellMbrId();

				notifyDate = new Timestamp(currentDate.getTime());

				List<BidOrder> bidOrder = bidOrderServiceImpl.getAllSellMbrIdAndBuyMbrId(sellMbrId, buyMbrId,
						bidItemId);

				if (!bidOrder.isEmpty()) {
					BidOrder firstBidOrder = bidOrder.get(0);
					bidOrderId = firstBidOrder.getBidOrderId();

				}
				String Type = "競標訂單通知";
				String title = "訂單已成立";
				String sellContent = "等待買家付款";
				String buyContent = "請完成訂單付款";
				/***************************2.開始新增資料***************************************/
				//買家
				bidOrderNotifyServiceImpl.addBidOrderNotify(buyMbrId, bidOrderId, notifyDate, title, buyContent);
				//賣家
				bidOrderNotifyServiceImpl.addBidOrderNotify(sellMbrId, bidOrderId, notifyDate, title, sellContent);
		/////////////////////////////////////redis測試////////////////////	
				
				String buyUrl = "/bidorder/BidOrder.do?action=buyBidOrder0&buyMbrId="+buyMbrId;
				
				NoticeJedisDAO noticeJedisDAO = new NoticeJedisDAO();
				Notice notice = new Notice();
				notice.setType(Type);
				notice.setHead(title);
				notice.setContent(buyContent);
				notice.setLink(buyUrl);
				notice.setImageLink("/images/Mainicon.png");
				noticeJedisDAO.insert(notice, buyMbrId);
				
				String sellurl = "/bidorder/BidOrder.do?action=sellBidOrder0&sellMbrId="+sellMbrId;
				notice.setType(Type);
				notice.setHead(title);
				notice.setContent(sellContent);
				notice.setLink(sellurl);
				notice.setImageLink("/images/Mainicon.png");
				noticeJedisDAO.insert(notice, sellMbrId);

/////////////////////////////////////redis測試////////////////////	
			
			}
			/***************************賣家待付款***************************************/

			String url = "/bidorder/BidOrder.do?action=buyBidOrder0&buyMbrId="+buyMbrId;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		


		}
		/***********************待出貨  *************************/
		/***********************待出貨  *************************/
		/***********************待出貨  *************************/
		/***********************待出貨  *************************/
		/***********************待出貨  *************************/
		
		
		if ("set_Pay_And_Address".equals(action)) {
			
			Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));
			Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
			Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
			String type = "競標訂單通知";
			String title = "買家已付款";
			String sellContent = "買家已付款請進行出貨";
			String buyContent = "等待賣家出貨";
			
			Timestamp notifyDate = null;
			notifyDate = new Timestamp(currentDate.getTime());
			
			//買家
			bidOrderNotifyServiceImpl.addBidOrderNotify(buyMbrId, bidOrderId, notifyDate, title, buyContent);
			//賣家
			bidOrderNotifyServiceImpl.addBidOrderNotify(sellMbrId, bidOrderId, notifyDate, title, sellContent);


/////////////////////////////////////redis測試////////////////////	

			NoticeJedisDAO noticeJedisDAO = new NoticeJedisDAO();
			Notice notice = new Notice();
			
			String buyUrl = "/bidorder/BidOrder.do?action=buyBidOrder1&buyMbrId="+buyMbrId;
			notice.setType(type);
			notice.setHead(title);
			notice.setContent(buyContent);
			notice.setLink(buyUrl);
			notice.setImageLink("/images/Mainicon.png");
			noticeJedisDAO.insert(notice, buyMbrId);
			
			String sellUrl = "/bidorder/BidOrder.do?action=sellBidOrder1&sellMbrId="+sellMbrId;
			notice.setType(type);
			notice.setHead(title);
			notice.setContent(sellContent);
			notice.setLink(sellUrl);
			notice.setImageLink("/images/Mainicon.png");
			noticeJedisDAO.insert(notice, sellMbrId);
/////////////////////////////////////redis測試////////////////////	
			/***************************去待出貨***************************************/

			String url = "/bidorder/BidOrder.do?action=buyBidOrder0&buyMbrId="+buyMbrId;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		
		/***********************買家收貨完成訂單  *************************/
		/***********************買家收貨完成訂單  *************************/
		/***********************買家收貨完成訂單  *************************/
		/***********************買家收貨完成訂單  *************************/
		/***********************買家收貨完成訂單  *************************/
	
		
		
		if ("buyer_auction_receipt".equals(action)) {
		
		Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));
		Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
		Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
		String type = "競標訂單通知";
		String title = "競標訂單完成";
		String buyContent = "競標訂單完成";
		String sellContent = "競標訂單完成已取得款項";
			
		Timestamp notifyDate = null;
		notifyDate = new Timestamp(currentDate.getTime());
		
		//買家
		bidOrderNotifyServiceImpl.addBidOrderNotify(buyMbrId, bidOrderId, notifyDate, title, buyContent);
		//賣家
		bidOrderNotifyServiceImpl.addBidOrderNotify(sellMbrId, bidOrderId, notifyDate, title, sellContent);
		
		
		
		/////////////////////////////////////redis測試////////////////////	
		
		NoticeJedisDAO noticeJedisDAO = new NoticeJedisDAO();
		Notice notice = new Notice();
		
		String buyUrl = "/bidorder/BidOrder.do?action=buyBidOrder3&buyMbrId="+buyMbrId;
		notice.setType(type);
		notice.setHead(title);
		notice.setContent(buyContent);
		notice.setLink(buyUrl);
		notice.setImageLink("/images/Mainicon.png");
		noticeJedisDAO.insert(notice, buyMbrId);
		
		String sellUrl = "/bidorder/BidOrder.do?action=sellBidOrder3&sellMbrId="+sellMbrId;
		notice.setType(type);
		notice.setHead(title);
		notice.setContent(sellContent);
		notice.setLink(sellUrl);
		notice.setImageLink("/images/Mainicon.png");
		noticeJedisDAO.insert(notice, sellMbrId);
		/////////////////////////////////////redis測試////////////////////	
		/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

		
		String url = "/bidorder/BidOrder.do?action=buyBidOrder3&buyMbrId="+buyMbrId;
		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
		successView.forward(req, res);
		}
		
		/***********************買家取消交易  *************************/
		/***********************買家取消交易  *************************/
		/***********************買家取消交易  *************************/
		/***********************買家取消交易  *************************/
		/***********************買家取消交易  *************************/
		
		
		
		if ("buy_Cancel_Order".equals(action)) {
			
			Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));
			Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
			Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
			String type = "競標訂單通知";
			String title = "買家取消訂單";
			String sellContent = "買家取消訂單";
			String buyContent = "買家取消訂單";
System.out.println("沒錢取消訂單1");
			Timestamp notifyDate = null;
			notifyDate = new Timestamp(currentDate.getTime());
			
			//買家
			bidOrderNotifyServiceImpl.addBidOrderNotify(buyMbrId, bidOrderId, notifyDate, title, buyContent);
			//賣家
			bidOrderNotifyServiceImpl.addBidOrderNotify(sellMbrId, bidOrderId, notifyDate, title, sellContent);
			
			/////////////////////////////////////redis測試////////////////////	
						
			NoticeJedisDAO noticeJedisDAO = new NoticeJedisDAO();
			Notice notice = new Notice();
			
			String buyUrl = "/bidorder/BidOrder.do?action=buyBidOrder4&buyMbrId="+buyMbrId;
			notice.setType(type);
			notice.setHead(title);
			notice.setContent(buyContent);
			notice.setLink(buyUrl);
			notice.setImageLink("/images/Mainicon.png");
			noticeJedisDAO.insert(notice, buyMbrId);
			
			String sellUrl = "/bidorder/BidOrder.do?action=sellBidOrder4&sellMbrId="+sellMbrId;
			notice.setType(type);
			notice.setHead(title);
			notice.setContent(sellContent);
			notice.setLink(sellUrl);
			notice.setImageLink("/images/Mainicon.png");
			noticeJedisDAO.insert(notice, sellMbrId);
			/////////////////////////////////////redis測試////////////////////	
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			
			
			String url = "/bidorder/BidOrder.do?action=buyBidOrder4&buyMbrId="+buyMbrId;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
						
			
		}
		
		/***********************買家出貨 *************************/
		/***********************買家出貨 *************************/
		/***********************買家出貨 *************************/
		/***********************買家出貨 *************************/
		/***********************買家出貨 *************************/
		
		
		if ("shipped".equals(action)) {
			
			Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));
			Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
			Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
			String type = "競標訂單通知";
			String title = "賣家已出貨";
			String sellContent = "賣家已出貨";
			String buyContent = "賣家已出貨";
			
			Timestamp notifyDate = null;
			notifyDate = new Timestamp(currentDate.getTime());
			
			//買家
			bidOrderNotifyServiceImpl.addBidOrderNotify(buyMbrId, bidOrderId, notifyDate, title, buyContent);
			//賣家
			bidOrderNotifyServiceImpl.addBidOrderNotify(sellMbrId, bidOrderId, notifyDate, title, sellContent);
			
			/////////////////////////////////////redis測試////////////////////	
			
			NoticeJedisDAO noticeJedisDAO = new NoticeJedisDAO();
			Notice notice = new Notice();
			
			String buyUrl = "/bidorder/BidOrder.do?action=buyBidOrder2&buyMbrId="+buyMbrId;
			notice.setType(type);
			notice.setHead(title);
			notice.setContent(buyContent);
			notice.setLink(buyUrl);
			notice.setImageLink("/images/Mainicon.png");
			noticeJedisDAO.insert(notice, buyMbrId);
			
			String sellUrl = "/bidorder/BidOrder.do?action=sellBidOrder2&sellMbrId="+sellMbrId;
			notice.setType(type);
			notice.setHead(title);
			notice.setContent(sellContent);
			notice.setLink(sellUrl);
			notice.setImageLink("/images/Mainicon.png");
			noticeJedisDAO.insert(notice, sellMbrId);
			/////////////////////////////////////redis測試////////////////////	
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			
			
			String url = "/bidorder/BidOrder.do?action=sellBidOrder2&sellMbrId="+sellMbrId;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
		
		/***********************賣家取消交易  *************************/
		/***********************賣家取消交易  *************************/
		/***********************賣家取消交易  *************************/
		/***********************賣家取消交易  *************************/
		/***********************賣家取消交易  *************************/
		
		
		
		if ("sell_Cancel_Order".equals(action)) {
			
			Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));
			Integer sellMbrId = Integer.valueOf(req.getParameter("sellMbrId"));
			Integer buyMbrId = Integer.valueOf(req.getParameter("buyMbrId"));
			String type = "競標訂單通知";
			String title = "賣家取消訂單";
			String sellContent = "賣家取消訂單已退款";
			String buyContent = "賣家取消訂單";
				
			Timestamp notifyDate = null;
			notifyDate = new Timestamp(currentDate.getTime());
			
			//買家
			bidOrderNotifyServiceImpl.addBidOrderNotify(buyMbrId, bidOrderId, notifyDate, title, buyContent);
			//賣家
			bidOrderNotifyServiceImpl.addBidOrderNotify(sellMbrId, bidOrderId, notifyDate, title, sellContent);
			
			/////////////////////////////////////redis測試////////////////////	
						
			NoticeJedisDAO noticeJedisDAO = new NoticeJedisDAO();
			Notice notice = new Notice();
			
			String buyUrl = "/bidorder/BidOrder.do?action=buyBidOrder4&buyMbrId="+buyMbrId;
			notice.setType(type);
			notice.setHead(title);
			notice.setContent(buyContent);
			notice.setLink(buyUrl);
			notice.setImageLink("/images/Mainicon.png");
			noticeJedisDAO.insert(notice, buyMbrId);
			
			String sellUrl = "/bidorder/BidOrder.do?action=sellBidOrder4&sellMbrId="+sellMbrId;
			notice.setType(type);
			notice.setHead(title);
			notice.setContent(sellContent);
			notice.setLink(sellUrl);
			notice.setImageLink("/images/Mainicon.png");
			noticeJedisDAO.insert(notice, sellMbrId);
			/////////////////////////////////////redis測試////////////////////	
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			
			
			String url = "/bidorder/BidOrder.do?action=sellBidOrder4&sellMbrId="+sellMbrId;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
						
			
		}
		
		
	}
}
