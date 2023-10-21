package com.twoclothing.model.aproduct.itemtracking;

import java.io.Serializable;
import java.util.List;

public interface ItemTrackingDAO {
	Serializable insert(ItemTracking itemTracking);

    ItemTracking getByCompositeKey(Integer itemId, Integer mbrId);
    
    List<ItemTracking> getAll();

    List<ItemTracking> getAll(int currentPage);
    
    long getTotal();

    List<ItemTracking> getAllByMbrId(Integer mbrId, int currentPage);

    int delete(Integer itemId, Integer mbrId);
}