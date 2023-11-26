package com.twoclothing.chijung.controller.front_end;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twoclothing.model.categorytags.CategoryTags;
import com.twoclothing.utils.generic.GenericService;



@WebServlet("/front_end/itemsearchServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ItemSearchServlet extends HttpServlet{
	
	private GenericService gs;
	ServletContext servletContext;

//	@Override
	public void init() throws ServletException {
		this.gs = gs.getInstance();
		this.servletContext = getServletContext();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		switch (action) {
			case "getCategoryTags":
				List<CategoryTags> categoryTagsSortedList = new ArrayList<>((List<CategoryTags>) servletContext.getAttribute("categoryTagsSortedList"));
				
				// 使用Gson轉換List為JSON格式
	            Gson gson = new GsonBuilder().create();
	            String jsonString = gson.toJson(categoryTagsSortedList);

	            // 將JSON格式的字串寫入 HttpServletResponse
	            res.setContentType("application/json");
	            res.setCharacterEncoding("UTF-8");
	            res.getWriter().write(jsonString);
	            break;
			case "getItems":	
				
				break;
		}
	}

	
	//===========================================================================================
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
