package com.twoclothing.huiwen.service;

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
	public List<Item> getAllItems(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPageTotal() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public List<Item> getItemByCompositeQuery(Map<String, String[]> map) {
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
				continue;
			}
			query.put(key, value);
		}
		
		System.out.println("query :" + query);
		
		return dao.getByCompositeQuery(query);


	}

}
