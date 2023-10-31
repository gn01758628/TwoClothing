package com.twoclothing.model.abid.bidorderratingimage;

import java.util.List;

public interface BidOrderRatingImageDAO {
	
	int insert(BidOrderRatingImage bidOrderRatingImage);
	
	List<BidOrderRatingImage> getAll();
	
	BidOrderRatingImage getByPrimaryKey(Integer imageId);

	List<BidOrderRatingImage> getAllByBidOrderId(Integer bidOrderId);


}
