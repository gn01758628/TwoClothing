package com.twoclothing.model.aproduct.itemtracking;

import java.util.List;

public interface ItemTrackingDAO {
	int insert(ItemTracking itemTracking);

    ItemTracking getByCompositeKey(Integer itemId, Integer mbrId);

    List<ItemTracking> getAll();
    
    List<ItemTracking> getAllByItemId(Integer itemId);

    List<ItemTracking> getAllByMbrId(Integer mbrId);

    int delete(Integer itemId, Integer mbrId);
}