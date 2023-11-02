package com.twoclothing.gordon.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import redis.clients.jedis.Jedis;
import com.google.gson.Gson;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.twoclothing.model.abid.bidorder.BidOrder;
import com.twoclothing.model.abid.bidordernotify.BidOrderNotify;
import com.twoclothing.gordon.service.BidOrderNotifyServiceImpl;
import com.twoclothing.gordon.service.BidOrderServiceImpl;

@WebServlet("/bidordernotify/BidOrderNotify.do")
public class BidOrderNotifyServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		BidOrderNotifyServiceImpl bidOrderNotifyServiceImpl = new BidOrderNotifyServiceImpl();
		BidOrderServiceImpl bidOrderServiceImpl = new BidOrderServiceImpl();
		BidOrder bidOrderInput = (BidOrder) req.getAttribute("BidOrder");
		Date currentDate = new Date();

		String action = req.getParameter("action");
		String bidOrderAction = (String) req.getAttribute("BidOrderAction");

		if ("addBidOrder_so_AddNotify".equals(bidOrderAction)) {

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
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
				
				String title = "訂單已成立";
				
				String sellContent = "等待買家付款";
				
				String buyContent = "請完成訂單付款";
				/***************************2.開始新增資料***************************************/
				//買家
				bidOrderNotifyServiceImpl.addBidOrderNotify(buyMbrId, bidOrderId, notifyDate, title, buyContent);
				//賣家
				bidOrderNotifyServiceImpl.addBidOrderNotify(sellMbrId, bidOrderId, notifyDate, title, sellContent);
		/////////////////////////////////////redis測試////////////////////	
				BidOrderNotify sellBidOrderNotify = new BidOrderNotify();
				BidOrderNotify buyBidOrderNotify = new BidOrderNotify();
				
				sellBidOrderNotify.setMbrId(sellMbrId);
				sellBidOrderNotify.setBidOrderId(bidOrderId);
				sellBidOrderNotify.setNotifyDate(notifyDate);
				sellBidOrderNotify.setTitle(title);
				sellBidOrderNotify.setContent(sellContent);
				
				buyBidOrderNotify.setMbrId(buyMbrId);
				buyBidOrderNotify.setBidOrderId(bidOrderId);
				buyBidOrderNotify.setNotifyDate(notifyDate);
				buyBidOrderNotify.setTitle(title);
				buyBidOrderNotify.setContent(buyContent);
				
				
				Jedis jedis = new Jedis("localhost", 6379);
				
				jedis.select(0);
				
				Gson gson = new Gson();
				    String sellValue = gson.toJson(sellBidOrderNotify);
				    String buyValue = gson.toJson(buyBidOrderNotify);

				    String buyKey =  String.valueOf(buyMbrId);
				    String sellKey =  String.valueOf(sellMbrId);

				// 存储数据
				jedis.set(buyKey, sellValue);
				

				
				// 存储数据
				jedis.set(sellKey, buyValue);
				
				
				jedis.close();
/////////////////////////////////////redis測試////////////////////	
			
			}
			/***************************去會員中心***************************************/

			String url = "/MemberCentre.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
		


		}
	}
}
