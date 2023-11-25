package com.twoclothing.chi.service;

import java.util.List;
import java.util.Map;

import com.twoclothing.model.aproduct.itemreport.ItemReport;
import com.twoclothing.redismodel.notice.Notice;

public interface ItemReportService {
	void addItemReport(ItemReport itemReport);

	List<ItemReport> getAllByMbrId(Integer mbrId, int currentPage);
	
	List<ItemReport> getAllByMbrId(Integer mbrId);

	int getPageTotal(Integer mbrId);

	ItemReport getByPrimaryKey(Integer reportId);

	List<ItemReport> getAll();
	
	List<ItemReport> getAll(int currentPage);

	List<ItemReport> getByCompositeQuery(Map<String, String[]> map, int currentPage);
	
	int getCompositeQueryPageTotal(Map<String, String[]> map);
	
	void updateItemReport(ItemReport itemReport);
	
	void addNotice(Notice notice, Integer mbrId);
}
