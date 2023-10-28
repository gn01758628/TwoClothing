package com.twoclothing.model.aproduct.itembrowsing;

import java.io.Serializable;
import java.util.List;

public interface ItemBrowsingDAO {
	Serializable insert(ItemBrowsing itemBrowsing);

	List<ItemBrowsing> getAllByMbrId(Integer mbrId, int currentPage);

	long getTotal(Integer mbrId);
	
	ItemBrowsing getByCompositeKey(Integer itemId, Integer mbrId);

	boolean update(ItemBrowsing itemBrowsing);
}