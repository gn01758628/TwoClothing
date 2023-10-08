package com.twoclothing.web.bidorderratingimage;

import java.util.List;

public interface BidOrderRatingImageDAO {
	public void insert(BidOrderRatingImage bidOrderRatingImage);
	
	public BidOrderRatingImage getByPrimaryKey(Integer imageId);

	public List<BidOrderRatingImage> getAllByBidOrderId(Integer bidOrderId);

	public List<BidOrderRatingImage> getAll();

	public void update(BidOrderRatingImage bidOrderRatingImage);
	

}
