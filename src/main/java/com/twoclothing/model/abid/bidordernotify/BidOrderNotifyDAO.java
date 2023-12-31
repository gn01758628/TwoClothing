package com.twoclothing.model.abid.bidordernotify;

import java.util.List;

public interface BidOrderNotifyDAO {

	int insert(BidOrderNotify bidOrderNotif);

	BidOrderNotify getByPrimaryKey(Integer notifyId);

	List<BidOrderNotify> getAll();

	List<BidOrderNotify> getAllByMbrId(Integer mbrId);

	List<BidOrderNotify> getAllByBidOrderId(Integer bidOrderId);

	//void update(BidOrderNotify bidOrderNotify);
	

}
