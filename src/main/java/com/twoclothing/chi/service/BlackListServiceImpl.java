package com.twoclothing.chi.service;

import java.util.List;

import com.twoclothing.model.blacklist.BlackList;
import com.twoclothing.model.blacklist.BlackListDAO;
import com.twoclothing.model.blacklist.BlackListHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

public class BlackListServiceImpl implements BlackListService {
	private BlackListDAO dao;

	public BlackListServiceImpl() {
		dao = new BlackListHibernateDAO(HibernateUtil.getSessionFactory());
	}

	@Override
	public void addBlackList(BlackList blackList) {
		dao.insert(blackList);
	}

	@Override
	public List<BlackList> getAllByMbrId(Integer mbrId, int currentPage) {
		return dao.getAllByMbrId(mbrId, currentPage);
	}

	@Override
	public int getPageTotal(Integer mbrId) {
		long total = dao.getTotal(mbrId);
		int pageQty = (int) (total % 8 == 0 ? (total / 8) : (total / 8 + 1));
		return pageQty;
	}

	@Override
	public BlackList getByPrimaryKey(Integer mbrId, Integer blackId) {
		BlackList blackList = dao.getByCompositeKey(mbrId, blackId);
		return blackList;
	}

	@Override
	public void deleteBlackList(BlackList blackList) {
		dao.delete(blackList);
	}
}
