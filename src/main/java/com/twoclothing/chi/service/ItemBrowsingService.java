package com.twoclothing.chi.service;

import java.util.List;

import com.twoclothing.model.aproduct.itembrowsing.ItemBrowsing;

public interface ItemBrowsingService {
	void addItemBrowsing(ItemBrowsing itemBrowsing);
	
	List<ItemBrowsing> getAllByMbrId(Integer mbrId, int currentPage);
	
	int getPageTotal(Integer mbrId);
	
	ItemBrowsing getByPrimaryKey(Integer itemId, Integer mbrId);

	void updateItemBrowsing(ItemBrowsing itemBrowsing);
}
