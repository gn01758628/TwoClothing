package com.twoclothing.model.aproduct.itemtracking;

import java.io.Serializable;
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
	public Serializable insert(ItemTracking itemTracking) {
		return (Serializable) getSession().save(itemTracking);
	}

	@Override
	public boolean exists(ItemTracking itemTracking) {
		ItemTracking.CompositeDetail compositeKey = itemTracking.getCompositeKey();
		Long count = getSession()
				.createQuery("select count(*) from ItemTracking it where it.compositeKey = :compositeKey", Long.class)
				.setParameter("compositeKey", compositeKey).uniqueResult();
		return count > 0;
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
	public List<ItemTracking> getAll(int currentPage) {
		int first = (currentPage - 1) * 10;
		return getSession().createQuery("from ItemTracking", ItemTracking.class).setFirstResult(first).setMaxResults(10)
				.list();
	}

	@Override
	public long getTotal(Integer mbrId) {
		if (mbrId != -1) {
			return getSession().createQuery("select count(*) from ItemTracking where mbrId = :mbrId", Long.class)
					.setParameter("mbrId", mbrId).uniqueResult();
		} else {
			return getSession().createQuery("select count(*) from ItemTracking", Long.class).uniqueResult();
		}
	}

	@Override
	public List<ItemTracking> getAllByMbrId(Integer mbrId, int currentPage) {
		int first = (currentPage - 1) * 10;
		return getSession().createQuery("from ItemTracking where mbrId = :mbrId", ItemTracking.class)
				.setFirstResult(first).setMaxResults(10).setParameter("mbrId", mbrId).list();
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
