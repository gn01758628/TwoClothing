package com.twoclothing.chi.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.chi.service.BidItemReportService;
import com.twoclothing.chi.service.BidItemReportServiceImpl;
import com.twoclothing.model.abid.biditemreport.BidItemReport;

@WebServlet("/front/biditemreport")
public class BidItemReportFrontServlet extends HttpServlet {
	private BidItemReportService bidItemReportService;

	@Override
	public void init() throws ServletException {
		bidItemReportService = new BidItemReportServiceImpl();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String mbrId = req.getParameter("mbrId");
		String url = "";

		switch (action) {
		case "getAllByMbrId":
			req.setAttribute("mbrId", mbrId);
			url = getAllByMbrId(req, res);
			break;
		case "insert":
			url = addBidItemReport(req, res);
			break;
		default:
			url = "/front_end/bidIitemreport/bidItemReportList.jsp";
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
	}

	private String getAllByMbrId(HttpServletRequest req, HttpServletResponse res) {
//		String mbrIdString = req.getParameter("mbrId");
//		int mbrId = Integer.parseInt(mbrIdString);
		int mbrId = 1; // 測試用，到時這行可刪
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		List<BidItemReport> bidItemReportList = bidItemReportService.getAllByMbrId(mbrId, currentPage);

		int bidItemReportPageQty = bidItemReportService.getPageTotal(mbrId);
		req.getSession().setAttribute("bidItemReportPageQty", bidItemReportPageQty);

		Map<Integer, String> rStatusMap = new HashMap<>();
		rStatusMap.put(0, "待審核");
		rStatusMap.put(1, "已審核");

		Map<Integer, String> resultMap = new HashMap<>();
		resultMap.put(0, "處分");
		resultMap.put(1, "不處分");

		BidItemReport bidItemReport = new BidItemReport();
		String note = bidItemReport.getNote();
		if (note == null) {
			note = "";
		}

		req.setAttribute("bidItemReportList", bidItemReportList);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("rStatusMap", rStatusMap);
		req.setAttribute("resultMap", resultMap);
		req.setAttribute("note", note);

		return "/front_end/bidItemreport/bidItemReportList.jsp";
	}

	private String addBidItemReport(HttpServletRequest req, HttpServletResponse res) {
		String bidItemId = req.getParameter("bidItemId");
		String mbrId = req.getParameter("mbrId");
		String description = req.getParameter("description");

		List<String> errorMsgs = new LinkedList<String>();

		if (description == null || description.trim().isEmpty()) {
			errorMsgs.add("請填寫檢舉原因");
		}

		if (!errorMsgs.isEmpty()) {
			req.setAttribute("errorMsgs", errorMsgs);
		}

		BidItemReport bidItemReport = new BidItemReport();
		bidItemReport.setBidItemId(Integer.parseInt(bidItemId));
		bidItemReport.setMbrId(Integer.parseInt(mbrId));
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		bidItemReport.setReportDate(currentTime);
		bidItemReport.setBidDescription(description);
		bidItemReport.setBidStatus(0);

		bidItemReportService.addBidItemReport(bidItemReport);

		return "/front/biditemreport?action=getAllByMbrId";
	}
}
