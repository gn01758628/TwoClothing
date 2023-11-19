package com.twoclothing.huiwen.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.twoclothing.huiwen.service.ItemService;
import com.twoclothing.huiwen.service.ItemServiceImpl;
import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.categorytags.CategoryTags;
import com.twoclothing.model.members.Members;

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
		
		//取商品列表
		HttpSession session = req.getSession();
		Integer mbrId = (Integer) session.getAttribute("mbrId");
		
		List<Item> itemList = new ArrayList<>();
		itemList = itemService.getItemBymbrIdAndStatus(mbrId);
		
		//取會員頭貼與圖片
		Members mem = itemService.getMembersByPK(mbrId);
		System.out.println(mem);
		
		//取類別標籤的父標籤，查詢商品類別用
		List<CategoryTags> categoryTagsList = new ArrayList<>();
		List<Map<String, Object>> itemListWithCategory = new ArrayList<>();
		for(Item item:itemList) {
		    Integer tagId = item.getTagId();
		    CategoryTags categoryTags = itemService.getByPrimaryKey(tagId);

		    Map<String, Object> itemWithCategory = new HashMap<>();
		    itemWithCategory.put("item", item);
		    itemWithCategory.put("categoryTags", categoryTags);

		    itemListWithCategory.add(itemWithCategory);
		}
		System.out.println(itemListWithCategory);
		
		
		req.setAttribute("itemList", itemList);
		req.setAttribute("Members", mem);
		req.setAttribute("itemListWithCategory", itemListWithCategory);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/front_end/item/sellerHome.jsp");
		dispatcher.forward(req, res);						
	}	
}
