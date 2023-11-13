package com.twoclothing.huiwen.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.huiwen.service.BalanceHistoryService;
import com.twoclothing.huiwen.service.BalanceHistoryServiceImpl;
import com.twoclothing.model.balancehistory.BalanceHistory;


@WebServlet("/BalanceHistory/*")
public class BalanceHistoryServlet extends HttpServlet{
	private BalanceHistoryService BHSvc;

	public void init() throws ServletException {
		BHSvc = new BalanceHistoryServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String choice = req.getParameter("choice");
		//連去新增
		if("add".equals(choice)) {
			String url = "/back_end/balanceHistory/BHAdd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		//連去搜尋
		if("search".equals(choice)) {
			List<BalanceHistory> balanceHistoryList = BHSvc.getAllBH();
			req.setAttribute("BHList", balanceHistoryList);
			
			String url = "/front_end/balanceHistory/BHSearchList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
		//PK搜尋
		if("searchPK".equals(choice)){
			Integer balanceId = Integer.parseInt(req.getParameter("balanceId"));
			BalanceHistory balanceHistory = BHSvc.getBHById(balanceId);
			req.setAttribute("balanceHistory",balanceHistory);
			
			String url = "/back_end/balanceHistory/listAllBalance.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		
		//會員id搜尋
		if("searchMbrId".equals(choice)){
			Integer mbrId = Integer.parseInt(req.getParameter("mbrId"));
			List<BalanceHistory> balanceHistoryList = BHSvc.getAllBHByMbrId(mbrId);
			req.setAttribute("BHList", balanceHistoryList);
			
			String url = "/back_end/balanceHistory/listAllBalance.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		//查全部
		if("getAll".equals(choice)) {
			List<BalanceHistory> balanceHistoryList = BHSvc.getAllBH();
			req.setAttribute("BHList", balanceHistoryList);
			
			String url = "/back_end/balanceHistory/listAllBalance.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		//新增虛擬錢包異動資訊
		if("AddOne".equals(choice)) {
			BalanceHistory balanceHistory = new BalanceHistory();
			int mbrId =Integer.parseInt(req.getParameter("mbrId"));
			if(!req.getParameter("orderId").trim().isEmpty() ) {
				int orderId =Integer.parseInt(req.getParameter("orderId"));
			}
			if(req.getParameter("bidOrderId").length()!=0) {
				int bidOrderId =Integer.parseInt(req.getParameter("bidOrderId"));
			}
			if(req.getParameter("wrId").length()!=0) {
				int wrId =Integer.parseInt(req.getParameter("wrId"));			
			}
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			int changeValue =Integer.parseInt(req.getParameter("changeValue"));
			
			balanceHistory.setMbrId(1);//從登入資訊取
			balanceHistory.setOrderId(1);//從訂單取
//			balanceHistory.setBidOrderId(1);//與一般訂單2選1
			balanceHistory.setWrId(1);//從提款編號取，不一定有
			balanceHistory.setChangeDate(currentTime);			
			balanceHistory.setChangeValue(changeValue);
			
			int balanceHistoryPK = BHSvc.addBH(balanceHistory);
			System.out.println(balanceHistory);
			
			req.setAttribute("balanceHistory", balanceHistory);
			String url = "/back_end/balanceHistory/listAllBalance.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}

		
	}
}
