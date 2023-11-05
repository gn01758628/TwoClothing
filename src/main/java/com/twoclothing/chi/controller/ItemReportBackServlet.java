package com.twoclothing.chi.controller;

import java.io.IOException;
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
import com.twoclothing.model.employee.Employee;
import com.twoclothing.tonyhsieh.service.EmployeeService;
import com.twoclothing.tonyhsieh.service.EmployeeServiceImpl;

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

//			getSelectInfo(req, res);

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
		} else {
			System.out.println("map.sizes() == 0");
		}
		
		return "/back_end/itemreport/itemReportManageList.jsp";
	}

//	private void getSelectInfo(HttpServletRequest req, HttpServletResponse res) {
//		EmployeeService employeeService = new EmployeeServiceImpl();
//		List<Employee> allEmployee = employeeService.getAll();
//		req.setAttribute("empId", allEmployee);
//	}
}
