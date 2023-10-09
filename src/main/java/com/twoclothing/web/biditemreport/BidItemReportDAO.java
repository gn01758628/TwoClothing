package com.twoclothing.web.biditemreport;

import java.util.List;

import com.twoclothing.web.bidorderreport.BidOrderReport;


public interface BidItemReportDAO {
	
	void insert(BidItemReport bidItemReport);
	
    BidItemReport getByPrimaryKey(Integer reportId);
    
    List<BidItemReport> getAll();

    List<BidItemReport> getAllByEmpId(Integer empId);
    
    List<BidItemReport> getAllByBidItemId(Integer bidItemId);
    
	List<BidItemReport> getAllByBidStatus(Integer bidStatus);
	
	List<BidItemReport> getAllByResult(Integer result);
	
    
    void update(BidItemReport bidItemReport);

}
