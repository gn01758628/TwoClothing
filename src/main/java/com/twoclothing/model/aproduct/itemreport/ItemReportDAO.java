package com.twoclothing.model.aproduct.itemreport;

import java.util.List;
import java.util.Map;

public interface ItemReportDAO {
	void insert(ItemReport itemReport);

	ItemReport getByPrimaryKey(Integer reportId);
	
	List<ItemReport> getAll();

	List<ItemReport> getAll(int currentPage);

	List<ItemReport> getAllByMbrId(Integer mbrId, int currentPage);
	
	List<ItemReport> getAllByMbrId(Integer mbrId);

	long getTotal(Integer mbrId);

	List<ItemReport> getByCompositeQuery(Map<String, String> map, int currentPage);
	
	int getMapTotal(Map<String, String> map);

	void update(ItemReport itemReport);
}