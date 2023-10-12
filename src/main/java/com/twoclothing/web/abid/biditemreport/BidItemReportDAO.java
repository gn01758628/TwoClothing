package com.twoclothing.web.abid.biditemreport;

import java.util.List;


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
