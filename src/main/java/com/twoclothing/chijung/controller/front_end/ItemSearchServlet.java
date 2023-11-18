package com.twoclothing.chijung.controller.front_end;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream;
import java.util.stream.Collectors;

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
				List<CategoryTags> categoryTags = (List<CategoryTags>) servletContext.getAttribute("categoryTags");
				System.out.println(categoryTags);
				categoryTags = categoryTags.stream()
				        .sorted(Comparator.comparingInt(
				                category -> category.getSuperTagId() != null ? category.getSuperTagId() : Integer.MIN_VALUE
				        ))
				        .collect(Collectors.toList());
				// 使用Gson轉換List為JSON格式
	            Gson gson = new GsonBuilder().create();
	            String jsonString = gson.toJson(categoryTags);

	            // 將JSON格式的字串寫入 HttpServletResponse
	            res.setContentType("application/json");
	            res.setCharacterEncoding("UTF-8");
	            res.getWriter().write(jsonString);
	            break;
			case "getItems":	
				
				break;
		}
	}
	
	private static List<CategoryTags> getYourCategoryTagsList() {
        // 返回你的CategoryTags列表
        return List.of(
                new CategoryTags(1, null, "所有種類", 1),
                new CategoryTags(2, 1, "上衣", 1),
                new CategoryTags(3, 2, "短袖", 1),
                new CategoryTags(4, 2, "長袖", 1),
                new CategoryTags(5, 3, "男短袖", 1),
                new CategoryTags(6, 3, "女短袖", 1),
                new CategoryTags(7, 4, "毛衣長袖", 1),
                // 其他CategoryTags
                new CategoryTags(8, null, "其他種類", 1),
                new CategoryTags(9, 8, "其他子節點", 1)
        );
    }

	
	
	private static List<CategoryTags> sortByTreeStructure(List<CategoryTags> categoryTagsList) {
        Map<Integer, List<CategoryTags>> tagIdGroupBySuperTagId = categoryTagsList.stream()
                .collect(Collectors.groupingBy(CategoryTags::getSuperTagId));

        return sortByTreeStructure(tagIdGroupBySuperTagId, null);
    }

    private static List<CategoryTags> sortByTreeStructure(Map<Integer, List<CategoryTags>> tagIdGroupBySuperTagId, Integer superTagId) {
        List<CategoryTags> currentNodes = tagIdGroupBySuperTagId.get(superTagId);

        if (currentNodes == null) {
            return List.of();
        }

        return currentNodes.stream()
                .sorted(Comparator.comparingInt(CategoryTags::getTagId))
                .flatMap(node -> Stream.concat(Stream.of(node), sortByTreeStructure(tagIdGroupBySuperTagId, node.getTagId()).stream()))
                .collect(Collectors.toList());
    }
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
