package com.twoclothing.redismodel.itembrowsing;

import java.util.List;

public interface ItemBrowsingDAO {
	void insert(ItemBrowsing itemBrowsing);

	List<Integer> getAllItemIdByMbrId(Integer mbrId);
}
