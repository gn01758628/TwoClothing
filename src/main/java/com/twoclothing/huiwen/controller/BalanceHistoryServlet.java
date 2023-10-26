package com.twoclothing.huiwen.controller;

import java.io.IOException;
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
		System.out.println("找到servlet");
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String choice = req.getParameter("choice");
		
		if("add".equals(choice)) {
			String url = "/back_end/balanceHistory/.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}

		if("search".equals(choice)) {
			String url = "/back_end/balanceHistory/BHSearch.jsp";
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
			System.out.println("1111111");
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

		
	}
}
