package com.twoclothing.web.bidorderreport;

import java.util.List;

public interface BidOrderReportDAO {
	
	public void insert(BidOrderReport bidOrderReport);
	
	public BidOrderReport getByPrimaryKey(Integer reportId);
	
	public List<BidOrderReport> getAll();
	
	public List<BidOrderReport> getAllByBidOrderId(Integer bidOrderId);
	
	public void update(BidOrderReport bidOrderReport);
	
	public void delete(Integer reportId);
}
