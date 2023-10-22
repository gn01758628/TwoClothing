package com.twoclothing.huiwen.controller;

import static com.twoclothing.huiwen.Constants.ITEM_PAGE_MAX_RESULT;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.huiwen.service.ItemService;
import com.twoclothing.huiwen.service.ItemServiceImpl;
import com.twoclothing.model.aproduct.item.Item;

@WebServlet("/Item/*")
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

		res.setCharacterEncoding("UTF-8");
		res.setContentType("text; charset=UTF-8");
		String action = req.getParameter("action");

		PrintWriter out = res.getWriter();

		// 新增資料
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String itemName = req.getParameter("itemName");
			if (itemName == null || itemName.trim().length() == 0) {
				errorMsgs.add("商品名稱請勿空白");
				return;
			}

			String detail = req.getParameter("detail");

			Integer grade = null;
			try {
				grade = Integer.valueOf(req.getParameter("grade").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("新舊請填數字");
				return;
			}

			Integer size = null;
			try {
				size = Integer.valueOf(req.getParameter("size").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("尺寸請填數字");
				return;
			}

			Integer tagId = null;
			try {
				tagId = Integer.valueOf(req.getParameter("tagId").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("類別請填數字");
				return;
			}

			Integer mbrId = null;
			try {
				mbrId = Integer.valueOf(req.getParameter("mbrId").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("員編請填數字");
				return;
			}

			Integer price = null;
			try {
				price = Integer.valueOf(req.getParameter("price").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("價格請填數字");
				return;
			}

			Integer itemStatus = null;
			try {
				itemStatus = Integer.valueOf(req.getParameter("itemStatus").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("狀態請填數字");
				return;
			}

			Integer quantity = null;
			try {
				quantity = Integer.valueOf(req.getParameter("quantity"));
			} catch (NumberFormatException e) {
				errorMsgs.add("數量請填數字");
				return;
			}

			itemService.addItem(itemName, grade, size, detail, tagId, mbrId, price, itemStatus, quantity);

		
	}

	// 複合查詢

	String actionGetAll = req.getParameter("action");
	String forwardPath = "";
//			if (str == null || (str.trim().length()) == 0) {
//				return;
//			}
	switch(actionGetAll){
		case "getAll":
			System.out.println("拿到getAll路徑");
//			forwardPath = itemService.getAllItems();
			
			forwardPath = getAllItems(req, res);		
			break;
		case "search":
			System.out.println("拿到search路徑");
			forwardPath = getCompositeItemQuery(req, res);
			break;
			
		case "SearchItems":
			System.out.println("拿到SearchItems路徑");
			forwardPath = "/front_end/item/itemSellerSearch.jsp";
			break;
		default:
			forwardPath = "/front_end/item/itemSellerUpload.jsp";			
		}
	System.out.println("forwardPath"+forwardPath);
	RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
	dispatcher.forward(req, res);
	}
	
	

//	private String getAllItems(HttpServletRequest req, HttpServletResponse res) {
//		String page = req.getParameter("page");
//		int pageNow = (page == null) ? 1 : Integer.parseInt(page);
//
//		List<Item> itemList = itemService.getAllItems(pageNow);
//		
//		if (req.getSession().getAttribute("itemPageQty") == null) {
//			int itemPageQty = itemService.getPageTotal();
//			req.getSession().setAttribute("itemPageQty", itemPageQty);
//			System.out.println("==itemPageQty:"+itemPageQty);
//		}
//		
//		req.setAttribute("itemList", itemList);
//		req.setAttribute("pageNow", pageNow);
//		
//		System.out.println("///pageNow:"+pageNow);
//		return "/front_end/item/itemSellerListCompositeQuery.jsp";
//	}
	
	private String getAllItems(HttpServletRequest req, HttpServletResponse res) {
		
		List<Item> itemList = itemService.getAllItems();
		req.setAttribute("itemList", itemList);
		System.out.println("itemList:"+itemList);
		return "/front_end/item/itemSellerListCompositeQuery.jsp";
	}

	private String getCompositeItemQuery(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String[]> map = req.getParameterMap();
//		Map<String, String[]> map = new HashMap<>(req.getParameterMap());


		if (req.getParameter("itemNameSearch") != null) {
		    String itemNameSearch = req.getParameter("itemNameSearch");
		    req.getSession().setAttribute("itemNameSearch", itemNameSearch);
		    // 在map中添加itemNameSearch参数
		    
		} else {
			String itemNameSearch = (String)req.getSession().getAttribute("itemNameSearch");
			map.put("itemNameSearch", new String[]{itemNameSearch});
			System.out.println("====================="+itemNameSearch);
		}
		if (req.getParameter("itemPriceSearchStart") != null) {
			String itemPriceSearchStart = req.getParameter("itemPriceSearchStart");
			req.getSession().setAttribute("itemPriceSearchStart", itemPriceSearchStart);
			// 在map中添加itemNameSearch参数
			
		} else {
			String itemPriceSearchStart = (String)req.getSession().getAttribute("itemPriceSearchStart");
			map.put("itemPriceSearchStart", new String[]{itemPriceSearchStart});
			System.out.println("====================="+itemPriceSearchStart);
		}
		if (req.getParameter("itemPriceSearchEnd") != null) {
			String itemPriceSearchEnd = req.getParameter("itemPriceSearchEnd");
			req.getSession().setAttribute("itemPriceSearchEnd", itemPriceSearchEnd);
			// 在map中添加itemNameSearch参数
			
		} else {
			String itemPriceSearchEnd = (String)req.getSession().getAttribute("itemPriceSearchEnd");
			map.put("itemPriceSearchEnd", new String[]{itemPriceSearchEnd});
			System.out.println("====================="+itemPriceSearchEnd);
		}

		
//		for (Map.Entry<String, String[]> entry : map.entrySet()) {
//		    String key = entry.getKey();
//		    String[] values = entry.getValue();
//		    System.out.println("Key: " + key);
//		    for (String value : values) {
//		        System.out.println("Value: " + value);
//		    }
//		}
		
		if (!map.isEmpty()) {

			String page = req.getParameter("pageNow");
			System.out.println("///page:"+page);
			//page=0
			int pageNow = (page == null) ? 1 : Integer.parseInt(page);
	
//			List<Item> itemList = itemService.getAllItems(pageNow);
//			System.out.println("pageNow:" + pageNow);
			//pageNow=1

			List<Item> itemList = itemService.getItemByCompositeQuery(map, pageNow);
			
//			int count = 0 ;
//			for(Item item : itemList) {
//				count++;
//				System.out.println("item:"+item);
//			}
			
//			System.out.println("count:"+count);
			System.out.println("==================itemPageQty:"+req.getSession().getAttribute("itemPageQty"));
//			if (req.getSession().getAttribute("itemPageQty") == null) {
//				int itemPageQty = itemService.getPageTotal();
//				req.getSession().setAttribute("itemPageQty", itemPageQty);
//				System.out.println("==itemPageQty:"+itemPageQty);
//			}
			

//			req.setAttribute("itemList", itemList);
//			req.setAttribute("pageNow", pageNow);
//			System.out.println(itemList);
		} else {
//			return "/TwoClothing/front_end/item/itemSellerSearch.jsp";
		}

//		RequestDispatcher dispatcher = req.getRequestDispatcher("/front_end/item/itemSellerListCompositeQuery.jsp");
//		dispatcher.forward(req, res);
		return "/front_end/item/itemSellerListCompositeQuery.jsp";
	}
}


