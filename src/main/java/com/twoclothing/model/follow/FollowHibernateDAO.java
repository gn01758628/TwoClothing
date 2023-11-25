package com.twoclothing.model.follow;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class FollowHibernateDAO implements FollowDAO {
	private SessionFactory factory;

	public FollowHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(Follow follow) {
		getSession().save(follow);
	}

	@Override
	public Follow getByCompositeKey(Integer mbrId, Integer followId) {
		Follow.CompositeDetail compositeKey = new Follow.CompositeDetail(mbrId, followId);
		return getSession().get(Follow.class, compositeKey);
	}

	@Override
	public List<Follow> getAllByFollowId(Integer followId) {
		return getSession().createQuery("from Follow where followId = :followId", Follow.class)
				.setParameter("followId", followId).list();
	}

	@Override
	public List<Follow> getAllByMbrId(Integer mbrId, int currentPage) {
		int first = (currentPage - 1) * 10;
		return getSession().createQuery("from Follow where mbrId = :mbrId", Follow.class).setFirstResult(first)
				.setMaxResults(10).setParameter("mbrId", mbrId).list();
	}

	@Override
	public long getTotal(Integer mbrId) {
		return getSession().createQuery("select count(*) from Follow where mbrId = :mbrId", Long.class)
				.setParameter("mbrId", mbrId).uniqueResult();
	}

	@Override
	public void delete(Follow follow) {
		getSession().delete(follow);
	}
}
