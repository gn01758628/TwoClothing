package com.twoclothing.huiwen.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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

@WebServlet("/Itemfront/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 2 * 1024 * 1024, maxRequestSize = 2 * 2 * 1024 * 1024)
public class ItemFrontServlet extends HttpServlet{
	
	private ItemService itemService;
	
	private ItemTrackingService itemTrackingService;
	
	public void init() throws ServletException {
		itemService = new ItemServiceImpl();
		
		itemTrackingService = new ItemTrackingServiceImpl();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text; charset=UTF-8");
		
		//商品列表 到 商品詳情頁
		System.out.println("!!!"+req.getParameter("goto"));
		String goTo = req.getParameter("goto");
		int itemId = Integer.valueOf(goTo);
		Item item = itemService.getItemByItemId(itemId);
		
		HttpSession session = req.getSession();
		Integer mbrId = (Integer) session.getAttribute("mbrId");
		ItemTracking itemTracking = new ItemTracking();
		itemTracking = itemTrackingService.getByPrimaryKey(itemId, mbrId);
		boolean isItemTracked = (itemTracking != null);
		
		req.setAttribute("item", item);
		req.setAttribute("isItemTracked", isItemTracked);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/front_end/item/itemDetail.jsp");
		dispatcher.forward(req, res);
	}	
}
