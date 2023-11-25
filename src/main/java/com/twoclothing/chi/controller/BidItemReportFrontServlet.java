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
import javax.servlet.http.HttpSession;

import com.twoclothing.chenghan.service.BidItemService;
import com.twoclothing.chenghan.service.BidItemServiceImpl;
import com.twoclothing.chi.service.BidItemReportService;
import com.twoclothing.chi.service.BidItemReportServiceImpl;
import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditemreport.BidItemReport;

@WebServlet("/front/biditemreport")
public class BidItemReportFrontServlet extends HttpServlet {
	private BidItemReportService bidItemReportService;
	
	private BidItemService bidItemService;

	@Override
	public void init() throws ServletException {
		bidItemReportService = new BidItemReportServiceImpl();
		
		bidItemService = new BidItemServiceImpl();
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
			addBidItemReport(req, res);
			return;
		default:
			url = "/front_end/bidIitemreport/bidItemReportList.jsp";
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
	}

	private String getAllByMbrId(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		Integer mbrId = (Integer) session.getAttribute("mbrId");

		List<BidItemReport> bidItemReportList = bidItemReportService.getAllByMbrId(mbrId);

		Map<Integer, String> bidItemNameMap = new HashMap<>();
		for (BidItemReport bidItemReport : bidItemReportList) {
			Integer bidItemId = bidItemReport.getBidItemId();
	        BidItem bidItem = bidItemService.getBidItemByBidItemId(bidItemId);
	        String bidItemName = (bidItem != null) ? bidItem.getBidName() : "未知商品";
	        bidItemNameMap.put(bidItemId, bidItemName);
	    }
		req.setAttribute("bidItemNameMap", bidItemNameMap);

		Map<Integer, String> statusMap = new HashMap<>();
		statusMap.put(0, "待審核");
		statusMap.put(1, "已審核");

		Map<Integer, String> resultMap = new HashMap<>();
		resultMap.put(0, "處分");
		resultMap.put(1, "不處分");

		req.setAttribute("bidItemReportList", bidItemReportList);
		req.setAttribute("statusMap", statusMap);
		req.setAttribute("resultMap", resultMap);

		return "/front_end/biditemreport/bidItemReportList.jsp";
	}

	private void addBidItemReport(HttpServletRequest req, HttpServletResponse res) {
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
	}
}
