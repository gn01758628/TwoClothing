package com.twoclothing.web.biditembrowsing;

import java.util.List;

public interface BidItemBrowsingDAO {
	
	void insert(BidItemBrowsing bidItemBrowsing);
	
	BidItemBrowsing getByCompositeKey(Integer mbrId,Integer bidItemId);

	List<BidItemBrowsing> getAll();

	List<BidItemBrowsing> getAllByBidItemId(Integer bidItemId);
	
	List<BidItemBrowsing> getAllByMbrId(Integer mbrId);
	
	
	
	
}
