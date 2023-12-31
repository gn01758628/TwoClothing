package com.twoclothing.model.abid.biditemreport;

import java.util.List;
import java.util.Map;

public interface BidItemReportDAO {
	void insert(BidItemReport bidItemReport);

	BidItemReport getByPrimaryKey(Integer reportId);
	
	List<BidItemReport> getAll();

	List<BidItemReport> getAll(int currentPage);

	List<BidItemReport> getAllByMbrId(Integer mbrId, int currentPage);
	
	List<BidItemReport> getAllByMbrId(Integer mbrId);

	long getTotal(Integer mbrId);

	List<BidItemReport> getByCompositeQuery(Map<String, String> map, int currentPage);

	int getMapTotal(Map<String, String> map);

	void update(BidItemReport bidItemReport);
	
//	int insert(BidItemReport bidItemReport);
//	
//	int update(BidItemReport bidItemReport);
//	  
//	List<BidItemReport> getAll();
//
//	List<BidItemReport> getByCompositeQuery(Map<String, String> map);
//    
//	BidItemReport getByPrimaryKey(Integer reportId);
}
