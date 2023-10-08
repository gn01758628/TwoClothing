package com.twoclothing.web.bidorder;

import java.util.List;

public interface BidOrderDAO {

	public void insert(BidOrder bidorder);

	public BidOrder getByPrimaryKey(Integer bidOrderId);

	public List<BidOrder> getAll();

	// 買家
	public List<BidOrder> getAllByBuyMemberId(Integer buyMbrId);

	// 賣家
	public List<BidOrder> getAllBySellMemberId(Integer sellMbrId);

	public void update(BidOrder bidorder);

	

}
