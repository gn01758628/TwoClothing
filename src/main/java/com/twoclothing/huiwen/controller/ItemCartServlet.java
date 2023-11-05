package com.twoclothing.huiwen.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.twoclothing.model.coupon.Coupon;
import com.twoclothing.model.memberscoupon.MembersCoupon;
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

			String[] itemIds = { itemId };
			String[] quantities = { quantity };

			for (int i = 0; i < itemIds.length; i++) {
				jedis.hset(mbrId, itemIds[i], quantities[i]);
			}

			Map<String, String> productInfo = jedis.hgetAll(mbrId);

			// 打印商品ID与数量
//			for (Map.Entry<String, String> entry : productInfo.entrySet()) {
//				System.out.println("Product ID: " + entry.getKey() + ", Quantity: " + entry.getValue());
//			}

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

			String mbrIdStr = req.getParameter("mbrId");
			
			//抓會員存的商品id
			List<Item> itemList = new ArrayList<>();
			Set<String> itemIds = jedis.hkeys(mbrIdStr);
			
			//抓商品的數量
			List<String> quantities = new ArrayList<>();

			for (String itemId : itemIds) {
				String quantity = jedis.hget(mbrIdStr, itemId);
				quantities.add(quantity);
				Item item = itemService.getItemByItemId(Integer.valueOf(itemId));
				itemList.add(item);
			}
			
			//會員擁有的點數
			Integer mbrId = Integer.valueOf(mbrIdStr);
			Integer mbrPoint = itemService.getMbrPointByMbrId(mbrId);
			

			//取得該會員擁有(且可用)的優惠券(狀態為0:未使用的)
			List<MembersCoupon> membersCouponList = itemService.getMemCouponByMbrId("mbrIdStr", mbrId);
			List<MembersCoupon> elementsToRemove = new ArrayList<>();
			for(MembersCoupon membersCoupon : membersCouponList) {
				int status = membersCoupon.getCouponStatus();
				System.out.println("status"+status);
				if(status != 0) {
					elementsToRemove.add(membersCoupon);
				}
			}
			membersCouponList.removeAll(elementsToRemove);

			System.out.println("尚未使用的會員優惠券"+membersCouponList);
			
			//取得該優惠券的物件(條件:日期未失效)
			List<Coupon> couponList = new ArrayList<>();
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			for(MembersCoupon membersCoupon : membersCouponList) {
				Integer CouponId = membersCoupon.getCompositeKey().getCouponId();
				Coupon coupon = itemService.getCouponByPK(CouponId);
				if((coupon.getExpireDate()) == null || (coupon.getExpireDate()).compareTo(currentTime) > 0 ) {
					couponList.add(coupon);
				}
			}
			
			//取得優惠券的折扣類型
			
	
			System.out.println("couponList:"+couponList);
//			System.out.println("MembersCouponList:"+membersCouponList);
//			System.out.println("mbrPoint"+mbrPoint);
//			System.out.println("quantities:" + quantities);
			System.out.println("itemList" + itemList);

			
			req.setAttribute("itemList", itemList);
			req.setAttribute("quantities", quantities);
			req.setAttribute("mbrPoint", mbrPoint);
			req.setAttribute("MembersCouponList", membersCouponList);
			req.setAttribute("couponList", couponList);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/front_end/item/cartDetail.jsp");
			dispatcher.forward(req, res);
			return;
		}
		
		//使用者刪除購物某商品(Redis刪除)
		if ("delCart".equals(req.getParameter("delCart"))) {
			String itemId = req.getParameter("itemId");
			String mbrId = req.getParameter("mbrId");
			System.out.println("delCart_itemId:"+itemId);
			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			jedis.select(13);
			jedis.hdel(mbrId, itemId);
		}
		
	}
}
