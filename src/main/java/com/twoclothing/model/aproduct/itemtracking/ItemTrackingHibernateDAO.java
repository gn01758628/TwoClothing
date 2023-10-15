package com.twoclothing.model.aproduct.itemtracking;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ItemTrackingHibernateDAO implements ItemTrackingDAO {
	private SessionFactory factory;

	public ItemTrackingHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(ItemTracking itemTracking) {
	}

	@Override
	public ItemTracking getByCompositeKey(Integer itemId, Integer mbrId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemTracking> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemTracking> getAllByItemId(Integer itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemTracking> getAllByMbrId(Integer mbrId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer itemId, Integer mbrId) {
		// TODO Auto-generated method stub

	}
}
