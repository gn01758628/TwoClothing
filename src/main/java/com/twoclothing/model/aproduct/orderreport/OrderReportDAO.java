package com.twoclothing.model.aproduct.orderreport;

import java.util.List;
import java.util.Map;

public interface OrderReportDAO {
	void insert(OrderReport orderReport);
	
	OrderReport getByPrimaryKey(Integer reportId);
	
//	List<OrderReport> getAll();
	
	List<OrderReport> getAll(int currentPage);
	
	List<OrderReport> getAllByMbrId(Integer mbrId, int currentPage);
	
	long getTotal(Integer mbrId);
	
	List<OrderReport> getByCompositeQuery(Map<String, String> map, int currentPage);
	
	int getMapTotal(Map<String, String> map);
	
//	List<OrderReport> getAllByOrderId(Integer orderId);
	
//	List<OrderReport> getAllByEmpId(Integer empId);
	
//	List<OrderReport> getAllByRStatus(Integer rStatus);
	
//	List<OrderReport> getAllByResult(Integer result);
	
//	void update(OrderReport orderReport);
	
	void update(OrderReport orderReport);
}