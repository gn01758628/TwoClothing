package com.twoclothing.model.pointhistory;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.twoclothing.model.department.Department;

public class PointHistoryHibernateDAO implements PointHistoryDAO{

	private SessionFactory factory;

	public PointHistoryHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int insert(PointHistory pointHistory) {
		Integer pointId = (Integer) getSession().save(pointHistory);
		return pointId;
		
	}

	@Override
	public PointHistory getByPrimaryKey(Integer pointId) {
		return getSession().get(PointHistory.class, pointId);

	}

	@Override
	public List<PointHistory> getAll() {
		return getSession().createQuery("from PointHistory order by pointId desc", PointHistory.class).list();

	}

	@Override
	public List<PointHistory> getAllByMbrId(Integer mbrId) {
		return getSession().createQuery("from PointHistory where by mbrId = :mbrId order by pointId desc", PointHistory.class).setParameter("mbrId", mbrId).list();

	}

}
