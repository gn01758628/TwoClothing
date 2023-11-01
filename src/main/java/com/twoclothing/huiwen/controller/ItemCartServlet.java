package com.twoclothing.huiwen.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.huiwen.service.ItemService;
import com.twoclothing.huiwen.service.ItemServiceImpl;
import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.utils.JedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@WebServlet("/ItemCart/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 2 * 1024 * 1024, maxRequestSize = 2 * 2 * 1024 * 1024)
public class ItemCartServlet extends HttpServlet {

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

		// 商品詳情到購物車
		if ("gotoCart".equals(req.getParameter("gotoCart"))) {

			String itemId = req.getParameter("itemId");
			List<Item> itemList = new ArrayList<>();
			Item item = itemService.getItemByItemId(Integer.valueOf(itemId));
			itemList.add(item);

			String quantity = req.getParameter("quantity");
			System.out.println("quantity" + quantity);

			String mbrId = req.getParameter("mbrId");
			System.out.println("mbrId" + mbrId);
			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			jedis.select(13);

			// 要存储的数组数据
			String[] itemIds = { itemId };
			String[] quantities = { quantity };
			// Redis 键

			// 存储数组数据到 Redis 列表（右侧）
			for (int i = 0; i < itemIds.length; i++) {
				jedis.hset(mbrId, itemIds[i], quantities[i]);
			}

			Map<String, String> productInfo = jedis.hgetAll(mbrId);

			// 打印商品ID与数量
			for (Map.Entry<String, String> entry : productInfo.entrySet()) {
				System.out.println("Product ID: " + entry.getKey() + ", Quantity: " + entry.getValue());
			}

			System.out.println("productInfo:" + productInfo);
			System.out.println("itemList:" + itemList);

			req.setAttribute("item", itemList);

			jedis.close();
		}
		
		if ("cart".equals(req.getParameter("goto"))) {
			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			jedis.select(13);
			

			System.out.println("mbrId:" + req.getParameter("mbrId"));

			String mbrId = req.getParameter("mbrId");
			
			
			List<Item> itemList = new ArrayList<>();
			Set<String> itemIds = jedis.hkeys(mbrId);
			
			List<String> quantities = new ArrayList<>();
			
			for(String itemId : itemIds) {
		        String quantity = jedis.hget(mbrId, itemId);
		        quantities.add(quantity);
				Item item = itemService.getItemByItemId(Integer.valueOf(itemId));
				itemList.add(item);
			}

			System.out.println("quantities:"+quantities);
			
			System.out.println("itemList"+itemList);
	
			req.setAttribute("itemList", itemList);
			req.setAttribute("quantities", quantities);


			RequestDispatcher dispatcher = req.getRequestDispatcher("/front_end/item/cartDetail.jsp");
			dispatcher.forward(req, res);
			return;
			}
		}
	}
//}
