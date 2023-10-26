package com.twoclothing.huiwen.service;

import java.util.List;

import com.twoclothing.model.aproduct.itemorder.ItemOrder;
import com.twoclothing.model.members.MembersDAO;
import com.twoclothing.model.members.MembersHibernateDAO;
import com.twoclothing.model.pointhistory.PointHistory;
import com.twoclothing.model.pointhistory.PointHistoryHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

public class PointHistoryServiceImpl implements PointHistoryService{
	private PointHistoryHibernateDAO PHDAO;
	private MembersDAO members;
	private ItemOrder order;
	
	public PointHistoryServiceImpl() {
		members = new MembersHibernateDAO(HibernateUtil.getSessionFactory());
		PHDAO = new PointHistoryHibernateDAO(HibernateUtil.getSessionFactory());
//		order = new ItemOrderHibernateDAO(HibernateUtil.getSessionFactory());
	}
	@Override
	public PointHistory getPHById(Integer pointId) {
		return PHDAO.getByPrimaryKey(pointId);
	}

	@Override
	public List<PointHistory> getAllPH() {
		return PHDAO.getAll();
	}

	@Override
	public List<PointHistory> getAllPHByMbrId(Integer mbrId) {
		return PHDAO.getAllByMbrId(mbrId);

	}

	@Override
	public int addPH(PointHistory pointHistory) {
		return PHDAO.insert(pointHistory);
	}

}
