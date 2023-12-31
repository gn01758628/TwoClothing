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

import com.twoclothing.chi.service.BlackListService;
import com.twoclothing.chi.service.BlackListServiceImpl;
import com.twoclothing.chi.service.FollowService;
import com.twoclothing.chi.service.FollowServiceImpl;
import com.twoclothing.huiwen.service.ItemService;
import com.twoclothing.huiwen.service.ItemServiceImpl;
import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.blacklist.BlackList;
import com.twoclothing.model.categorytags.CategoryTags;
import com.twoclothing.model.follow.Follow;
import com.twoclothing.model.members.Members;

@WebServlet("/SellerHome/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 2 * 1024 * 1024, maxRequestSize = 2 * 2 * 1024 * 1024)
public class SellerHomeServlet extends HttpServlet{
	
	private ItemService itemService;
	
	private FollowService followService;
	
	private BlackListService blackListService;
	
	public void init() throws ServletException {
		
		itemService = new ItemServiceImpl();
		
		followService = new FollowServiceImpl();
		
		blackListService = new BlackListServiceImpl();
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
//		HttpSession session = req.getSession();
//		Integer mbrId = (Integer) session.getAttribute("mbrId");
		Integer mbrId = Integer.valueOf(req.getParameter("mbrId"));
		List<Item> itemList = new ArrayList<>();
		itemList = itemService.getItemBymbrIdAndStatus(mbrId);
		
		//取會員頭貼與圖片
		Members mem = itemService.getMembersByPK(mbrId);
		
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
		
		HttpSession session = req.getSession();
		Integer sessionMbrId = (Integer) session.getAttribute("mbrId");
		Follow follow = new Follow();
		follow = followService.getByPrimaryKey(sessionMbrId, mbrId);
		boolean isFollow = (follow != null);
		
		BlackList blackList = new BlackList();
		blackList = blackListService.getByPrimaryKey(sessionMbrId, mbrId);
		boolean isBlackList = (blackList != null);
		
		session.setAttribute("homembrId", mbrId);
		
		req.setAttribute("itemList", itemList);
		req.setAttribute("Members", mem);
		req.setAttribute("itemListWithCategory", itemListWithCategory);
		req.setAttribute("isFollow", isFollow);
		req.setAttribute("isBlackList", isBlackList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/front_end/item/sellerHome.jsp");
		dispatcher.forward(req, res);						
	}	
}
