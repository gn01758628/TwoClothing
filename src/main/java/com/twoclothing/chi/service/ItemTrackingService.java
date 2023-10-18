package com.twoclothing.chi.service;

import java.util.List;

import com.twoclothing.model.aproduct.itemtracking.ItemTracking;

public interface ItemTrackingService {
	ItemTracking addItemTracking(ItemTracking itemTracking);

	int deleteItemTracking(Integer itemId, Integer mbrId);

	ItemTracking getByPrimaryKey(Integer itemId, Integer mbrId);
}
