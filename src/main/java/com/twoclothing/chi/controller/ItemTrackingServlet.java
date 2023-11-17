package com.twoclothing.chi.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.twoclothing.chi.service.ItemTrackingService;
import com.twoclothing.chi.service.ItemTrackingServiceImpl;
import com.twoclothing.huiwen.service.ItemService;
import com.twoclothing.huiwen.service.ItemServiceImpl;
import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.aproduct.itemtracking.ItemTracking;
import com.twoclothing.model.aproduct.itemtracking.ItemTracking.CompositeDetail;

@WebServlet("/itemtrackinglist.check")
public class ItemTrackingServlet extends HttpServlet {
	private ItemTrackingService itemTrackingService;

	private ItemService itemService;

	@Override
	public void init() throws ServletException {
		itemTrackingService = new ItemTrackingServiceImpl();

		itemService = new ItemServiceImpl();
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
		case "getAllByMbrId":
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
		HttpSession session = req.getSession();
		Integer mbrId = (Integer) session.getAttribute("mbrId");
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		List<ItemTracking> itemTracking = itemTrackingService.getAllByMbrId(mbrId, currentPage);

		List<Item> itemTrackingList = new ArrayList<>();

		for (ItemTracking i : itemTracking) {
			CompositeDetail compositeDetail = i.getCompositeKey();
			int itemId = compositeDetail.getItemId();
			Item item = itemService.getItemByItemId(itemId);
			if (item != null) {
				itemTrackingList.add(item);
			}
		}

		int itemTrackingPageQty = itemTrackingService.getPageTotal(mbrId);
		req.getSession().setAttribute("itemTrackingPageQty", itemTrackingPageQty);

		req.setAttribute("itemTrackingList", itemTrackingList);
		req.setAttribute("currentPage", currentPage);

		return "/front_end/itemtracking/itemTrackingList.jsp";
	}

	private String addItemTracking(HttpServletRequest req, HttpServletResponse res) {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		HttpSession session = req.getSession();
		Integer mbrId = (Integer) session.getAttribute("mbrId");
		ItemTracking itemTracking = new ItemTracking();

		itemTracking.setCompositeKey(new ItemTracking.CompositeDetail(itemId, mbrId));
		itemTracking.setTrackingTime(new Timestamp(System.currentTimeMillis()));

		itemTrackingService.addItemTracking(itemTracking);

		return "/itemtrackinglist.check?action=getAllByMbrId";
	}

	private String deleteItemTracking(HttpServletRequest req, HttpServletResponse res) {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		HttpSession session = req.getSession();
		Integer mbrId = (Integer) session.getAttribute("mbrId");
		List<String> errorMsgs = new LinkedList<String>();

		int delete = itemTrackingService.deleteItemTracking(itemId, mbrId);

		if (delete != 1) {
			errorMsgs.add("刪除失敗");
		}

		if (!errorMsgs.isEmpty()) {
			req.setAttribute("errorMsgs", errorMsgs);
		}

		return "/itemtrackinglist.check?action=getAllByMbrId";
	}
}
