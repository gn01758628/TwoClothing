package com.twoclothing.chi.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.twoclothing.chi.service.ItemBrowsingRedisService;
import com.twoclothing.chi.service.ItemBrowsingRedisServiceImpl;
import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.redismodel.itembrowsing.ItemBrowsing;

@WebServlet("/itembrowsing.redis")
public class ItemBrowsingRedisServlet extends HttpServlet {
	private ItemBrowsingRedisService itemBrowsingRedisService;

	@Override
	public void init() throws ServletException {
		itemBrowsingRedisService = new ItemBrowsingRedisServiceImpl();
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
		case "insert":
//			url = addItemBrowsing(req, res);
//			break;
			addItemBrowsing(req, res);
			return;
		case "getAllByMbrId":
			req.setAttribute("mbrId", mbrId);
			url = getAllByMbrId(req, res);
			break;
		default:
			url = "/front_end/itembrowsing/itemBrowsingRedisList.jsp";
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
	}

	private void addItemBrowsing(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		Integer mbrId = (Integer) session.getAttribute("mbrId");
		int itemId = Integer.parseInt(req.getParameter("itemId"));

		ItemBrowsing itemBrowsing = new ItemBrowsing(mbrId, itemId, System.currentTimeMillis());
		itemBrowsingRedisService.addItemBrowsingRedis(itemBrowsing);

//		return "/itembrowsing.redis?action=getAllByMbrId";
	}

	private String getAllByMbrId(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		Integer mbrId = (Integer) session.getAttribute("mbrId");

		List<Item> itemBrowsingList = itemBrowsingRedisService.getAllByMbrId(mbrId);

		req.setAttribute("itemBrowsingList", itemBrowsingList);

		return "/front_end/itembrowsing/itemBrowsingRedisList.jsp";
	}
}
