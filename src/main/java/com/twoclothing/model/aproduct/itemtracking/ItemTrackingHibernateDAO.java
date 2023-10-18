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
	public int insert(ItemTracking itemTracking) {
		return (Integer) getSession().save(itemTracking);
	}

	@Override
	public ItemTracking getByCompositeKey(Integer itemId, Integer mbrId) {
		ItemTracking.CompositeDetail compositeKey = new ItemTracking.CompositeDetail(itemId, mbrId);
		return getSession().get(ItemTracking.class, compositeKey);
	}

	@Override
	public List<ItemTracking> getAll() {
		return getSession().createQuery("from ItemTracking", ItemTracking.class).list();
	}

	@Override
	public List<ItemTracking> getAllByItemId(Integer itemId) {
		return getSession().createQuery("from ItemTracking where itemId = :itemId", ItemTracking.class)
				.setParameter("itemId", itemId).list();
	}

	@Override
	public List<ItemTracking> getAllByMbrId(Integer mbrId) {
		return getSession().createQuery("from ItemTracking where mbrId = :mbrId", ItemTracking.class)
				.setParameter("mbrId", mbrId).list();
	}

	@Override
	public int delete(Integer itemId, Integer mbrId) {
		ItemTracking.CompositeDetail compositeKey = new ItemTracking.CompositeDetail(itemId, mbrId);
		ItemTracking itemTracking = getSession().get(ItemTracking.class, compositeKey);
		if (itemTracking != null) {
			getSession().delete(itemTracking);
			return 1;
		} else {
			return -1;
		}
	}
}
