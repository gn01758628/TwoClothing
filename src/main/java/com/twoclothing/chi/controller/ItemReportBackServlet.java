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

@WebServlet("/back/itemreport")
public class ItemReportBackServlet extends HttpServlet {
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
		String url = "";

		switch (action) {
		case "getAll":
			req.setAttribute("action", action);
			url = getAll(req, res);
			break;
		case "compositeQuery":
			req.setAttribute("action", action);
			req.getParameter("itemId");
			req.getParameter("mbrId");
			req.getParameter("empId");
			req.getParameter("rStatus");
			req.getParameter("result");
			url = getCompositeQuery(req, res);
			break;
		case "getOne":
			url = getItemReport(req, res);
			break;
		case "update":
			url = updateItemReport(req, res);
			break;
		default:
			url = "/back_end/itemreport/itemReportManageIndex.jsp";
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
	}

	private String getAll(HttpServletRequest req, HttpServletResponse res) {
		int mbrId = -1;
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		List<ItemReport> itemReportList = itemReportService.getAll(currentPage);

		int itemReportPageQty = itemReportService.getPageTotal(mbrId);
		req.getSession().setAttribute("itemReportPageQty", itemReportPageQty);

		Map<Integer, String> rStatusMap = new HashMap<>();
		rStatusMap.put(0, "待審核");
		rStatusMap.put(1, "已審核");

		Map<Integer, String> resultMap = new HashMap<>();
		resultMap.put(0, "處分");
		resultMap.put(1, "不處分");
		
		req.setAttribute("itemReportList", itemReportList);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("rStatusMap", rStatusMap);
		req.setAttribute("resultMap", resultMap);

		return "/back_end/itemreport/itemReportManageList.jsp";

	}

	private String getCompositeQuery(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String[]> map = req.getParameterMap();
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		List<String> errorMsgs = new LinkedList<String>();

		if (map != null) {
			List<ItemReport> itemReportList = itemReportService.getByCompositeQuery(map, currentPage);

			if (itemReportList.size() == 0) {
				errorMsgs.add("查無資料");
			}

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("errorMsgs", errorMsgs);
			}

			int itemReportPageQty = itemReportService.getCompositeQueryPageTotal(map);
			req.getSession().setAttribute("itemReportPageQty", itemReportPageQty);

			Map<Integer, String> rStatusMap = new HashMap<>();
			rStatusMap.put(0, "待審核");
			rStatusMap.put(1, "已審核");

			Map<Integer, String> resultMap = new HashMap<>();
			resultMap.put(0, "處分");
			resultMap.put(1, "不處分");

			req.setAttribute("itemReportList", itemReportList);
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("rStatusMap", rStatusMap);
			req.setAttribute("resultMap", resultMap);
		} else {
			System.out.println("map.sizes() == 0");
		}

		return "/back_end/itemreport/itemReportManageList.jsp";
	}

	private String getItemReport(HttpServletRequest req, HttpServletResponse res) {
		Integer reportId = Integer.parseInt(req.getParameter("reportId"));
		
		ItemReport itemReport = itemReportService.getByPrimaryKey(reportId);
		
		Map<Integer, String> rStatusMap = new HashMap<>();
		rStatusMap.put(0, "待審核");
		rStatusMap.put(1, "已審核");
		
		req.setAttribute("itemReport", itemReport);
		req.setAttribute("rStatusMap", rStatusMap);
		
		return "/back_end/itemreport/itemReportManageUpdate.jsp";
	}

	private String updateItemReport(HttpServletRequest req, HttpServletResponse res) {
		String reportIdString = req.getParameter("reportId");
		int reportId = Integer.parseInt(reportIdString);
//		String empIdString = req.getParameter("empId");
//		int empId = Integer.parseInt(empIdString);
		int empId = 1; // 測試用，到時這行可刪
		int rStatus = 1;
		Timestamp auditdate = new Timestamp(System.currentTimeMillis());
		String resultString = req.getParameter("result");
		int result = Integer.parseInt(resultString);
		String note = req.getParameter("note");

		List<String> errorMsgs = new LinkedList<String>();

		if (result == -1) {
			errorMsgs.add("請進行處分");
		}

		if (!errorMsgs.isEmpty()) {
			req.setAttribute("errorMsgs", errorMsgs);
			return "/back/itemreport?action=getOne";
		}

		req.setAttribute("itemReport", itemReportService.updateItemReport(reportId, empId, rStatus, auditdate, result, note));

		return "/back/itemreport?action=getAll";
	}
}
