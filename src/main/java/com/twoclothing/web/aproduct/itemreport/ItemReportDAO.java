package com.twoclothing.web.aproduct.itemreport;

import java.util.List;

public interface ItemReportDAO {
	void insert(ItemReport itemReport);

	ItemReport getByPrimaryKey(Integer reportId);

	List<ItemReport> getAll();

	List<ItemReport> getAllByItemId(Integer itemId);

	List<ItemReport> getAllByEmpId(Integer empId);

	List<ItemReport> getAllByRStatus(Integer rStatus);

	List<ItemReport> getAllByResult(Integer result);

	void update(ItemReport itemReport);
}