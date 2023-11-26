package com.twoclothing.chi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.twoclothing.chi.service.FollowService;
import com.twoclothing.chi.service.FollowServiceImpl;
import com.twoclothing.gordon.service.MembersService;
import com.twoclothing.gordon.service.MembersServiceImpl;
import com.twoclothing.model.follow.Follow;
import com.twoclothing.model.follow.Follow.CompositeDetail;
import com.twoclothing.model.members.Members;

@WebServlet("/follow.check")
public class FollowServlet extends HttpServlet {
	private FollowService followService;
	
	private MembersService membersService;

	@Override
	public void init() throws ServletException {
		followService = new FollowServiceImpl();
		
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
		String mbrId = req.getParameter("mbrId");
		String url = "";

		switch (action) {
		case "getAllByMbrId":
			req.setAttribute("mbrId", mbrId);
			url = getAllByMbrId(req, res);
			break;
		case "insert":
//			url = addFollow(req, res);
//			break;
			addFollow(req, res);
			return;
		case "delete":
//			url = deleteFollow(req, res);
//			break;
			deleteFollow(req, res);
			return;
		case "deletefromlist":
			url = deleteFollowFromList(req, res);
			break;
		default:
			url = "/front_end/follow/followList.jsp";
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

		List<Follow> followList = followService.getAllByMbrId(mbrId, currentPage);
		
		List<Members> followingList = new ArrayList<>();
		
		for (Follow f : followList) {
			CompositeDetail compositeDetail = f.getCompositeKey();
			int followId = compositeDetail.getFollowId();
			Members members = membersService.getByPrimaryKey(followId);
			
			if (members != null) {
				followingList.add(members);
	        }
		}

		int followPageQty = followService.getPageTotal(mbrId);
		req.getSession().setAttribute("followPageQty", followPageQty);

		req.setAttribute("followingList", followingList);
		req.setAttribute("currentPage", currentPage);

		return "/front_end/follow/followList.jsp";
	}

	private void addFollow(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		Integer mbrId = (Integer) session.getAttribute("mbrId"); // 要關注人的會員
		int followId = Integer.parseInt(req.getParameter("mbrId")); // 被關注的會員(賣家)

		Follow follow = new Follow();
		follow.setCompositeKey(new Follow.CompositeDetail(mbrId, followId));

		followService.addFollow(follow);

//		return "/follow?action=getAllByMbrId";
	}

	private void deleteFollow(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		Integer mbrId = (Integer) session.getAttribute("mbrId"); // 要關注人的會員
		int followId = Integer.parseInt(req.getParameter("mbrId")); // 被關注的會員(賣家)
		Follow follow = followService.getByPrimaryKey(mbrId, followId);

		followService.deleteFollow(follow);

//		return "/follow?action=getAllByMbrId";
	}
	
	private String deleteFollowFromList(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		Integer mbrId = (Integer) session.getAttribute("mbrId"); // 要關注人的會員
		int followId = Integer.parseInt(req.getParameter("followId")); // 被關注的會員(賣家)
		Follow follow = followService.getByPrimaryKey(mbrId, followId);

		followService.deleteFollow(follow);

		return "/follow.check?action=getAllByMbrId";
	}
}
