package com.twoclothing.web.bidorder;

import java.util.List;

public interface BidOrderDAO {

	void insert(BidOrder bidorder);

	BidOrder getByPrimaryKey(Integer bidOrderId);

	List<BidOrder> getAll();

	// 買家
	List<BidOrder> getAllByBuyMbrId(Integer buyMbrId);

	// 賣家
	List<BidOrder> getAllBySellMbrId(Integer sellMbrId);
	
	List<BidOrder> getAllByOrderStatus(Integer orderStatus);

	void update(BidOrder bidorder);

}
