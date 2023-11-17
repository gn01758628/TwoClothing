package com.twoclothing.huiwen.service;

import java.util.List;

import com.twoclothing.model.aproduct.itemimage.ItemImage;

public interface ItemImageService {
	
	public void addItemImage(ItemImage itemImage);
	
	public void updateItemImage(ItemImage itemImage);
	
	List<ItemImage> getByItemId(Integer itemId);

}
