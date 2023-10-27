package com.twoclothing.huiwen.service;

import java.util.List;

import com.twoclothing.model.balancehistory.BalanceHistory;

public interface BalanceHistoryService {
	
	BalanceHistory getBHById(Integer balanceId);
	
	List<BalanceHistory> getAllBH();
	
	List<BalanceHistory> getAllBHByMbrId(Integer mbrId);

	int addBH(BalanceHistory balanceHistory);
}
