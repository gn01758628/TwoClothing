package com.twoclothing.web.bidorderreport;

import java.util.List;

import com.twoclothing.web.biditemreport.BidItemReport;

public interface BidOrderReportDAO {
	
	void insert(BidOrderReport bidOrderReport);
	
	BidOrderReport getByPrimaryKey(Integer reportId);
	
	List<BidOrderReport> getAll();
	
	List<BidOrderReport> getAllByBidOrderId(Integer bidOrderId);
	
	List<BidItemReport> getAllByMbrId(Integer mbrId);
	
	List<BidOrderReport> getAllByEmpId(Integer empId);
	
	List<BidOrderReport> getAllByBidStatus(Integer bidStatus);
	
	List<BidOrderReport> getAllByResult(Integer result);
	
	void update(BidOrderReport bidOrderReport);

}
