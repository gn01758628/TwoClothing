package com.twoclothing.chi.service;

import java.util.List;

import com.twoclothing.model.blacklist.BlackList;

public interface BlackListService {
	void addBlackList(BlackList blackList);

	List<BlackList> getAllByMbrId(Integer mbrId, int currentPage);
	
	int getPageTotal(Integer mbrId);

	BlackList getByPrimaryKey(Integer mbrId, Integer blackId);

	void deleteBlackList(BlackList blackList);
}
