package com.twoclothing.web.itemtracking;

import java.util.List;

public interface ItemTrackingDAO {
	
	public void insert(ItemTracking itemTracking);

    public ItemTracking getByCompositeKey(Integer itemId, Integer mbrId);

    public List<ItemTracking> getAll();

    public List<ItemTracking> getAllByMbrId(Integer mbrId);

    public List<ItemTracking> getAllByItemId(Integer itemId);
}
