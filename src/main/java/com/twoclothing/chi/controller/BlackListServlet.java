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

import com.twoclothing.chi.service.BlackListService;
import com.twoclothing.chi.service.BlackListServiceImpl;
import com.twoclothing.gordon.service.MembersService;
import com.twoclothing.gordon.service.MembersServiceImpl;
import com.twoclothing.model.blacklist.BlackList;
import com.twoclothing.model.blacklist.BlackList.CompositeDetail;
import com.twoclothing.model.members.Members;

@WebServlet("/blacklist.check")
public class BlackListServlet extends HttpServlet {
	private BlackListService blackListService;
	
	private MembersService membersService;

	@Override
	public void init() throws ServletException {
		blackListService = new BlackListServiceImpl();
		
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
			addBlackList(req, res);
			return;
		case "delete":
			deleteBlackList(req, res);
			return;
		case "deletefromlist":
			url = deleteBlackListFromList(req, res);
			break;
		default:
			url = "/front_end/blacklist/BlackList.jsp";
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

		List<BlackList> blackList = blackListService.getAllByMbrId(mbrId, currentPage);
		
		List<Members> blackingList = new ArrayList<>();
		
		for (BlackList b : blackList) {
			CompositeDetail compositeDetail = b.getCompositeKey();
			int blackId = compositeDetail.getBlackId();
			Members members = membersService.getByPrimaryKey(blackId);
			
			if (members != null) {
				blackingList.add(members);
	        }
		}

		int blackListPageQty = blackListService.getPageTotal(mbrId);
		req.getSession().setAttribute("blackListPageQty", blackListPageQty);

		req.setAttribute("blackingList", blackingList);
		req.setAttribute("currentPage", currentPage);

		return "/front_end/blacklist/blackList.jsp";
	}

	private void addBlackList(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		Integer mbrId = (Integer) session.getAttribute("mbrId"); // 要黑名單人的會員
		int blackId = Integer.parseInt(req.getParameter("mbrId")); // 被黑名單的會員(賣家)

		BlackList blackList = new BlackList();
		blackList.setCompositeKey(new BlackList.CompositeDetail(mbrId, blackId));

		blackListService.addBlackList(blackList);
	}

	private void deleteBlackList(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		Integer mbrId = (Integer) session.getAttribute("mbrId"); // 要黑名單人的會員
		int blackId = Integer.parseInt(req.getParameter("mbrId")); // 被黑名單的會員(賣家)
		BlackList blackList = blackListService.getByPrimaryKey(mbrId, blackId);

		blackListService.deleteBlackList(blackList);
	}
	
	private String deleteBlackListFromList(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		Integer mbrId = (Integer) session.getAttribute("mbrId"); // 要黑名單人的會員
		int blackId = Integer.parseInt(req.getParameter("blackId")); // 被黑名單的會員(賣家)
		BlackList blackList = blackListService.getByPrimaryKey(mbrId, blackId);

		blackListService.deleteBlackList(blackList);
		
		return "/blacklist.check?action=getAllByMbrId";
	}
}
