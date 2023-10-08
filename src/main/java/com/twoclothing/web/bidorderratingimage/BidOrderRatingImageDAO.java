package com.twoclothing.web.bidorderratingimage;

import java.util.List;

public interface BidOrderRatingImageDAO {
	void insert(BidOrderRatingImage bidOrderRatingImage);
	
	BidOrderRatingImage getByPrimaryKey(Integer imageId);

	List<BidOrderRatingImage> getAll();

	List<BidOrderRatingImage> getAllByBidOrderId(Integer bidOrderId);

	void update(BidOrderRatingImage bidOrderRatingImage);

}
