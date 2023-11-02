package com.twoclothing.gordon.service;

import java.util.List;

import com.twoclothing.model.abid.bidorder.BidOrder;

public interface BidOrderService {
	
	BidOrder addBidOrder (BidOrder bidorder);
	
	List<BidOrder> getAll();
	
	BidOrder getByPrimaryKey(Integer bidOrderId);
	
	 List<BidOrder> getAllBuyMbrId (Integer buyMbrId);
	 
	 List<BidOrder> getAllSellMbrId (Integer SellMbrId);
	 
	 List<BidOrder> getAllSellMbrIdAndBuyMbrId(Integer sellMbrId, Integer buyMbrId, Integer bidItemId);

	 
	 List<BidOrder> getAllOrderStatusAndBuyer (Integer orderStatus, Integer buyMbrId);
	
	 List<BidOrder> getAllOrderStatusAndSeller(Integer orderStatus, Integer sellMbrId);
	 
	 BidOrder updateAll(BidOrder bidorder);
	 
	 BidOrder updateBuyStarAndBuyerRatingDesc (Integer buyStar, String buyerRatingDesc);
	 BidOrder updateSellStarAndsellerRatingDesc (Integer sellStar, String sellerRatingDesc);
	 BidOrder updateOrderStatus (Integer orderStatus);
	 
	 Integer deleteBidOrder(Integer bidOrderId);
	 
	 
}
