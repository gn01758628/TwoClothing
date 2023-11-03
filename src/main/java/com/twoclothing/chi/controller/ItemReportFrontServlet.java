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

import com.twoclothing.chi.service.ItemReportService;
import com.twoclothing.chi.service.ItemReportServiceImpl;
import com.twoclothing.model.aproduct.itemreport.ItemReport;

@WebServlet("/front/itemreport")
public class ItemReportFrontServlet extends HttpServlet {
	private ItemReportService itemReportService;

	@Override
	public void init() throws ServletException {
		itemReportService = new ItemReportServiceImpl();
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
			url = addItemReport(req, res);
			break;
		default:
			url = "/front_end/itemreport/itemReportList.jsp";
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

		List<ItemReport> itemReportList = itemReportService.getAllByMbrId(mbrId, currentPage);

		int itemReportPageQty = itemReportService.getPageTotal(mbrId);
		req.getSession().setAttribute("itemReportPageQty", itemReportPageQty);

		Map<Integer, String> rStatusMap = new HashMap<>();
		rStatusMap.put(0, "待審核");
		rStatusMap.put(1, "已審核");

		Map<Integer, String> resultMap = new HashMap<>();
		resultMap.put(0, "處分");
		resultMap.put(1, "不處分");

		ItemReport itemReport = new ItemReport();
		String note = itemReport.getNote();
		if (note == null) {
			note = "";
		}

		req.setAttribute("itemReportList", itemReportList);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("rStatusMap", rStatusMap);
		req.setAttribute("resultMap", resultMap);
		req.setAttribute("note", note);

		return "/front_end/itemreport/itemReportList.jsp";
	}

	private String addItemReport(HttpServletRequest req, HttpServletResponse res) {
		String itemIdString = req.getParameter("itemId");
		int itemId = Integer.parseInt(itemIdString);
		String mbrIdString = req.getParameter("mbrId");
		int mbrId = Integer.parseInt(mbrIdString);
		String description = req.getParameter("description");

		List<String> errorMsgs = new LinkedList<String>();

		if (description == null || description.trim().isEmpty()) {
			errorMsgs.add("請填寫檢舉原因");
		}

		if (!errorMsgs.isEmpty()) {
			req.setAttribute("errorMsgs", errorMsgs);
		}

		ItemReport itemReport = new ItemReport();
		itemReport.setItemId(itemId);
		itemReport.setMbrId(mbrId);
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		itemReport.setReportDate(currentTime);
		itemReport.setDescription(description);
		itemReport.setrStatus(0);

		req.setAttribute("itemReport", itemReportService.addItemReport(itemReport));

		return "/front/itemreport?action=getAllByMbrId";
	}
}
