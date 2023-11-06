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

			// 现在您可以在这里使用 bidOrder 对象来访问之前存储的数据
			if (bidOrderInput != null) {
				bidItemId = bidOrderInput.getBidItemId();
				buyMbrId = bidOrderInput.getBuyMbrId();
				sellMbrId = bidOrderInput.getSellMbrId();

				notifyDate = new Timestamp(currentDate.getTime());

				List<BidOrder> bidOrder = bidOrderServiceImpl.getAllSellMbrIdAndBuyMbrId(sellMbrId, buyMbrId,
						bidItemId);

				if (!bidOrder.isEmpty()) {
					BidOrder firstBidOrder = bidOrder.get(0);
					// 获取第一条记录的各个字段值
					bidOrderId = firstBidOrder.getBidOrderId();

					// 其他字段也可以类似地获取
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
				
				String buyUrl = "/bidorder/BidOrder.do?action=get_Pay_And_Address&buyMbrId="+buyMbrId;
				
				NoticeJedisDAO noticeJedisDAO = new NoticeJedisDAO();
				Notice notice = new Notice();
				notice.setType(Type);
				notice.setHead(title);
				notice.setContent(buyContent);
				notice.setLink(buyUrl);
				notice.setImageLink("/images/Mainicon.jpg");
				noticeJedisDAO.insert(notice, buyMbrId);
				
				String sellurl = "/bidorder/BidOrder.do?action=get_Pay_And_Address&sellMbrId="+sellMbrId;
				notice.setType(Type);
				notice.setHead(title);
				notice.setContent(sellContent);
				notice.setLink(sellurl);
				notice.setImageLink("/images/Mainicon.jpg");
				noticeJedisDAO.insert(notice, sellMbrId);
//				BidOrderNotify sellBidOrderNotify = new BidOrderNotify();
//				BidOrderNotify buyBidOrderNotify = new BidOrderNotify();
//				
//				sellBidOrderNotify.setMbrId(sellMbrId);
//				sellBidOrderNotify.setBidOrderId(bidOrderId);
//				sellBidOrderNotify.setNotifyDate(notifyDate);
//				sellBidOrderNotify.setTitle(title);
//				sellBidOrderNotify.setContent(sellContent);
//				
//				buyBidOrderNotify.setMbrId(buyMbrId);
//				buyBidOrderNotify.setBidOrderId(bidOrderId);
//				buyBidOrderNotify.setNotifyDate(notifyDate);
//				buyBidOrderNotify.setTitle(title);
//				buyBidOrderNotify.setContent(buyContent);
//				
//				
//				Jedis jedis = new Jedis("localhost", 6379);
//				
//				jedis.select(0);
//				
//				Gson gson = new Gson();
//				    String sellValue = gson.toJson(sellBidOrderNotify);
//				    String buyValue = gson.toJson(buyBidOrderNotify);
//
//				    String buyKey =  String.valueOf(buyMbrId);
//				    String sellKey =  String.valueOf(sellMbrId);
//
//				// 存储数据
//				jedis.set(buyKey, sellValue);
//				
//
//				
//				// 存储数据
//				jedis.set(sellKey, buyValue);
//				
//				
//				jedis.close();
		
				
/////////////////////////////////////redis測試////////////////////	
			
			}
			/***************************去會員中心***************************************/

			String url = "/MemberCentre.jsp";
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
			
			String buyUrl = "/bidorder/BidOrder.do?action=get_Pay_And_Address&buyMbrId="+buyMbrId;
			notice.setType(type);
			notice.setHead(title);
			notice.setContent(buyContent);
			notice.setLink(buyUrl);
			notice.setImageLink("/images/Mainicon.jpg");
			noticeJedisDAO.insert(notice, buyMbrId);
			
			String sellUrl = "/bidorder/BidOrder.do?action=get_Pay_And_Address&sellMbrId="+sellMbrId;
			notice.setType(type);
			notice.setHead(title);
			notice.setContent(sellContent);
			notice.setLink(sellUrl);
			notice.setImageLink("/images/Mainicon.jpg");
			noticeJedisDAO.insert(notice, sellMbrId);
/////////////////////////////////////redis測試////////////////////	

		}
		
	}
}
