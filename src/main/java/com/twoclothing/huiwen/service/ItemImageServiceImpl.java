package com.twoclothing.huiwen.service;

import java.util.List;

import com.twoclothing.model.aproduct.itemimage.ItemImage;
import com.twoclothing.model.aproduct.itemimage.ItemImageDAO;
import com.twoclothing.model.aproduct.itemimage.ItemImageHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

public class ItemImageServiceImpl implements ItemImageService {
	
	private ItemImageDAO itemImageDAO ;
	
	public ItemImageServiceImpl() {
		itemImageDAO = new ItemImageHibernateDAO(HibernateUtil.getSessionFactory());

	}

	@Override
	public void addItemImage(ItemImage itemImage) {
		itemImageDAO.insert(itemImage);
		
	}

	@Override
	public void updateItemImage(ItemImage itemImage) {
		itemImageDAO.update(itemImage);
		
	}

	@Override
	public List<ItemImage> getByItemId(Integer itemId) {
		System.out.println(itemImageDAO.getAllByItemId(itemId));
		return itemImageDAO.getAllByItemId(itemId);
	}
	
}
