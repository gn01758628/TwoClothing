package com.twoclothing.chi.service;

import java.sql.Timestamp;
import java.util.List;

import com.twoclothing.model.aproduct.itembrowsing.ItemBrowsing;

public interface ItemBrowsingService {
	ItemBrowsing addItemBrowsing(ItemBrowsing itemBrowsing);
	
	List<ItemBrowsing> getAllByMbrId(Integer mbrId, int currentPage);
	
	int getPageTotal(Integer mbrId);
	
	ItemBrowsing getByPrimaryKey(Integer itemId, Integer mbrId);

	ItemBrowsing updateItemBrowsing(Integer itemId, Integer mbrId, Timestamp browsingTime);
}
