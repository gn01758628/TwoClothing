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
import javax.servlet.http.HttpSession;

import com.twoclothing.huiwen.service.PointHistoryService;
import com.twoclothing.huiwen.service.PointHistoryServiceImpl;
import com.twoclothing.model.balancehistory.BalanceHistory;
import com.twoclothing.model.pointhistory.PointHistory;

@WebServlet("/PointHistory/*")
public class PointHistoryServlet extends HttpServlet {
	private PointHistoryService PHSvc;

	public void init() throws ServletException {
		PHSvc = new PointHistoryServiceImpl();
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
			String url = "/back_end/pointHistory/PHAdd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		
		//連去搜尋，直接顯示查全部
		if("search".equals(choice)) {
//			List<PointHistory> PHList = PHSvc.getAllPH();
			HttpSession session = req.getSession();
			Integer mbrId = (Integer) session.getAttribute("mbrId");
			List<PointHistory> PHList = PHSvc.getAllPHByMbrId(mbrId);
			req.setAttribute("PHList", PHList);
			
			
			String url = "/front_end/pointHistory/PHSearchList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
        //PK搜尋
		if ("searchPK".equals(choice)) {
			Integer pointId=Integer.parseInt(req.getParameter("pointId"));
			PointHistory pointHistory = PHSvc.getPHById(pointId);
			req.setAttribute("pointHistory", pointHistory);
			System.out.println("pointHistory:"+pointHistory);
			
			String url = "/back_end/pointHistory/listAllPoint.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		//會員id搜尋
		if ("searchMbrId".equals(choice)) {
			HttpSession session = req.getSession();
			Integer mbrId = (Integer) session.getAttribute("mbrId");
			PointHistory pointHistory = PHSvc.getPHById(mbrId);
			req.setAttribute("pointHistory", pointHistory);
			System.out.println("pointHistory:"+pointHistory);
			
			String url = "/back_end/pointHistory/listAllPoint.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
        //查全部
		if ("getAll".equals(choice)) {

			List<PointHistory> PHList = PHSvc.getAllPH();
			req.setAttribute("PHList", PHList);
			System.out.println("PHList:" + PHList);
			
			String url = "/back_end/pointHistory/listAllPoint.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);			
		}
		
		//新增會員點數異動資訊
		if("AddOne".equals(choice)) {
			PointHistory pointHistory = new PointHistory();
			HttpSession session = req.getSession();
			Integer mbrId = (Integer) session.getAttribute("mbrId");
			if(!req.getParameter("orderId").trim().isEmpty() ) {
				int orderId =Integer.parseInt(req.getParameter("orderId"));
			}
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			int changeValue =Integer.parseInt(req.getParameter("changeValue"));
			
			pointHistory.setMbrId(mbrId);//從登入資訊取
			pointHistory.setOrderId(1);//從訂單取
			//異動時間1/訂單完成(+) 2/訂單確認(-)
			pointHistory.setChangeDate(currentTime);			
			pointHistory.setChangeValue(changeValue);
			
			int pointHistoryPK = PHSvc.addPH(pointHistory);
			System.out.println(pointHistory);
			
			req.setAttribute("pointHistory", pointHistory);
			String url = "/back_end/pointHistory/listAllPoint.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}

	}

}
