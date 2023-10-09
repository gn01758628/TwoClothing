package com.twoclothing.web.biditembrowsing;

import java.util.List;

public interface BidItemBrowsingDAO {
	
	void insert(BidItemBrowsing bidItemBrowsing);
	
	BidItemBrowsing getByPrimaryKey(Integer mbrId);

	List<BidItemBrowsing> getAll();

	List<BidItemBrowsing> getAllByBrowsingTime(Integer browsingTime);
	
	List<BidItemBrowsing> getAllByBidItemId(Integer bidItemId);
	
	
	
	
}
