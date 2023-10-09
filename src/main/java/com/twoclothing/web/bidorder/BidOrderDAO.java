package com.twoclothing.web.bidorder;

import java.util.List;

public interface BidOrderDAO {

	void insert(BidOrder bidorder);

	BidOrder getByPrimaryKey(Integer bidOrderId);

	List<BidOrder> getAll();

	// 買家
	List<BidOrder> getAllByBuyMemberId(Integer buyMbrId);

	// 賣家
	List<BidOrder> getAllBySellMemberId(Integer sellMbrId);
	
	List<BidOrder> getAllByOrderStatus(Integer orderStatus);

	void update(BidOrder bidorder);

}
