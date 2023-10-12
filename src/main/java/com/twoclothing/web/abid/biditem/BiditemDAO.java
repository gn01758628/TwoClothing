package com.twoclothing.web.abid.biditem;

import java.sql.Timestamp;
import java.util.List;

public interface BiditemDAO {

	   void insert(BidItem biditem);

	   BidItem getByPrimaryKey(Integer bidItemId);

	    List<BidItem> getAll();

	    List<BidItem> getAllByMbrId(Integer mbrId);
	    
	    List<BidItem> getAllByStartTime(Timestamp startTime);
	    
	    List<BidItem> getAllByEndtTime(Timestamp endtTime);
	    
	    List<BidItem> getAllByEmpId(Integer empId);
	    
	    List<BidItem> getAllByTagId(Integer[] tagId);
	
	    void delete(Integer bidItemId);
	
}
