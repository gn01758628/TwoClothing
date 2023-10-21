package com.twoclothing.chi.service;

import java.util.List;

import com.twoclothing.model.aproduct.itemtracking.ItemTracking;

public interface ItemTrackingService {
	List<ItemTracking> getAllItemTracking(int currentPage);
	
	int getPageTotal();
	
	List<ItemTracking> getAllByMbrId(Integer mbrId, int currentPage);
	
	ItemTracking getByPrimaryKey(Integer itemId, Integer mbrId);
	
	ItemTracking addItemTracking(ItemTracking itemTracking);

	int deleteItemTracking(Integer itemId, Integer mbrId);
}
