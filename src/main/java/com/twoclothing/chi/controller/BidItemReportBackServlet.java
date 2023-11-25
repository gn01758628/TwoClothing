package com.twoclothing.chi.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
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
import com.twoclothing.chenghan.service.BidItemService;
import com.twoclothing.chenghan.service.BidItemServiceImpl;
import com.twoclothing.chi.service.BidItemReportService;
import com.twoclothing.chi.service.BidItemReportServiceImpl;
import com.twoclothing.gordon.service.MembersService;
import com.twoclothing.gordon.service.MembersServiceImpl;
import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditemreport.BidItemReport;
import com.twoclothing.model.members.Members;
import com.twoclothing.redismodel.notice.Notice;

@WebServlet("/back/biditemreport")
@MultipartConfig
public class BidItemReportBackServlet extends HttpServlet {
	private BidItemReportService bidItemReportService;

	private BidItemService bidItemService;

	private MembersService membersService;

	@Override
	public void init() throws ServletException {
		bidItemReportService = new BidItemReportServiceImpl();

		bidItemService = new BidItemServiceImpl();

		membersService = new MembersServiceImpl();
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
			url = getCompositeQuery(req, res);
			break;
		case "getOne":
			req.getParameter("reportId");
			getBidItemReport(req, res);
			return;
		case "update":
			updateBidItemReport(req, res);
			return;
		default:
			url = "/back_end/biditemreport/bidItemReportManageIndex.jsp";
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
	}

	private String getAll(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		Integer mbrId = (Integer) session.getAttribute("mbrId");
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		List<BidItemReport> bidItemReportList = bidItemReportService.getAll(currentPage);

		int bidItemReportPageQty = bidItemReportService.getPageTotal(mbrId);
		req.getSession().setAttribute("bidItemReportPageQty", bidItemReportPageQty);

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
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("statusMap", statusMap);
		req.setAttribute("resultMap", resultMap);

		return "/back_end/biditemreport/bidItemReportManageIndex.jsp";
	}

	private String getCompositeQuery(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String[]> map = req.getParameterMap();
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		List<String> errorMsgs = new LinkedList<String>();

		Map<String, Object> convertedMap = new HashMap<>();
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			String key = entry.getKey();
			String[] values = entry.getValue();

			if (values.length == 1) {
				convertedMap.put(key, values[0]);
			} else {
				convertedMap.put(key, Arrays.asList(values));
			}
		}
		req.setAttribute("convertedMap", convertedMap);

		if (map != null) {
			List<BidItemReport> bidItemReportList = bidItemReportService.getByCompositeQuery(map, currentPage);

			if (bidItemReportList.size() == 0) {
				errorMsgs.add("查無資料");
			}

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("errorMsgs", errorMsgs);
			}

			int bidItemReportPageQty = bidItemReportService.getCompositeQueryPageTotal(map);
			req.getSession().setAttribute("bidItemReportPageQty", bidItemReportPageQty);

			Map<Integer, String> statusMap = new HashMap<>();
			statusMap.put(0, "待審核");
			statusMap.put(1, "已審核");

			Map<Integer, String> resultMap = new HashMap<>();
			resultMap.put(0, "處分");
			resultMap.put(1, "不處分");

			Map<Integer, String> bidItemNameMap = new HashMap<>();
			for (BidItemReport bidItemReport : bidItemReportList) {
				Integer bidItemId = bidItemReport.getBidItemId();
				BidItem bidItem = bidItemService.getBidItemByBidItemId(bidItemId);
				String bidItemName = (bidItem != null) ? bidItem.getBidName() : "未知商品";
				bidItemNameMap.put(bidItemId, bidItemName);
			}
			req.setAttribute("bidItemNameMap", bidItemNameMap);

			req.setAttribute("bidItemReportList", bidItemReportList);
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("statusMap", statusMap);
			req.setAttribute("resultMap", resultMap);
		}

		return "/back_end/biditemreport/bidItemReportManageIndex.jsp";
	}

	private void getBidItemReport(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Integer reportId = Integer.parseInt(req.getParameter("reportId"));
		BidItemReport bidItemReport = bidItemReportService.getByPrimaryKey(reportId);
		setJsonResponse(res, bidItemReport);
	}

	private void updateBidItemReport(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Integer reportId = Integer.parseInt(req.getParameter("reportId"));
		BidItemReport bidItemReport = bidItemReportService.getByPrimaryKey(reportId);

		int bidItemId = Integer.parseInt(req.getParameter("bidItemId"));
		int mbrId = Integer.parseInt(req.getParameter("mbrId"));
		HttpSession session = req.getSession();
		Integer empId = (Integer) session.getAttribute("empId");
		int result = Integer.parseInt(req.getParameter("result"));
		String note = req.getParameter("note");

		if (result == 0 && (note == null || note.trim().isEmpty())) {
			note = "檢舉成功，商品已被移除";
		} else if (result == 1 && (note == null || note.trim().isEmpty())) {
			note = "";
		}

		bidItemReport.setEmpId(empId);
		bidItemReport.setBidStatus(1);
		Timestamp auditdate = new Timestamp(System.currentTimeMillis());
		bidItemReport.setAuditDate(auditdate);
		bidItemReport.setResult(result);
		bidItemReport.setNote(note);

		bidItemReportService.updateBidItemReport(bidItemReport);

		Notice notice = new Notice();
		notice.setType("檢舉審核結果");
		notice.setHead("請確認商品檢舉審核結果");

		BidItem bidItem = bidItemService.getBidItemByBidItemId(bidItemId);
		int sellMbr = bidItem.getMbrId();
		Members members = membersService.getByPrimaryKey(sellMbr);
		int sellScore = members.getSellScore();

		Notice noticeBidItemDelete = new Notice();
		noticeBidItemDelete.setType("檢舉審核結果");
		noticeBidItemDelete.setHead("請確認商品檢舉審核結果");
		noticeBidItemDelete.setContent("商品「" + bidItem.getBidName() + "」，檢舉審核為「處分」結果，已強制移除。");
		noticeBidItemDelete.setLink("#");
		noticeBidItemDelete.setImageLink("/ReadItemIMG/biditem?id=" + bidItemId + "&position=1");

		if (result == 0) {
			notice.setContent("商品檢舉審核為「處分」結果，請至「我的檢舉」查看。");
			notice.setLink("/front/biditemreport?action=getAllByMbrId");
			notice.setImageLink("/images/report0.png");
			bidItemReportService.addNotice(notice, mbrId);
			bidItem.setBidStatus(6);
			members.setSellScore(sellScore - 2);
			bidItemReportService.addNotice(noticeBidItemDelete, sellMbr);
		} else if (result == 1) {
			notice.setContent("商品檢舉審核為「不處分」結果，請至「我的檢舉」查看。");
			notice.setLink("/front/biditemreport?action=getAllByMbrId");
			notice.setImageLink("/images/report1.png");
			bidItemReportService.addNotice(notice, mbrId);
		}

		setJsonResponse(res, bidItemReport);
	}

	private void setJsonResponse(HttpServletResponse res, Object obj) throws IOException {
		Gson gson = new Gson();
		String jsonData = gson.toJson(obj);
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jsonData);
	}
}
