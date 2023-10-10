package com.twoclothing.web.orderreport;

import java.util.List;

public interface OrderReportDAO {
	void insert(OrderReport orderReport);
	
	OrderReport getByPrimaryKey(Integer reportId);
	
	List<OrderReport> getAll();
	
	List<OrderReport> getAllByRStatus(Integer rStatus);
	
	List<OrderReport> getAllByResult(Integer result);
	
	void update(OrderReport orderReport);
}
