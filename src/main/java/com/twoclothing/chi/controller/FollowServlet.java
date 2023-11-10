package com.twoclothing.chi.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.chi.service.FollowService;
import com.twoclothing.chi.service.FollowServiceImpl;
import com.twoclothing.model.follow.Follow;

@WebServlet("/follow")
public class FollowServlet extends HttpServlet {
	private FollowService followService;

	@Override
	public void init() throws ServletException {
		followService = new FollowServiceImpl();
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
			url = addFollow(req, res);
			break;
		case "delete":
			url = deleteFollow(req, res);
			break;
		default:
			url = "/front_end/follow/followList.jsp";
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

		List<Follow> followList = followService.getAllByMbrId(mbrId, currentPage);

		int followPageQty = followService.getPageTotal(mbrId);
		req.getSession().setAttribute("followPageQty", followPageQty);

		req.setAttribute("followList", followList);
		req.setAttribute("currentPage", currentPage);

		return "/front_end/follow/followList.jsp";
	}

	private String addFollow(HttpServletRequest req, HttpServletResponse res) {
		int mbrId = Integer.parseInt(req.getParameter("mbrId"));
		int followId = Integer.parseInt(req.getParameter("followId"));

		Follow follow = new Follow();
		follow.setCompositeKey(new Follow.CompositeDetail(mbrId, followId));

		followService.addFollow(follow);

		return "/follow?action=getAllByMbrId";
	}

	private String deleteFollow(HttpServletRequest req, HttpServletResponse res) {
		int mbrId = Integer.parseInt(req.getParameter("mbrId"));
		int followId = Integer.parseInt(req.getParameter("followId"));
		Follow follow = followService.getByPrimaryKey(mbrId, followId);

		followService.deleteFollow(follow);

		return "/follow?action=getAllByMbrId";
	}
}
