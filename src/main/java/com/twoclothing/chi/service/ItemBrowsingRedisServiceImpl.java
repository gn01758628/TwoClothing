package com.twoclothing.chi.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.aproduct.item.ItemDAO;
import com.twoclothing.model.aproduct.item.ItemHibernateDAO;
import com.twoclothing.redismodel.itembrowsing.ItemBrowsing;
import com.twoclothing.redismodel.itembrowsing.ItemBrowsingDAO;
import com.twoclothing.redismodel.itembrowsing.ItemBrowsingJedisDAO;
import com.twoclothing.utils.HibernateUtil;

public class ItemBrowsingRedisServiceImpl implements ItemBrowsingRedisService {
	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	private final ItemDAO itemDAO = new ItemHibernateDAO(sessionFactory);

	private final ItemBrowsingDAO redis = new ItemBrowsingJedisDAO();

	@Override
	public void addItemBrowsingRedis(ItemBrowsing itemBrowsing) {
		redis.insert(itemBrowsing);
	}

	@Override
	public List<Item> getAllByMbrId(Integer mbrId) {
		List<Integer> itemIdList = redis.getAllItemIdByMbrId(mbrId);

		List<Item> itemList = new ArrayList<>();

		for (Integer itemId : itemIdList) {
			Item item = itemDAO.getByPrimaryKey(itemId);
			if (item != null) {
				itemList.add(item);
			}
		}

		return itemList;
	}
}
