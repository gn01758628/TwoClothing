package com.twoclothing.huiwen.controller;

import static com.twoclothing.huiwen.Constants.ITEM_PAGE_MAX_RESULT;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.twoclothing.huiwen.service.ItemService;
import com.twoclothing.huiwen.service.ItemServiceImpl;
import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.aproduct.itemimage.ItemImage;

@WebServlet("/Item/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 2 * 1024 * 1024, maxRequestSize = 2 * 2 * 1024 * 1024)
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
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text; charset=UTF-8");
		String choice = req.getParameter("choice");

		PrintWriter out = res.getWriter();

		System.out.println("choice:" + choice);

		// 新增資料
		if ("add".equals(choice)) {
			String url = "/front_end/item/itemSellerUpload.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(url);
			dispatcher.forward(req, res);
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String itemName = req.getParameter("itemName");
			if (itemName == null || itemName.trim().isEmpty())
				errorMsgs.add("商品標題不可以空白");

			String grade = req.getParameter("grade");
			if (grade == null || grade.trim().isEmpty() || !grade.matches("^[0-5]$"))
				errorMsgs.add("請正確選擇商品新舊程度");

			String size = req.getParameter("size");
			if (size == null || size.trim().isEmpty() || !size.matches("^[0-8]$"))
				errorMsgs.add("請正確選擇商品品尺寸");

			String detail = req.getParameter("detail");
			if (detail == null || detail.trim().isEmpty()) {
				errorMsgs.add("商品簡述不可以空白");
			} else {
				if (detail.replace("<br>", " ").length() > 250)
					errorMsgs.add("商品簡述不可以超過250個字");
			}

			String tagId = req.getParameter("tagId");
			System.out.println("tagId:" + tagId);
			List<Integer> allSelectableTagsId = itemService.getAllSelectableTagsId();
			System.out.println("列表"+allSelectableTagsId);
			if (tagId == null || tagId.trim().isEmpty()) {
System.out.println("類別錯1");
				errorMsgs.add("請正確選擇商品類別標籤");
			} 
//			else if (!allSelectableTagsId.contains(Integer.parseInt(tagId))) {
//System.out.println("類別錯2");
//				errorMsgs.add("您選擇的類別標籤並非是可選標籤");
//			}

			String quantity = req.getParameter("quantity");
			if (quantity == null) {
				errorMsgs.add("請正確填寫數量");
			} else {
				if (!quantity.isEmpty()) {
					if (!quantity.matches("[1-9][0-9]*")) {
						errorMsgs.add("數量必須是有效的正整数，且大於0");
					}
				}
			}
			String price = req.getParameter("price");
			if (price == null) {
				errorMsgs.add("請正確填寫價格");
			} else {
				if (!price.isEmpty()) {
					if (!price.matches("[1-9][0-9]*"))
						errorMsgs.add("價格必須是有效的正整数，且大於0");
				}
			}

			Part imgPart1 = null, imgPart2 = null;
			try {
				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					if ("image01".equals(part.getName()))
						imgPart1 = part;
					if ("image02".equals(part.getName()))
						imgPart2 = part;
				}

				// 判斷檔案類型是否錯誤
				boolean image01TypeInvalid = imgPart1 != null && imgPart1.getSize() > 0
						&& !imgPart1.getContentType().startsWith("image/");
				boolean image02TypeInvalid = imgPart2 != null && imgPart2.getSize() > 0
						&& !imgPart2.getContentType().startsWith("image/");
				if (image01TypeInvalid || image02TypeInvalid) {
					errorMsgs.add("圖片檔案類型錯誤(只接受圖檔)");
				}
				// 單張圖片超過大小的例外處理
			} catch (IllegalStateException e) {
				errorMsgs.add("單張圖片的大小不能超過2MB");
			}

			// 取得byte[]圖片
			byte[] image01 = null, image02 = null;
			if (imgPart1 != null && imgPart2 != null) {
				InputStream in = imgPart1.getInputStream();
				image01 = in.readAllBytes();
				in = imgPart2.getInputStream();
				image02 = in.readAllBytes();
				in.close();
			}
			if (image01 == null || image01.length == 0)
				errorMsgs.add("每個商品都必須要有主圖片");
			if (image02 == null)
				errorMsgs.add("請正確的選擇商品補充圖片");

			// 如果錯誤訊系不為空則轉發回新增頁面
			if (!errorMsgs.isEmpty()) {
				System.out.println("不為空");
	            req.getRequestDispatcher("/front_end/item/itemSellerUpload.jsp").forward(req, res);
			}

//			if (!errorMsgs.isEmpty()) {
//				req.getRequestDispatcher("/front_end/item/itemSellerUpload.jsp").forward(req, res);
//			}
//	        List<CategoryTags> allCategoryTags = itemService.getAllCategoryTags();
//	        req.setAttribute("categoryTags", allCategoryTags);
			Item item = new Item();
			item.setItemName(itemName);
			item.setGrade(Integer.parseInt(grade));
			item.setDetail(detail);
			item.setTagId(Integer.parseInt(tagId));
			item.setPrice(Integer.parseInt(price));
			item.setQuantity(Integer.parseInt(quantity));
			item.setItemStatus(0);
			Integer mbrId = 1;
			item.setMbrId(mbrId);
			if ("8".equals(size)) {
				item.setSize(null);
			} else {
				item.setSize(Integer.parseInt(size));
			}

			int itemPK = itemService.addItem(item);

			ItemImage itemImage01 = new ItemImage();
			itemImage01.setItemId(itemPK);
			itemImage01.setImage(image01);
			itemService.addItemImage(itemImage01);

			if (image02.length != 0) {
				ItemImage itemImage02 = new ItemImage();
				itemImage02.setItemId(itemPK);
				itemImage02.setImage(image02);
				itemService.addItemImage(itemImage02);
			}

//			String url = "/Item/searchAll?choice=getAll";
//			RequestDispatcher dispatcher = req.getRequestDispatcher(url);
//			dispatcher.forward(req, res);
		}

		
		//取一筆，準備修改
		if ("getOne".equals(choice)) {

			Integer itemId = Integer.valueOf(req.getParameter("itemId"));

			Item item = itemService.getItemByItemId(itemId);

			req.setAttribute("item", item);
			String url = "/front_end/item/itemSellerUpdate.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(url);
			dispatcher.forward(req, res);
		}
		//修改
		if ("update".equals(choice)) {

			Integer itemId = Integer.valueOf(req.getParameter("itemId"));
			String itemName = req.getParameter("itemName");
			Integer price = Integer.valueOf(req.getParameter("price"));
			Integer size = Integer.valueOf(req.getParameter("size"));
			Integer grade = Integer.valueOf(req.getParameter("grade"));
			String detail =req.getParameter("detail");
			Integer quantity = Integer.valueOf(req.getParameter("quantity"));
			System.out.println(itemId+"/"+ itemName+"/"+grade+"/"+size+"/"+detail+"/"+price+"/"+quantity);

			int itemUpdate = itemService.updateItem(itemId, itemName, grade, size, detail, price, quantity) ;
			if(itemUpdate == 1) {
				System.out.println("修改成功");
				
			}else {
				System.out.println("修改失敗");

			}
			
			String url = "/front_end/item/itemSellerSearch.jsp";

			RequestDispatcher dispatcher2 = req.getRequestDispatcher(url);
			dispatcher2.forward(req, res);

		}

