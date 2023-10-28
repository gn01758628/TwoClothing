package com.twoclothing.model.abid.biditemreport;

import java.util.List;
import java.util.Map;

import com.twoclothing.model.department.Department;




public interface BidItemReportDAO {
	
	
	
	int insert(BidItemReport bidItemReport);
	
	int update(BidItemReport bidItemReport);
	  
	List<BidItemReport> getAll();

	List<BidItemReport> getByCompositeQuery(Map<String, String> map);
    
  

}
