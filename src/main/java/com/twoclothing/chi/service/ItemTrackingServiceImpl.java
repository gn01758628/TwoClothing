package com.twoclothing.chi.service;

import com.twoclothing.model.aproduct.itemtracking.ItemTracking;
import com.twoclothing.model.aproduct.itemtracking.ItemTrackingDAO;
import com.twoclothing.model.aproduct.itemtracking.ItemTrackingHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

public class ItemTrackingServiceImpl implements ItemTrackingService {
	private ItemTrackingDAO dao;

	public ItemTrackingServiceImpl() {
		dao = new ItemTrackingHibernateDAO(HibernateUtil.getSessionFactory());
	}

	@Override
	public ItemTracking addItemTracking(ItemTracking itemTracking) {
		dao.insert(itemTracking);
		return itemTracking;
	}

	@Override
	public int deleteItemTracking(Integer itemId, Integer mbrId) {
		int result = dao.delete(itemId, mbrId);
		if (result == 1) {
			return 1;
		} else if (result == -1) {
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public ItemTracking getByPrimaryKey(Integer itemId, Integer mbrId) {
		ItemTracking itemTracking = dao.getByCompositeKey(itemId, mbrId);
		return itemTracking;
	}
}
