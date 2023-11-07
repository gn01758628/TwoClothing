package com.twoclothing.chi.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.twoclothing.model.aproduct.itemreport.ItemReport;

public interface ItemReportService {
	ItemReport addItemReport(ItemReport itemReport);

	List<ItemReport> getAllByMbrId(Integer mbrId, int currentPage);

	int getPageTotal(Integer mbrId);

	ItemReport getByPrimaryKey(Integer reportId);

	List<ItemReport> getAll(int currentPage);

	List<ItemReport> getByCompositeQuery(Map<String, String[]> map, int currentPage);
	
	int getCompositeQueryPageTotal(Map<String, String[]> map);
	
	ItemReport updateItemReport(Integer reportId, Integer empId, Integer rStatus, Timestamp auditdate, Integer result, String note);
}
