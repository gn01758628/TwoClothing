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
import com.twoclothing.chi.service.ItemReportService;
import com.twoclothing.chi.service.ItemReportServiceImpl;
import com.twoclothing.gordon.service.MembersService;
import com.twoclothing.gordon.service.MembersServiceImpl;
import com.twoclothing.huiwen.service.ItemService;
import com.twoclothing.huiwen.service.ItemServiceImpl;
import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.aproduct.itemreport.ItemReport;
import com.twoclothing.model.members.Members;
import com.twoclothing.redismodel.notice.Notice;

@WebServlet("/back/itemreport")
@MultipartConfig
public class ItemReportBackServlet extends HttpServlet {
	private ItemReportService itemReportService;
	
	private ItemService itemService;
	
	private MembersService membersService;

	@Override
	public void init() throws ServletException {
		itemReportService = new ItemReportServiceImpl();
		
		itemService = new ItemServiceImpl();
		
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
		HttpSession session = req.getSession();
		Integer empId = (Integer) session.getAttribute("empId");
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		List<ItemReport> itemReportList = itemReportService.getAll(currentPage);

		int itemReportPageQty = itemReportService.getPageTotal(empId);
		req.getSession().setAttribute("itemReportPageQty", itemReportPageQty);
		
		Map<Integer, String> itemNameMap = new HashMap<>();
		for (ItemReport itemReport : itemReportList) {
			Integer itemId = itemReport.getItemId();
	        Item item = itemService.getItemByItemId(itemId);
	        String itemName = (item != null) ? item.getItemName() : "未知商品";
	        itemNameMap.put(itemId, itemName);
	    }
		req.setAttribute("itemNameMap", itemNameMap);

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

		return "/back_end/itemreport/itemReportManageIndex.jsp";
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
			
			Map<Integer, String> itemNameMap = new HashMap<>();
			for (ItemReport itemReport : itemReportList) {
				Integer itemId = itemReport.getItemId();
		        Item item = itemService.getItemByItemId(itemId);
		        String itemName = (item != null) ? item.getItemName() : "未知商品";
		        itemNameMap.put(itemId, itemName);
		    }
			req.setAttribute("itemNameMap", itemNameMap);

			req.setAttribute("itemReportList", itemReportList);
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("rStatusMap", rStatusMap);
			req.setAttribute("resultMap", resultMap);
		}

		return "/back_end/itemreport/itemReportManageIndex.jsp";
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

		int itemId = Integer.parseInt(req.getParameter("itemId"));
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
		notice.setType("檢舉編號：" + reportId + " 審核結果");
		notice.setHead("請確認商品檢舉審核結果");
		
		Item item = itemService.getItemByItemId(itemId);
		int sellMbr = item.getMbrId();
		Members members = membersService.getByPrimaryKey(sellMbr);
		int sellScore =  members.getSellScore();
		
		Notice noticeItemDelete = new Notice();
		noticeItemDelete.setType("檢舉審核結果");
		noticeItemDelete.setHead("請確認商品檢舉審核結果");
		noticeItemDelete.setContent("商品「" + item.getItemName() + "」，檢舉審核為「處分」結果，已強制移除。");
		noticeItemDelete.setLink("#");
		noticeItemDelete.setImageLink("/ReadItemIMG/item?id=" + itemId + "&position=1");

		if (result == 0) {
			notice.setContent("審核為「處分」結果，請至「我的檢舉」查看。");
			notice.setLink("/front/itemreport?action=getAllByMbrId");
			notice.setImageLink("/images/report0.png");
			itemReportService.addNotice(notice, mbrId);
			item.setItemStatus(2);
			
			List<ItemReport> itemReportAll = itemReportService.getAll();
			for (ItemReport itemReportBoth : itemReportAll) {
			    if (itemReportBoth.getItemId() == itemId && itemReportBoth.getrStatus() == 0) {
			        itemReportBoth.setResult(0);
			        itemReportBoth.setEmpId(empId);
			        itemReportBoth.setrStatus(1);
			        itemReportBoth.setAuditDate(auditdate);
			        itemReportBoth.setNote(note);
			        
			        int bothMbrId = itemReportBoth.getMbrId();
			        int reportBothId = itemReportBoth.getReportId();
			        
			        if (bothMbrId != mbrId) {
			        	Notice noticeBothDelete = new Notice();
			        	noticeBothDelete.setType("檢舉編號：" + reportBothId + " 審核結果");
			        	noticeBothDelete.setHead("請確認商品檢舉審核結果");
			        	noticeBothDelete.setContent("審核為「處分」結果，請至「我的檢舉」查看。");
			        	noticeBothDelete.setLink("/front/itemreport?action=getAllByMbrId");
			        	noticeBothDelete.setImageLink("/images/report0.png");
			        	itemReportService.addNotice(noticeBothDelete, bothMbrId);
			        }
			    }
			}
			
			members.setSellScore(sellScore - 2);
			itemReportService.addNotice(noticeItemDelete, sellMbr);
		} else if (result == 1) {
			notice.setContent("審核為「不處分」結果，請至「我的檢舉」查看。");
			notice.setLink("/front/itemreport?action=getAllByMbrId");
			notice.setImageLink("/images/report1.png");
			itemReportService.addNotice(notice, mbrId);
		}
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
