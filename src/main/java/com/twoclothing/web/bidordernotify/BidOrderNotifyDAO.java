package com.twoclothing.web.bidordernotify;

import java.util.List;

public interface BidOrderNotifyDAO {

	public void insert(BidOrderNotify bidOrderNotif);

	public BidOrderNotify getByPrimaryKey(Integer notifyId);

	public List<BidOrderNotify> getAll();

	public List<BidOrderNotify> getAllByMemberId(Integer mbrId);

	public List<BidOrderNotify> getAllByBidOrderId(Integer bidOrderId);

	public void update(BidOrderNotify bidOrderNotify);
	

}
