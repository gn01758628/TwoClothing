package com.twoclothing.web.biditemreport;

import java.util.List;


public interface BidItemReportDAO {
	
	void insert(BidItemReport bidItemReport);
	
    BidItemReport getByPrimaryKey(Integer reportId);
    
    List<BidItemReport> getAll();
    
    List<BidItemReport> getAllByMbrId(Integer mbrId);

    List<BidItemReport> getAllByEmpId(Integer empId);
    
    List<BidItemReport> getAllByBidItemId(Integer bidItemId);
    
    void update(BidItemReport bidItemReport);

}
