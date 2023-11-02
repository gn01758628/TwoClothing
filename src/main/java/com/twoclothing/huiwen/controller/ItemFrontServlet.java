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

@WebServlet("/Itemfront/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 2 * 1024 * 1024, maxRequestSize = 2 * 2 * 1024 * 1024)
public class ItemFrontServlet extends HttpServlet{
	
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
		
		//商品列表 到 商品詳情頁
		System.out.println(req.getParameter("goto"));
		String goTo = req.getParameter("goto");
			int itemId = Integer.valueOf(goTo);
			Item item = itemService.getItemByItemId(itemId);
			
			req.setAttribute("item", item);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/front_end/item/itemDetail.jsp");
			dispatcher.forward(req, res);			

		
		
		//商品詳情到購物車
		
//		String gotoCart = req.getParameter("goToCart");
//		System.out.println(gotoCart);
//		String itemName = req.getParameter("itemName");
//		System.out.println(itemName);
//
//		String size = req.getParameter("size");
//		String quantity = req.getParameter("quantity");
//		String price = req.getParameter("price");
//		int itemIdCart = Integer.valueOf(gotoCart);

	}
			
	
}
