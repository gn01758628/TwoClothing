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



@WebServlet("/front_end/itemsearch")
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
				List<CategoryTags> categoryTagsList = new ArrayList<>((List<CategoryTags>) servletContext.getAttribute("categoryTags"));
				
				for (CategoryTags categoryTags : categoryTagsList) {
				    if (categoryTags.getSuperTagId() == null) {
				        categoryTags.setSuperTagId(-1);
				    }
				}
				
				System.out.println(categoryTagsList);
				
				List<CategoryTags> sortedList = new ArrayList<CategoryTags>();
				buildCategoryTree(sortedList, categoryTagsList, -1);
				System.out.println("=======================");
				System.out.println(sortedList);
				// 使用Gson轉換List為JSON格式
	            Gson gson = new GsonBuilder().create();
	            String jsonString = gson.toJson(sortedList);

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
	// 根據輸入的參數,動態生成樹狀結構
//	public static void buildCategoryTree(List<CategoryTags> sortedList, List<CategoryTags> categoryTagsList, int parentId) {
//        Iterator<CategoryTags> iterator = categoryTagsList.iterator();
//
//        while (iterator.hasNext()) {
//        	CategoryTags category = iterator.next();
//
//            if (Objects.equals(category.getSuperTagId(), parentId)) {
//                sortedList.add(category);
////                iterator.remove(); // 移除已處理的元素
//
//                buildCategoryTree(sortedList, categoryTagsList, category.getTagId());
//            }
//        }
//    }
	
	public static void buildCategoryTree(List<CategoryTags> sortedList, List<CategoryTags> categoryTagsList, int parentId) {
	    Map<Integer, List<CategoryTags>> parentToChildrenMap = new HashMap<>();

	    // 將標籤按照父標籤分組
	    for (CategoryTags category : categoryTagsList) {
	        parentToChildrenMap.computeIfAbsent(category.getSuperTagId(), k -> new ArrayList<>()).add(category);
	    }

	    buildCategoryTreeRecursive(sortedList, parentToChildrenMap, parentId);
	}

	private static void buildCategoryTreeRecursive(List<CategoryTags> sortedList, Map<Integer, List<CategoryTags>> parentToChildrenMap, int parentId) {
	    List<CategoryTags> children = parentToChildrenMap.get(parentId);

	    if (children != null) {
	        // 排序子標籤，這部分可以根據實際需求修改
	        children.sort(Comparator.comparingInt(CategoryTags::getTagId));

	        for (CategoryTags category : children) {
	            sortedList.add(category);

	            // 遞迴處理子標籤
	            buildCategoryTreeRecursive(sortedList, parentToChildrenMap, category.getTagId());
	        }
	    }
	}

	
	//===========================================================================================
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
