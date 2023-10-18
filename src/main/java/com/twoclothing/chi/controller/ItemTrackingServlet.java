package com.twoclothing.chi.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/servlet/itemtracking")
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
		PrintWriter out = res.getWriter();

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String itemIdString = req.getParameter("itemId");
			String mbrIdString = req.getParameter("mbrId");

			Integer itemId = null;
			if (itemIdString == null || itemIdString.trim().isEmpty()) {
				errorMsgs.add("請填寫商品編號");
			} else {
				try {
					itemId = Integer.valueOf(itemIdString);
				} catch (NumberFormatException e) {
					errorMsgs.add("商品編號請填數字");
				}
			}

			Integer mbrId = null;
			if (mbrIdString == null || mbrIdString.trim().isEmpty()) {
				errorMsgs.add("請填寫mbrId");
			} else {
				try {
					mbrId = Integer.valueOf(mbrIdString);
				} catch (NumberFormatException e) {
					errorMsgs.add("mbrId請填數字");
				}
			}

			ItemTracking itemTracking = new ItemTracking();
			itemTracking.setCompositeKey(new ItemTracking.CompositeDetail(10, 101));
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			itemTracking.setTrackingTime(currentTime);
			itemTrackingService.addItemTracking(itemTracking);
			
//			System.out.println(itemTracking);
			
			String url = "/TwoClothing/itemTracking.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
	}
}
