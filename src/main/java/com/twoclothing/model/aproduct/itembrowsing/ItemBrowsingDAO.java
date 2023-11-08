package com.twoclothing.model.aproduct.itembrowsing;

import java.util.List;

public interface ItemBrowsingDAO {
	void insert(ItemBrowsing itemBrowsing);

	List<ItemBrowsing> getAllByMbrId(Integer mbrId, int currentPage);

	long getTotal(Integer mbrId);
	
	ItemBrowsing getByCompositeKey(Integer itemId, Integer mbrId);

	void update(ItemBrowsing itemBrowsing);
}