package com.twoclothing.huiwen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.huiwen.service.ItemService;
import com.twoclothing.huiwen.service.ItemServiceImpl;
import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.utils.HibernateUtil;

@WebServlet("/itemsellerupload.jsp")
public class ItemServlet extends HttpServlet {

	private ItemService itemService;

	public void init() throws ServletException {
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

		PrintWriter out = res.getWriter();
	
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String itemName = req.getParameter("itemName");
			if (itemName == null || itemName.trim().length() == 0) {
				errorMsgs.add("商品名稱請勿空白");
			}
			
			String detail = req.getParameter("detail");
			
			Integer tagId = null;
			try {
				tagId = Integer.valueOf(req.getParameter("tagId").trim());
			}catch(NumberFormatException e) {
				errorMsgs.add("類別請填數字");
			}
			
			Integer mbrId = null;
			try {
				mbrId = Integer.valueOf(req.getParameter("mbrId").trim());
			}catch(NumberFormatException e) {
				errorMsgs.add("員編請填數字");
			}
			
			Integer price = null;
			try {
				price = Integer.valueOf(req.getParameter("price").trim());
			}catch(NumberFormatException e) {
				errorMsgs.add("價格請填數字");
			}
			
			Integer itemStatus = null;
			try {
				itemStatus = Integer.valueOf(req.getParameter("itemStatus").trim());
			}catch(NumberFormatException e) {
				errorMsgs.add("狀態請填數字");
			}
			
			Integer quantity = null;
			try {
				quantity = Integer.valueOf(req.getParameter("quantity"));
			}catch(NumberFormatException e) {
				errorMsgs.add("數量請填數字");
			}
			
			Item item = new Item();
			item.setItemName(itemName);
			item.setDetail(detail);
			item.setTagId(tagId);
			item.setMbrId(mbrId);
			item.setItemStatus(itemStatus);
			item.setPrice(price);
			item.setQuantity(quantity);
			
			
			ItemService itemSvc = new ItemServiceImpl();
			item = itemSvc.addItem(itemName, detail, tagId, mbrId, price, itemStatus, quantity);
			out.print("aaa");

//		String url = "/twoClothing/itemSellerUpload.jsp";
//		RequestDispatcher successView = req.getRequestDispatcher(url); 
//		successView.forward(req, res);

		}
	}

}
