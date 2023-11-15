package com.twoclothing.huiwen.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.huiwen.service.ItemService;
import com.twoclothing.huiwen.service.ItemServiceImpl;
import com.twoclothing.model.aproduct.item.Item;

@WebServlet("/SellerHome/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 2 * 1024 * 1024, maxRequestSize = 2 * 2 * 1024 * 1024)
public class SellerHomeServlet extends HttpServlet{
	
	private ItemService itemService;
	
	public void init() throws ServletException {
		
		itemService = new ItemServiceImpl();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text; charset=UTF-8");
		
		Integer mbrId = Integer.valueOf(req.getParameter("mbrId"));
		
		
//		req.setAttribute("item", item);
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/front_end/item/itemDetail.jsp");
//		dispatcher.forward(req, res);						
	}	
}
