package com.twoclothing.huiwen.service;

import static com.twoclothing.huiwen.Constants.ITEM_PAGE_MAX_RESULT;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.aproduct.item.ItemDAO;
import com.twoclothing.model.aproduct.item.ItemHibernateDAO;
import com.twoclothing.utils.HibernateUtil;


public class ItemServiceImpl implements ItemService{
	
	private ItemDAO dao;
	
	public ItemServiceImpl() {
		dao = new ItemHibernateDAO(HibernateUtil.getSessionFactory());
	}
	
	
	
	@Override
	public Item addItem(String itemName, Integer grade, Integer size, String detail, Integer tagId, Integer mbrId, Integer price, Integer itemStatus, Integer quantity) {
		
		Item item = new Item();		
		item.setItemName(itemName);
		item.setDetail(detail);
		item.setGrade(grade);
		item.setSize(size);
		item.setTagId(tagId);
		item.setMbrId(mbrId);
		item.setPrice(price);
		item.setItemStatus(itemStatus);
		item.setQuantity(quantity);
		dao.insert(item);

		return item;
	}

	@Override
	public Item updateItem(Item item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteItem(Integer itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Item getItemByItemId(Integer itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getAllItems() {
		List<Item> list = dao.getAll();
		System.out.println("service");
		return list;
	}

	@Override
	public List<Item> getAllItems(int page) {
		List<Item> list = dao.getAll();
		return list;
	}
	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		//計算5筆為一頁的話共有幾頁
		int pageQty = (int)(total % ITEM_PAGE_MAX_RESULT == 0 ? (total / ITEM_PAGE_MAX_RESULT) : (total / ITEM_PAGE_MAX_RESULT + 1));
		return pageQty;
	}



	@Override
	public List<Item> getItemByCompositeQuery(Map<String, String[]> map, int pageNow) {
		Map<String, String> query = new HashMap<>();
		// Map.Entry即代表一組key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();
		
		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，做個去除動作
			if ("action".equals(key)) {
				continue;
			}
			// 若是value為空即代表沒有查詢條件，做個去除動作
			String value = row.getValue()[0];
			if ( value == null || value.isEmpty()) {
				System.out.println("Value是空的");
				continue;
			}
			query.put(key, value);
		}
		
		System.out.println("query :" + query);
		
		return dao.getByCompositeQuery(query, pageNow);


	}




}
