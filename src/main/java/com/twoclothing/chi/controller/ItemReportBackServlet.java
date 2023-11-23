package com.twoclothing.chi.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.twoclothing.chi.service.ItemReportService;
import com.twoclothing.chi.service.ItemReportServiceImpl;
import com.twoclothing.model.aproduct.itemreport.ItemReport;
import com.twoclothing.redismodel.notice.Notice;

@WebServlet("/back/itemreport")
@MultipartConfig
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
		
		Enumeration<String> names = req.getParameterNames();
		while (names.hasMoreElements()) {
			System.out.println(names.nextElement());
		}

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
			req.getParameter("reportId");
			getItemReport(req, res);
			return;
		case "update":
			updateItemReport(req, res);
			return;
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

	private void getItemReport(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Integer reportId = Integer.parseInt(req.getParameter("reportId"));
		ItemReport itemReport = itemReportService.getByPrimaryKey(reportId);

//		Map<Integer, String> rStatusMap = new HashMap<>();
//		rStatusMap.put(0, "待審核");
//		rStatusMap.put(1, "已審核");
//
//		req.setAttribute("itemReport", itemReport);
//		req.setAttribute("rStatusMap", rStatusMap);

		setJsonResponse(res, itemReport);

//		return "/back_end/itemreport/itemReportManageUpdate.jsp";
	}

	private void updateItemReport(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Integer reportId = Integer.parseInt(req.getParameter("reportId"));
		ItemReport itemReport = itemReportService.getByPrimaryKey(reportId);

		HttpSession session = req.getSession();
		Integer empId = (Integer) session.getAttribute("empId");
		int result = Integer.parseInt(req.getParameter("result"));
		String note = req.getParameter("note");

//		List<String> errorMsgs = new LinkedList<String>();

//		if (result == -1) {
//			errorMsgs.add("請進行處分");
//		} else {
		itemReport.setEmpId(empId);
		itemReport.setrStatus(1);
		Timestamp auditdate = new Timestamp(System.currentTimeMillis());
		itemReport.setAuditDate(auditdate);
		itemReport.setResult(result);
		itemReport.setNote(note);

		itemReportService.updateItemReport(itemReport);

		Notice notice = new Notice();
		notice.setType("檢舉審核結果");
		notice.setHead("請確認商品檢舉審核結果");

		if (result == 0) {
			notice.setContent("商品檢舉審核為「處分」結果，請至「我的檢舉」查看。");
			notice.setImageLink("/images/report0.png");
		} else if (result == 1) {
			notice.setContent("商品檢舉審核為「不處分」結果，請至「我的檢舉」查看。");
			notice.setImageLink("/images/report1.png");
		}

		notice.setLink("/front/itemreport?action=getAllByMbrId&mbrId=${mbrId}"); // 到時加上連結至(會員前台)我的檢舉
		itemReportService.addNotice(notice, itemReport.getMbrId());
//		}

//		if (!errorMsgs.isEmpty()) {
//			req.setAttribute("errorMsgs", errorMsgs);
//			return "/back/itemreport?action=getOne";
//		}

//		return "/back/itemreport?action=getAll";

		setJsonResponse(res, itemReport);
	}

	private void setJsonResponse(HttpServletResponse res, Object obj) throws IOException {
		Gson gson = new Gson();
		String jsonData = gson.toJson(obj);
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jsonData);
	}
}
