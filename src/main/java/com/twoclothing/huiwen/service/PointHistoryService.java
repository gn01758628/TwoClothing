package com.twoclothing.huiwen.service;

import java.util.List;

import com.twoclothing.model.pointhistory.PointHistory;


public interface PointHistoryService {
	
	PointHistory getPHById(Integer pointId);
	
	List<PointHistory> getAllPH();
	
	List<PointHistory> getAllPHByMbrId(Integer mbrId);

	int addPH(PointHistory pointHistory);
}
