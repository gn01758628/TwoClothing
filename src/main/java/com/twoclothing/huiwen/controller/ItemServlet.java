package com.twoclothing.huiwen.controller;

import static com.twoclothing.huiwen.Constants.ITEM_PAGE_MAX_RESULT;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

		System.out.println(action);
		// 新增資料
		if ("insert".equals(action)) {
			System.out.println("insert");
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
		if ("search".equals(action)) {
			String actionGetAll = req.getParameter("action");
			String forwardPath = "";
//			if (str == null || (str.trim().length()) == 0) {
//				return;
//			}
			switch (actionGetAll) {
			case "getAll":
				System.out.println("拿到getAll路徑");
				forwardPath = getAllItemsPage(req, res);
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
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
	}

	// 查全部
	private String getAllItemsPage(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int pageNow = (page == null) ? 1 : Integer.parseInt(page);

		List<Item> itemList = itemService.getAllItems(pageNow);

		if (req.getAttribute("itemPageQty") == null) {
			int itemPageQty = itemService.getPageTotal();
			req.setAttribute("itemPageQty", itemPageQty);
		}

		req.setAttribute("itemList", itemList);
		req.setAttribute("pageNow", pageNow);

		return "/front_end/item/itemSellerListAll.jsp";
	}

	private String getCompositeItemQuery(HttpServletRequest req, HttpServletResponse res) {
//		Map<String, String[]> map = req.getParameterMap();
		Map<String, String[]> map = new HashMap<>(req.getParameterMap());

		String page = req.getParameter("page");
		int pageNow = (page == null) ? 1 : Integer.parseInt(page);

		// 第一次進來
		if (page == null) {
			String itemNameSearch = req.getParameter("itemNameSearch");
			req.getSession().setAttribute("itemNameSearch", itemNameSearch);
			// 其他參數比照
			String itemPriceSearchStart = req.getParameter("itemPriceSearchStart");
			req.getSession().setAttribute("itemPriceSearchStart", itemPriceSearchStart);

			String itemPriceSearchEnd = req.getParameter("itemPriceSearchEnd");
			req.getSession().setAttribute("itemPriceSearchEnd", itemPriceSearchEnd);
			// 後續切頁
		} else {
			String itemNameSearch = (String) req.getSession().getAttribute("itemNameSearch");
			if (itemNameSearch != null) {
				map.put("itemNameSearch", new String[] { itemNameSearch });
			}

			String itemPriceSearchStart = (String) req.getSession().getAttribute("itemPriceSearchStart");
			if (itemPriceSearchStart != null) {
				map.put("itemPriceSearchStart", new String[] { itemPriceSearchStart });
			}

			String itemPriceSearchEnd = (String) req.getSession().getAttribute("itemPriceSearchEnd");
			if (itemPriceSearchEnd != null) {
				map.put("itemPriceSearchEnd", new String[] { itemPriceSearchEnd });
			}
		}

		System.out.println("map:" + map);
		System.out.println("mapSize:" + map.size());

		if (map.size() != 0) {

			List<Item> itemList = itemService.getItemByCompositeQuery(map, pageNow);
			int total = itemService.getResultTotalCondition(map);

			if (req.getAttribute("itemPageQty") == null) {
				int pageQty = (int) (total % ITEM_PAGE_MAX_RESULT == 0 ? (total / ITEM_PAGE_MAX_RESULT)
						: (total / ITEM_PAGE_MAX_RESULT + 1));
				req.setAttribute("itemPageQty", pageQty);
			}
			req.setAttribute("pageNow", pageNow);
			req.setAttribute("itemList", itemList);
		} else {
			System.out.println("map.size()==0");

		}
		return "/front_end/item/itemSellerListCompositeQuery.jsp";
	}
}
