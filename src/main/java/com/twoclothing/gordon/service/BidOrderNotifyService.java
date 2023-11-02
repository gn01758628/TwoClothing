package com.twoclothing.gordon.service;

import java.sql.Timestamp;
import java.util.List;

import com.twoclothing.model.abid.bidordernotify.BidOrderNotify;
import com.twoclothing.model.members.Members;

public interface BidOrderNotifyService {

	
	BidOrderNotify addBidOrderNotify(Integer mbrId, Integer bidOrderId, Timestamp notifyDate, String title, String content);
	
	List<BidOrderNotify> getAll();
	
	BidOrderNotify getByPrimaryKey(Integer notifyId);
	
	List<BidOrderNotify> getByMbrId(Integer mbrId);
	
	List<BidOrderNotify> getByBidOrderId(Integer bidOrderId);

}
