package com.twoclothing.web.biditemreport;

import java.util.List;

import com.twoclothing.web.bidorder.BidOrder;


public interface BidItemReportDAO {
	
	public void insert(BidItemReport bidItemReport);
	
    public BidItemReport getByPrimaryKey(Integer reportId);
    
    public List<BidItemReport> getAll();
    
    public List<BidItemReport> getAllByMbrId(Integer mbrId);
    public List<BidItemReport> getAllByEmpId(Integer empId);
    
    public List<BidItemReport> getAllByBidItemId(Integer bidItemId);
    
    void update(BidItemReport bidItemReport);
    
    
    
 
}
