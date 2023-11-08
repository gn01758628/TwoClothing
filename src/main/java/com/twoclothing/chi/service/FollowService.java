package com.twoclothing.chi.service;

import java.util.List;

import com.twoclothing.model.follow.Follow;

public interface FollowService {
	void addFollow(Follow follow);

	List<Follow> getAllByMbrId(Integer mbrId, int currentPage);
	
	int getPageTotal(Integer mbrId);

	Follow getByPrimaryKey(Integer mbrId, Integer followId);

	void deleteFollow(Follow follow);
}
