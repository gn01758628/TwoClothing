package com.twoclothing.chi.service;

import java.util.List;

import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.redismodel.itembrowsing.ItemBrowsing;

public interface ItemBrowsingRedisService {
	void addItemBrowsingRedis(ItemBrowsing itemBrowsing);

	List<Item> getAllByMbrId(Integer mbrId);
}
