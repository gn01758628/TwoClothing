package com.twoclothing.web.bidorderreport;

import java.util.List;

public interface BidOrderReportDAO {
	
	void insert(BidOrderReport bidOrderReport);
	
	BidOrderReport getByPrimaryKey(Integer reportId);
	
	List<BidOrderReport> getAll();
	
	List<BidOrderReport> getAllByBidOrderId(Integer bidOrderId);
	
	List<BidOrderReport> getAllByEmpId(Integer empId);
	
	void update(BidOrderReport bidOrderReport);

}
