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

@WebServlet("/itemtrackinglist")
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
		case "getAllByMbrId":
			req.setAttribute("mbrId", mbrId);
			url = getAllByMbrId(req, res);
			break;
		case "insert":
			url = addItemTracking(req, res);
			break;
		case "delete":
			url = deleteItemTracking(req, res);
			break;
		default:
			url = "/front_end/itemtracking/itemTrackingList.jsp";
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
	}

	private String getAllByMbrId(HttpServletRequest req, HttpServletResponse res) {
//		String mbrIdString = req.getParameter("mbrId");
//		int mbrId = Integer.parseInt(mbrIdString);
		int mbrId = 103; // 測試用，到時這行可刪
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		List<ItemTracking> itemTrackingList = itemTrackingService.getAllByMbrId(mbrId, currentPage);

		int itemTrackingPageQty = itemTrackingService.getPageTotal(mbrId);
		req.getSession().setAttribute("itemTrackingPageQty", itemTrackingPageQty);

		req.setAttribute("itemTrackingList", itemTrackingList);
		req.setAttribute("currentPage", currentPage);

		return "/front_end/itemtracking/itemTrackingList.jsp";
	}

	private String addItemTracking(HttpServletRequest req, HttpServletResponse res) {
		String itemIdString = req.getParameter("itemId");
		int itemId = Integer.parseInt(itemIdString);
		String mbrIdString = req.getParameter("mbrId");
		int mbrId = Integer.parseInt(mbrIdString);

		ItemTracking itemTracking = new ItemTracking();
		itemTracking.setCompositeKey(new ItemTracking.CompositeDetail(itemId, mbrId));
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		itemTracking.setTrackingTime(currentTime);

		req.setAttribute("itemTracking", itemTrackingService.addItemTracking(itemTracking));

		return "/itemtrackinglist?action=getAllByMbrId";
	}

	private String deleteItemTracking(HttpServletRequest req, HttpServletResponse res) {
		String itemIdString = req.getParameter("itemId");
		int itemId = Integer.parseInt(itemIdString);
		String mbrIdString = req.getParameter("mbrId");
		int mbrId = Integer.parseInt(mbrIdString);
		List<String> errorMsgs = new LinkedList<String>();

		if (itemTrackingService.deleteItemTracking(itemId, mbrId) != 1) {
			errorMsgs.add("刪除失敗");
		}

		return "/itemtrackinglist?action=getAllByMbrId";
	}
}
