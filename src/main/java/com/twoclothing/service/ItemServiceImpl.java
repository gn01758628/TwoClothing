package com.twoclothing.service;

import java.util.List;

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
	public Item addItem(String itemName, String detail, Integer tagId, Integer mbrId, Integer price, Integer itemStatus, Integer quantity) {
		
		Item item = new Item();		
		item.setItemName(itemName);
		item.setDetail(detail);
		item.setTagId(tagId);
		item.setMbrId(mbrId);
		item.setPrice(price);
		item.setItemStatus(itemStatus);
		item.setQuantity(quantity);
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

}
