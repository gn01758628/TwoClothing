package com.twoclothing.web.biditemimage;

import java.util.List;

public interface BidItemImageDAO {

	void insert(BidItemImage bidItemImage);
	
	BidItemImage getByPrimaryKey(Integer imageId);

	List<BidItemImage> getAll();

	List<BidItemImage> getAllByBidItemId(Integer bidItemId);

	
	
}
