package com.twoclothing.huiwen.controller;

import static com.twoclothing.huiwen.Constants.ITEM_PAGE_MAX_RESULT;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.twoclothing.chi.service.FollowService;
import com.twoclothing.chi.service.FollowServiceImpl;
import com.twoclothing.huiwen.service.ItemImageService;
import com.twoclothing.huiwen.service.ItemImageServiceImpl;
import com.twoclothing.huiwen.service.ItemService;
import com.twoclothing.huiwen.service.ItemServiceImpl;
import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.aproduct.itemimage.ItemImage;
import com.twoclothing.model.categorytags.CategoryTags;
import com.twoclothing.model.follow.Follow;
import com.twoclothing.model.follow.Follow.CompositeDetail;
import com.twoclothing.redismodel.notice.Notice;

import redis.clients.jedis.JedisPool;

@WebServlet("/Item/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 2 * 1024 * 1024, maxRequestSize = 2 * 2 * 1024 * 1024)
public class ItemServlet extends HttpServlet {

	private ItemService itemService;
	private JedisPool jedisPool;
	private ItemImageService itemImageService;
	private FollowService followService;

	public void init() throws ServletException {
		itemService = new ItemServiceImpl();
		itemImageService= new ItemImageServiceImpl();
		followService = new FollowServiceImpl();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");

		PrintWriter out = res.getWriter();

		String choice = req.getParameter("choice");
		String forwardPath = "";
		if (choice != null) {
			switch (choice) {
			case "search":
				forwardPath = "/front_end/item/itemSellerSearch.jsp";
				break;
			case "searchCondition":
				forwardPath = getCompositeItemQuery(req, res);
				break;

			case "SearchItems":
				forwardPath = "/front_end/item/itemSellerSearch.jsp";
				break;
			case "getAllList":
				forwardPath = getAllList(req, res);
				break;
			case "getAllListNoPage":
				forwardPath = getAllListNoPage(req, res);
				break;
			case "addItem":
				forwardPath = "/front_end/item/itemSellerUpload.jsp";
				break;
			default:
				forwardPath = "/front_end/item/itemMain.jsp";
			}
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
		
		String addRoad = req.getParameter("addRoad");
		// 新增資料
		if ("add".equals(addRoad)) {
			
			//如果賣家分數為0不可新增商品
			HttpSession session = req.getSession();
			Integer mbrId = (Integer) session.getAttribute("mbrId");
			
			Integer sellerPoint = itemService.getSellScoreByMbrId(mbrId);
			

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String itemName = req.getParameter("itemName");
			if (itemName == null || itemName.trim().isEmpty()) {

				errorMsgs.add("商品標題不可以空白");
			}

			Integer grade = null;
			String gradeStr = req.getParameter("grade");
			if (!(gradeStr == null || gradeStr.trim().isEmpty())) {
				String regex = "^[0-5]$"; // 此為一位數的數字正則表達式
				
				if (gradeStr.matches(regex)) {
					grade = Integer.valueOf(gradeStr);
				} else {
					errorMsgs.add("請正確選擇商品新舊程度");
				}
			} else {
				errorMsgs.add("商品新舊程度不可以空白");
			}				

			Integer size = null;
			String sizeStr = req.getParameter("size");
			if (!(sizeStr == null || sizeStr.trim().isEmpty())) {
				String regex = "^[0-9]$"; // 此為一位數的數字正則表達式
				
				if (sizeStr.matches(regex)) {
					size = Integer.valueOf(sizeStr);
				} else {
					errorMsgs.add("請正確選擇商品尺寸");
				}
			} else {
				errorMsgs.add("商品尺寸不可以空白");
			}	

			String detail = req.getParameter("detail");
			if (detail == null || detail.trim().isEmpty()) {
				errorMsgs.add("商品簡述不可以空白");
			} else {
				if (detail.replace("<br>", " ").length() > 250)
					errorMsgs.add("商品簡述不可以超過250個字");
			}
			Integer tagId = null;
			String tagIdStr = req.getParameter("tagId");
			List<Integer> allSelectableTagsId = itemService.getAllSelectableTagsId();
			if (tagIdStr == null || tagIdStr.trim().isEmpty()) {
				errorMsgs.add("請正確選擇商品類別標籤");
			} else {
			    try {
			        int parsedTagId = Integer.parseInt(tagIdStr);
			        if (!allSelectableTagsId.contains(parsedTagId)) {
			            errorMsgs.add("您選擇的類別標籤並非是可選標籤");
			        } else {
			            tagId = parsedTagId; // Assign the parsed value only if it's valid
			        }
			    } catch (NumberFormatException e) {
			        errorMsgs.add("請輸入有效的商品類別標籤");
			    }
			}
			
			if(!(tagIdStr == null || tagIdStr.trim().isEmpty())) {
				CategoryTags categoryTag = itemService.getByPrimaryKey(Integer.parseInt(tagIdStr));		
				Gson gson = new Gson();
				String jsonTag = gson.toJson(categoryTag);
				req.setAttribute("jsonTag", jsonTag);
			}
			
			Integer quantity = null;
			String quantityStr = req.getParameter("quantity");
			if (!(quantityStr == null || quantityStr.trim().isEmpty())) {
			    String regex = "[1-9][0-9]*";

			    if (quantityStr.matches(regex)) {
			    	quantity = Integer.valueOf(quantityStr);
			    } else {
			        errorMsgs.add("數量必須是有效的正整数，且大於0");
			    }
			} else {
			    errorMsgs.add("請正確填寫數量");
			}
			
			
			Integer price = null;
			String priceStr = req.getParameter("price");
			if (!(priceStr == null || priceStr.trim().isEmpty())) {
			    String regex = "[1-9][0-9]*";

			    if (priceStr.matches(regex)) {
			    	price = Integer.valueOf(priceStr);
			    } else {
			        errorMsgs.add("價格必須是有效的正整数，且大於0");
			    }
			} else {
			    errorMsgs.add("請正確填寫價格");
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


			Item item = new Item();
			item.setItemName(itemName);
			item.setGrade(grade);
			item.setDetail(detail);
			item.setTagId(tagId);
			item.setPrice(price);
			item.setQuantity(quantity);
			item.setItemStatus(0);
			item.setSize(size);
			
			item.setMbrId(mbrId);
			
			// 如果錯誤訊系不為空則轉發回新增頁面
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("item", item);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/item/itemSellerUpload.jsp");
				failureView.forward(req, res);
				return;
			}

			int itemPK = itemService.addItem(item);
			
			List<Follow> followList = followService.getAllByFollowId(mbrId);
			
			for (Follow follow : followList) {
				CompositeDetail compositeDetail = follow.getCompositeKey();
				Integer followMbrId = compositeDetail.getMbrId();
				Integer followId = compositeDetail.getFollowId();
				if (mbrId == followId) {
					Notice notice = new Notice();
					notice.setType("關注中的賣家有新商品上架囉！");
					notice.setHead("新品上架");
					notice.setContent("商品「" + itemName + "」已上架，手刀搶購。");
					notice.setLink("/SellerHome/home?mbrId=" + mbrId);
					notice.setImageLink("/images/iteminsert2.png");
					itemService.addNotice(notice, followMbrId);
				}
			}

			ItemImage itemImage01 = new ItemImage();
			itemImage01.setItemId(itemPK);
			itemImage01.setImage(image01);
			itemImageService.addItemImage(itemImage01);

			if (image02.length != 0) {
				ItemImage itemImage02 = new ItemImage();
				itemImage02.setItemId(itemPK);
				itemImage02.setImage(image02);
				itemService.addItemImage(itemImage02);
			}
			
			req.setAttribute("item", item);
			req.setAttribute("item", item);
			String url = "/front_end/item/itemSellerUpdateOne.jsp";
			RequestDispatcher dispatcher1 = req.getRequestDispatcher(url);
			dispatcher1.forward(req, res);
		}

		// 取一筆，準備修改
		
		String updateRoad = req.getParameter("getOneForUpdate");
		
		if ("getOne".equals(updateRoad)) {
			Integer itemId = Integer.valueOf(req.getParameter("itemId"));

			Item item = itemService.getItemByItemId(itemId);

			Integer tagId = item.getTagId();

			CategoryTags categoryTag = itemService.getByPrimaryKey(tagId);		
			Gson gson = new Gson();
			String jsonTag = gson.toJson(categoryTag);
			
			req.setAttribute("item", item);
			req.setAttribute("jsonTag", jsonTag);
			String url = "/front_end/item/itemSellerUpdate.jsp";
			RequestDispatcher dispatcher1 = req.getRequestDispatcher(url);
			dispatcher1.forward(req, res);
		}
		
		// 修改
		String forUpdate = req.getParameter("forUpdate");
		if ("update".equals(forUpdate)) {

			Integer itemId = Integer.valueOf(req.getParameter("itemId"));
			String itemName = req.getParameter("itemName");
			Integer price = Integer.valueOf(req.getParameter("price"));
			Integer grade = Integer.valueOf(req.getParameter("grade"));
			Integer size = Integer.valueOf(req.getParameter("size"));
			Integer itemStatus = Integer.valueOf(req.getParameter("itemStatus"));
			Integer quantity = Integer.valueOf(req.getParameter("quantity"));
			Integer tagId = Integer.valueOf(req.getParameter("tagId"));
			String detail = req.getParameter("detail");		
			
			Part imgPart1 = null, imgPart2 = null;
			try {
				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					if ("image01".equals(part.getName()))
						imgPart1 = part;
					if ("image02".equals(part.getName()))
						imgPart2 = part;
				}			// 單張圖片超過大小的例外處理
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
			
			byte[] image01 = null, image02 = null;
			if (imgPart1 != null && imgPart2 != null) {
				InputStream in = imgPart1.getInputStream();
				image01 = in.readAllBytes();
				in = imgPart2.getInputStream();
				image02 = in.readAllBytes();
				in.close();
			}
			List<ItemImage> itemImageList = itemImageService.getByItemId(itemId);
			if (image01.length != 0) {
			    itemImageList.get(0).setImage(image01);
			    itemImageService.updateItemImage(itemImageList.get(0));
			}

			// 如果有第二張圖片，新增或更新第二張圖片
			if (image02.length != 0) {
			    ItemImage newItemImage2 ;

			    if (itemImageList.size() > 1) {
			        newItemImage2 = itemImageList.get(1);
			        newItemImage2.setImage(image02);
			        itemImageService.updateItemImage(newItemImage2);
			    } else {
			        newItemImage2 = new ItemImage();
			        newItemImage2.setItemId(itemId);
			        newItemImage2.setImage(image02);
			        itemImageService.addItemImage(newItemImage2);
			    }
			}

			Item item = itemService.getItemByItemId(itemId);
			
			
			item.setItemId(itemId);
			item.setItemName(itemName);
			item.setPrice(price);
			item.setGrade(grade);
			item.setSize(size);
			item.setItemStatus(itemStatus);
			item.setQuantity(quantity);
			item.setDetail(detail);
			item.setTagId(tagId);

			int itemUpdate = itemService.updateItem(item);

			if (itemUpdate == 1) {
				
				CategoryTags categoryTag = itemService.getByPrimaryKey(tagId);		
				Gson gson = new Gson();
				String jsonTag = gson.toJson(categoryTag);
				
				
				item = itemService.getItemByItemId(itemId);
				req.setAttribute("item", item);
				req.setAttribute("jsonTag", jsonTag);

				String url = "/front_end/item/itemSellerUpdateOne.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);
			} else {
				item = itemService.getItemByItemId(itemId);
				req.setAttribute("item", item);

				String url = "/front_end/item/itemSellerUpdate.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);
			}
		}
	}
	
	// 查全部，商品列表
	public String getAllList(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");

		int pageNow = (page == null) ? 1 : Integer.parseInt(page);

		List<Item> itemList = itemService.getAllItems(pageNow);

		if (req.getAttribute("itemPageQty") == null) {
			int itemPageQty = itemService.getPageTotal();
			req.setAttribute("itemPageQty", itemPageQty);
		}

		req.setAttribute("itemList", itemList);
		req.setAttribute("pageNow", pageNow);

		return "/front_end/item/itemList.jsp";
	}
	
	
	//查全部不分頁
	public String getAllListNoPage(HttpServletRequest req, HttpServletResponse res) {
		List<Item> itemList = itemService.getAllByStatus(0);
		req.setAttribute("itemList", itemList);
		return "/front_end/item/itemList.jsp";
	}

	// 複合查詢
	private String getCompositeItemQuery(HttpServletRequest req, HttpServletResponse res) {

		
		Map<String, String[]> map = new HashMap<>(req.getParameterMap());

		String page = req.getParameter("page");
		int pageNow = (page == null) ? 1 : Integer.parseInt(page);
		
		HttpSession session = req.getSession();
		String mbrId = String.valueOf(session.getAttribute("mbrId"));
		
		if (mbrId != null) {
			map.put("mbrId", new String[] { mbrId });
		}
		
		// 第一次進來
		if (page == null) {
			String itemNameSearch = req.getParameter("itemNameSearch");
			req.getSession().setAttribute("itemNameSearch", itemNameSearch);
			// 其他參數比照
			String itemPriceSearchStart = req.getParameter("itemPriceSearchStart");
			req.getSession().setAttribute("itemPriceSearchStart", itemPriceSearchStart);

			String itemPriceSearchEnd = req.getParameter("itemPriceSearchEnd");
			req.getSession().setAttribute("itemPriceSearchEnd", itemPriceSearchEnd);

			String itemGrade = req.getParameter("itemGrade");
			req.getSession().setAttribute("itemGrade", itemGrade);
			
			String itemSize = req.getParameter("itemSize");
			req.getSession().setAttribute("itemSize", itemSize);
			
			String tagId = req.getParameter("tagId");
			req.getSession().setAttribute("tagId", tagId);			
			
			String itemQuantity = req.getParameter("itemQuantity");

		    if (itemQuantity != null) {
		        switch (itemQuantity) {
		            case "2":
		                req.getSession().setAttribute("itemQuantityStart", "0");
		                req.getSession().setAttribute("itemQuantityEnd", "5");
		                break;
		            case "3":
		                req.getSession().setAttribute("itemQuantityStart", "6");
		                req.getSession().removeAttribute("itemQuantityEnd"); 
		                break;
		            default:
		                req.getSession().removeAttribute("itemQuantityStart");
		                req.getSession().removeAttribute("itemQuantityEnd");
		                break;
		        }
		    }

			String itemStatus = req.getParameter("itemStatus");
			req.getSession().setAttribute("itemStatus", itemStatus);
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
			String itemGrade = (String) req.getSession().getAttribute("itemGrade");
			if (itemGrade != null) {
				map.put("itemGrade", new String[] { itemGrade });
			}
			String itemSize = (String) req.getSession().getAttribute("itemSize");
			if (itemSize != null) {
				map.put("itemSize", new String[] { itemSize });
			}
			String itemQuantityStart = (String) req.getSession().getAttribute("itemQuantityStart");
			if (itemQuantityStart != null) {
				map.put("itemQuantityStart", new String[] { itemQuantityStart });
			}
			String itemQuantityEnd = (String) req.getSession().getAttribute("itemQuantityEnd");
			if (itemQuantityEnd != null) {
				map.put("itemQuantityEnd", new String[] { itemQuantityEnd });
			}

			String itemStatus = (String) req.getSession().getAttribute("itemStatus");
			if (itemStatus != null) {
				map.put("itemStatus", new String[] { itemStatus });
			}
			String tagId = (String) req.getSession().getAttribute("tagId");
			if (tagId != null) {
				map.put("tagId", new String[] { tagId });
			}
		}

		if (map.size() != 0) {
			List<Item> itemList = itemService.getItemByCompositeQuery(map, pageNow);
			
			//取類別標籤
			List<CategoryTags> tagList = itemService.getAllCategoryTags();		
			Gson gson = new Gson();
			String jsonTagList = gson.toJson(tagList);
			
			int total = itemService.getResultTotalCondition(map);

			if (req.getAttribute("itemPageQty") == null) {
				int pageQty = (int) (total % ITEM_PAGE_MAX_RESULT == 0 ? (total / ITEM_PAGE_MAX_RESULT)
						: (total / ITEM_PAGE_MAX_RESULT + 1));
				req.setAttribute("itemPageQty", pageQty);
			}
			req.setAttribute("jsonTagList", jsonTagList);
			req.setAttribute("pageNow", pageNow);
			req.setAttribute("itemList", itemList);
		} else {

		}
		return "/front_end/item/itemSellerListCompositeQuery.jsp";
	}

}
