package com.twoclothing.model.aproduct.itembrowsing;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ItemBrowsingHibernateDAO implements ItemBrowsingDAO {
	private SessionFactory factory;

	public ItemBrowsingHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Serializable insert(ItemBrowsing itemBrowsing) {
		return (Serializable) getSession().save(itemBrowsing);
	}

	@Override
	public List<ItemBrowsing> getAllByMbrId(Integer mbrId, int currentPage) {
		int first = (currentPage - 1) * 10;
		return getSession().createQuery("from ItemBrowsing where mbrId = :mbrId order by browsingTime desc", ItemBrowsing.class)
				.setFirstResult(first).setMaxResults(10).setParameter("mbrId", mbrId).list();
	}

	@Override
	public long getTotal(Integer mbrId) {
		return getSession().createQuery("select count(*) from ItemBrowsing where mbrId = :mbrId", Long.class)
				.setParameter("mbrId", mbrId).uniqueResult();
	}

	@Override
	public ItemBrowsing getByCompositeKey(Integer itemId, Integer mbrId) {
		ItemBrowsing.CompositeDetail compositeKey = new ItemBrowsing.CompositeDetail(itemId, mbrId);
		return getSession().get(ItemBrowsing.class, compositeKey);
	}

	@Override
	public boolean update(ItemBrowsing itemBrowsing) {
		try {
			getSession().update(itemBrowsing);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
