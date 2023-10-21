package com.twoclothing.chi.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.chi.service.ItemTrackingService;
import com.twoclothing.chi.service.ItemTrackingServiceImpl;
import com.twoclothing.model.aproduct.itemtracking.ItemTracking;

@WebServlet("/itemtracking")
public class ItemTrackingServlet extends HttpServlet {
	private ItemTrackingService itemTrackingService;

	@Override
	public void init() throws ServletException {
		itemTrackingService = new ItemTrackingServiceImpl();
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
		case "getAll":
			req.setAttribute("action", action);
			url = getAllItemTracking(req, res);
			break;
		case "getAllByMbrId":
			req.setAttribute("action", action);
			req.setAttribute("mbrId", mbrId);
			url = getAllByMbrId(req, res);
			break;
		case "getByPK":
			url = getByPrimaryKey(req, res);
			break;
		case "insert":
			url = addItemTracking(req, res);
			break;
		case "delete":
			url = deleteItemTracking(req, res);
			break;
		default:
			url = "/front_end/itemtracking/itemTrackingIndex.jsp";
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
	}

	private String getAllItemTracking(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		List<ItemTracking> itemTrackingList = itemTrackingService.getAllItemTracking(currentPage);

		if (req.getSession().getAttribute("itemTrackingPageQty") == null) {
			int itemTrackingPageQty = itemTrackingService.getPageTotal();
			req.getSession().setAttribute("itemTrackingPageQty", itemTrackingPageQty);
		}

		req.setAttribute("itemTrackingList", itemTrackingList);
		req.setAttribute("currentPage", currentPage);
		
		return "/front_end/itemtracking/itemTrackingAll.jsp";
	}

	private String getAllByMbrId(HttpServletRequest req, HttpServletResponse res) {
		String mbrIdString = req.getParameter("mbrId");
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		List<String> errorMsgs = new LinkedList<String>();
		if (mbrIdString == null || mbrIdString.trim().isEmpty()) {
			errorMsgs.add("會員編號未填寫");
		} else {
			try {
				Integer.parseInt(mbrIdString);
			} catch (NumberFormatException e) {
				errorMsgs.add("會員編號應填數字");
			}
		}
		
		List<ItemTracking> itemTrackingList = itemTrackingService.getAllByMbrId(Integer.valueOf(mbrIdString), currentPage);
		
		if (itemTrackingList.size() == 0) {
			errorMsgs.add("查無資料");
		}

		if (!errorMsgs.isEmpty()) {
			req.setAttribute("errorMsgs", errorMsgs);
			return "/front_end/itemtracking/itemTrackingIndex.jsp";
		}
		
		req.setAttribute("itemTrackingList", itemTrackingList);
		req.setAttribute("currentPage", currentPage);
		
		return "/front_end/itemtracking/itemTrackingAll.jsp";
	}

	private String getByPrimaryKey(HttpServletRequest req, HttpServletResponse res) {
		List<String> errorMsgs = checkinEerror(req, res);
		String itemIdString = req.getParameter("itemId");
		String mbrIdString = req.getParameter("mbrId");
		ItemTracking itemTracking = new ItemTracking();
		itemTracking = itemTrackingService.getByPrimaryKey(Integer.valueOf(itemIdString), Integer.valueOf(mbrIdString));
		
		if (itemTracking == null) {
			errorMsgs.add("查無資料");
		}
		
		if (!errorMsgs.isEmpty()) {
			return "/front_end/itemtracking/itemTrackingIndex.jsp";
		}
		
		req.setAttribute("itemTracking", itemTracking);
		
		return "/front_end/itemtracking/itemTracking.jsp";
	}

	private String addItemTracking(HttpServletRequest req, HttpServletResponse res) {
		List<String> errorMsgs = checkinEerror(req, res);
		if (!errorMsgs.isEmpty()) {
			return "/front_end/itemtracking/itemTrackingIndex.jsp";
		}

		String itemIdString = req.getParameter("itemId");
		String mbrIdString = req.getParameter("mbrId");
		ItemTracking itemTracking = new ItemTracking();
		itemTracking.setCompositeKey(new ItemTracking.CompositeDetail(Integer.valueOf(itemIdString), Integer.valueOf(mbrIdString)));
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		itemTracking.setTrackingTime(currentTime);
		req.setAttribute("itemTracking", itemTrackingService.addItemTracking(itemTracking));
		
		return "/front_end/itemtracking/itemTracking.jsp";
	}

	private String deleteItemTracking(HttpServletRequest req, HttpServletResponse res) {
		List<String> errorMsgs = checkinEerror(req, res);
		if (!errorMsgs.isEmpty()) {
			return "/front_end/itemtracking/itemTrackingIndex.jsp";
		}

		String itemIdString = req.getParameter("itemId");
		String mbrIdString = req.getParameter("mbrId");
		if (itemTrackingService.deleteItemTracking(Integer.valueOf(itemIdString), Integer.valueOf(mbrIdString)) == 1) {
			return "/front_end/itemtracking/itemTrackingDeleteSuccess.jsp";
		}
		
		return "/front_end/itemtracking/itemTrackingIndex.jsp";
	}

	List<String> checkinEerror(HttpServletRequest req, HttpServletResponse res) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		String itemIdString = req.getParameter("itemId");
		String mbrIdString = req.getParameter("mbrId");

		if (itemIdString == null || itemIdString.trim().isEmpty()) {
			errorMsgs.add("商品編號未填寫");
		} else {
			try {
				Integer.parseInt(itemIdString);
			} catch (NumberFormatException e) {
				errorMsgs.add("商品編號應填數字");
			}
		}

		if (mbrIdString == null || mbrIdString.trim().isEmpty()) {
			errorMsgs.add("會員編號未填寫");
		} else {
			try {
				Integer.parseInt(mbrIdString);
			} catch (NumberFormatException e) {
				errorMsgs.add("會員編號應填數字");
			}
		}

		return errorMsgs;
	}
}
