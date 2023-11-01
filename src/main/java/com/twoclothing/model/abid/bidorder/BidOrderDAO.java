package com.twoclothing.model.abid.bidorder;

import java.util.List;

public interface BidOrderDAO {

	int insert(BidOrder bidorder);

	BidOrder getByPrimaryKey(Integer bidOrderId);

	List<BidOrder> getAll();

	// 買家
	List<BidOrder> getAllByBuyMbrId(Integer buyMbrId);

	// 賣家
	List<BidOrder> getAllBySellMbrId(Integer sellMbrId);
	
	List<BidOrder> getAllByOrderStatusAndBuyer(Integer orderStatus, Integer buyMbrId);
	    // 買家查詢的實現


	List<BidOrder> getAllByOrderStatusAndSeller(Integer orderStatus, Integer sellMbrId);
	    // 賣家查詢的實現
	
	int update(BidOrder bidorder);
	
	int delete(Integer bidOrderId);

}
