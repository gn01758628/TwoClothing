package com.twoclothing.chi.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.chi.service.ItemBrowsingService;
import com.twoclothing.chi.service.ItemBrowsingServiceImpl;
import com.twoclothing.model.aproduct.itembrowsing.ItemBrowsing;

@WebServlet("/itembrowsing")
public class ItemBrowsingServlet extends HttpServlet {
	private ItemBrowsingService itemBrowsingService;

	@Override
	public void init() throws ServletException {
		itemBrowsingService = new ItemBrowsingServiceImpl();
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
			url = addItemBrowsing(req, res);
			break;
		case "update":
			url = updateItemBrowsing(req, res);
			break;
		default:
			url = "/front_end/itembrowsing/itemBrowsingList.jsp";
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
	}

	private String getAllByMbrId(HttpServletRequest req, HttpServletResponse res) {
//		int mbrId = Integer.parseInt(req.getParameter("mbrId"));
		int mbrId = 1; // 測試用，到時這行可刪
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		List<ItemBrowsing> itemBrowsingList = itemBrowsingService.getAllByMbrId(mbrId, currentPage);

		int itemBrowsingPageQty = itemBrowsingService.getPageTotal(mbrId);
		req.getSession().setAttribute("itemBrowsingPageQty", itemBrowsingPageQty);

		req.setAttribute("itemBrowsingList", itemBrowsingList);
		req.setAttribute("currentPage", currentPage);

		return "/front_end/itembrowsing/itemBrowsingList.jsp";
	}

	private String addItemBrowsing(HttpServletRequest req, HttpServletResponse res) {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int mbrId = Integer.parseInt(req.getParameter("mbrId"));
		
		ItemBrowsing itemBrowsing = new ItemBrowsing();
		itemBrowsing.setCompositeKey(new ItemBrowsing.CompositeDetail(itemId, mbrId));
		itemBrowsing.setBrowsingTime(new Timestamp(System.currentTimeMillis()));
		
		itemBrowsingService.addItemBrowsing(itemBrowsing);

		return "/itembrowsing?action=getAllByMbrId";
	}
	
	private String updateItemBrowsing(HttpServletRequest req, HttpServletResponse res) {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int mbrId = Integer.parseInt(req.getParameter("mbrId"));
		ItemBrowsing itemBrowsing = itemBrowsingService.getByPrimaryKey(itemId, mbrId);
		
		itemBrowsing.setBrowsingTime(new Timestamp(System.currentTimeMillis()));
		
		itemBrowsingService.updateItemBrowsing(itemBrowsing);

		return "/itembrowsing?action=getAllByMbrId";
	}
}
