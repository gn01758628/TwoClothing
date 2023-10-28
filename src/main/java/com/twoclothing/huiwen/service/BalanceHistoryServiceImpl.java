package com.twoclothing.huiwen.service;

import java.util.List;

import com.twoclothing.model.balancehistory.BalanceHistory;
import com.twoclothing.model.balancehistory.BalanceHistoryHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

public class BalanceHistoryServiceImpl implements BalanceHistoryService{
	
	private BalanceHistoryHibernateDAO BHDAO;
	
	public BalanceHistoryServiceImpl() {
		BHDAO = new BalanceHistoryHibernateDAO(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public BalanceHistory getBHById(Integer balanceId) {
		return BHDAO.getByPrimaryKey(balanceId);

	}

	@Override
	public List<BalanceHistory> getAllBH() {
		return BHDAO.getAll();
	}

	@Override
	public List<BalanceHistory> getAllBHByMbrId(Integer mbrId) {
		return BHDAO.getAllByMbrId(mbrId);
	}

	@Override
	public int addBH(BalanceHistory balanceHistory) {
		return BHDAO.insert(balanceHistory);
	}

}
