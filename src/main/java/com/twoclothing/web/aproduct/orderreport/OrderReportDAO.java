package com.twoclothing.web.aproduct.orderreport;

import java.util.List;

public interface OrderReportDAO {
	void insert(OrderReport orderReport);
	
	OrderReport getByPrimaryKey(Integer reportId);
	
	List<OrderReport> getAll();
	
	List<OrderReport> getAllByOrderId(Integer orderId);
	
	List<OrderReport> getAllByEmpId(Integer empId);
	
	List<OrderReport> getAllByRStatus(Integer rStatus);
	
	List<OrderReport> getAllByResult(Integer result);
	
	void update(OrderReport orderReport);
}
