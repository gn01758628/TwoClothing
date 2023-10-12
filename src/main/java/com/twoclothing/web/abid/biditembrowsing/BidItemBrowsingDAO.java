package com.twoclothing.web.abid.biditembrowsing;

import java.sql.Timestamp;
import java.util.List;

public interface BidItemBrowsingDAO {
	
	void insert(BidItemBrowsing bidItemBrowsing);
	
	BidItemBrowsing getByCompositeKey(Integer mbrId,Integer bidItemId);

	List<BidItemBrowsing> getAll();

	List<BidItemBrowsing> getAllByBidItemId(Integer bidItemId);
	
	List<BidItemBrowsing> getAllByMbrId(Integer mbrId);
	
	void update(Timestamp browsingTime,Integer mbrId,Integer bidItemId);
	
	
	
	
	
}
