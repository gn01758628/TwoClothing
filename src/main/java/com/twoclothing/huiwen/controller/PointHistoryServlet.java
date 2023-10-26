package com.twoclothing.huiwen.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.huiwen.service.PointHistoryService;
import com.twoclothing.huiwen.service.PointHistoryServiceImpl;
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
		System.out.println("choice:"+choice);
		if ("getOne_For_Display".equals(choice)) {
			Integer pointId=Integer.parseInt(req.getParameter("pointId"));
			PointHistory pointHistory = PHSvc.getPHById(pointId);

//			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("pointHistory", pointHistory);
			System.out.println("pointHistory:"+pointHistory);
			String url = "/back_end/pointHistory/listOnePoint.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getAll".equals(choice)) {

			List<PointHistory> PHList = PHSvc.getAllPH();
			req.setAttribute("PHList", PHList);
			System.out.println("PHList:" + PHList);
			
			String url = "/back_end/pointHistory/listAllPoint.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}

	}

}
