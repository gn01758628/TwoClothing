package com.twoclothing.web.biditem;

import java.sql.Timestamp;
import java.util.List;

public interface BiditemDAO {

	   void insert(BidItem biditem);

	   BidItem getByPrimaryKey(Integer bidItemId);

	    List<BidItem> getAll();

	    List<BidItem> getAllByMbrId(Integer mbrId);
	    
	    List<BidItem> getAllByStartTime(Timestamp startTime);
	
	    void delete(Integer bidItemId);
	
}
