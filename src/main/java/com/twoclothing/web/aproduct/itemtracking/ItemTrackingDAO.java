package com.twoclothing.web.aproduct.itemtracking;

import java.util.List;

public interface ItemTrackingDAO {
	void insert(ItemTracking itemTracking);

    ItemTracking getByCompositeKey(Integer itemId, Integer mbrId);

    List<ItemTracking> getAll();
    
    List<ItemTracking> getAllByItemId(Integer itemId);

    List<ItemTracking> getAllByMbrId(Integer mbrId);

    void delete(Integer itemId, Integer mbrId);
}