//		if ("getAll".equals(choice)) {
//			getAllItemsPage(req, res);
//			RequestDispatcher dispatcher = req.getRequestDispatcher("/front_end/item/itemSellerListAll.jsp");
//			dispatcher.forward(req, res);
//		}

		// 複合查詢
		String choiceGet = req.getParameter("choice");
		System.out.println("choiceGet:" + choiceGet);
		String forwardPath = "";

		switch (choiceGet) {
		case "search":
			System.out.println("拿到search路徑");
			forwardPath ="/front_end/item/itemSellerSearch.jsp";
			break;
		
		case "searchCondition":
			System.out.println("拿到searchCondition路徑");
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
	

	// 查全部
	private String getAllItemsPage(HttpServletRequest req, HttpServletResponse res) {

		String page = req.getParameter("page");
		System.out.println("page+" + page);

		int pageNow = (page == null) ? 1 : Integer.parseInt(page);
		System.out.println("00001");

		List<Item> itemList = itemService.getAllItems(pageNow);

		if (req.getAttribute("itemPageQty") == null) {
			int itemPageQty = itemService.getPageTotal();
			req.setAttribute("itemPageQty", itemPageQty);
		}
		Collections.reverse(itemList);
		req.setAttribute("itemList", itemList);
		req.setAttribute("pageNow", pageNow);

		return "/front_end/item/itemSellerListAll.jsp";
	}

	// 複合查詢
	private String getCompositeItemQuery(HttpServletRequest req, HttpServletResponse res) {

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
			System.out.println("map.sizes()==0");

		}
		return "/front_end/item/itemSellerListCompositeQuery.jsp";
	}

}
