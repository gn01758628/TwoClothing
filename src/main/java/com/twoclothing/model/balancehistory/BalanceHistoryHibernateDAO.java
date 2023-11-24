package com.twoclothing.model.balancehistory;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.twoclothing.model.pointhistory.PointHistory;

public class BalanceHistoryHibernateDAO implements BalanceHistoryDAO{

	private SessionFactory factory;

	public BalanceHistoryHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	@Override
	public int insert(BalanceHistory balanceHistory) {
		Integer balanceId = (Integer) getSession().save(balanceHistory);
		return balanceId;
	}

	@Override
	public BalanceHistory getByPrimaryKey(Integer balanceId) {
		return getSession().get(BalanceHistory.class, balanceId);

	}

	@Override
	public List<BalanceHistory> getAll() {
		return getSession().createQuery("from BalanceHistory", BalanceHistory.class).list();

	}

	@Override
	public List<BalanceHistory> getAllByMbrId(Integer mbrId) {
		return getSession().createQuery("from BalanceHistory where mbrId = :mbrId order by balanceId desc", BalanceHistory.class).setParameter("mbrId", mbrId).list();

	}

}
