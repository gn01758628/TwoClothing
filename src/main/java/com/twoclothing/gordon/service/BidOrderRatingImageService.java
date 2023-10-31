package com.twoclothing.gordon.service;

import java.util.List;

import com.twoclothing.model.abid.bidorderratingimage.BidOrderRatingImage;

public interface BidOrderRatingImageService {
	
	BidOrderRatingImage addBidOrderRatingImage(Integer bidOrderId, byte[] image);


	BidOrderRatingImage getByPrimaryKey(Integer imageId);

	
	List<BidOrderRatingImage> getAll();


	List<BidOrderRatingImage> getByBidOrderId(Integer bidOrderId);









}
