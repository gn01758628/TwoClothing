package com.twoclothing.model.blacklist;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BlackListHibernateDAO implements BlackListDAO {
	private SessionFactory factory;

	public BlackListHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(BlackList blackList) {
		getSession().save(blackList);
	}

	@Override
	public BlackList getByCompositeKey(Integer mbrId, Integer blackId) {
		BlackList.CompositeDetail compositeKey = new BlackList.CompositeDetail(mbrId, blackId);
		return getSession().get(BlackList.class, compositeKey);
	}

	@Override
	public List<BlackList> getAllByMbrId(Integer mbrId, int currentPage) {
		int first = (currentPage - 1) * 10;
		return getSession().createQuery("from BlackList where mbrId = :mbrId", BlackList.class).setFirstResult(first)
				.setMaxResults(10).setParameter("mbrId", mbrId).list();
	}

	@Override
	public long getTotal(Integer mbrId) {
		return getSession().createQuery("select count(*) from BlackList where mbrId = :mbrId", Long.class)
				.setParameter("mbrId", mbrId).uniqueResult();
	}

	@Override
	public void delete(BlackList blackList) {
		getSession().delete(blackList);
	}
}
