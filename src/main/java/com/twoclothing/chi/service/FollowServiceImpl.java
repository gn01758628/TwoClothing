package com.twoclothing.chi.service;

import java.util.List;

import com.twoclothing.model.follow.Follow;
import com.twoclothing.model.follow.FollowDAO;
import com.twoclothing.model.follow.FollowHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

public class FollowServiceImpl implements FollowService {
	private FollowDAO dao;

	public FollowServiceImpl() {
		dao = new FollowHibernateDAO(HibernateUtil.getSessionFactory());
	}

	@Override
	public void addFollow(Follow follow) {
		dao.insert(follow);
	}

	@Override
	public List<Follow> getAllByMbrId(Integer mbrId, int currentPage) {
		return dao.getAllByMbrId(mbrId, currentPage);
	}

	@Override
	public int getPageTotal(Integer mbrId) {
		long total = dao.getTotal(mbrId);
		int pageQty = (int) (total % 10 == 0 ? (total / 10) : (total / 10 + 1));
		return pageQty;
	}

	@Override
	public Follow getByPrimaryKey(Integer mbrId, Integer followId) {
		Follow follow = dao.getByCompositeKey(mbrId, followId);
		return follow;
	}

	@Override
	public void deleteFollow(Follow follow) {
		dao.delete(follow);
	}
}
