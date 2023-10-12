package com.twoclothing.web.abid.bidrecord;

import java.util.List;

public interface BidRecordDAO {
	
	void insert(BidRecord bidRecord);
	
	BidRecord getByPrimaryKey(Integer recordId);

	List<BidRecord> getAll();

	List<BidRecord> getAllByBidItemId(Integer bidItemId);
	
	List<BidRecord> getAllByMbrId(Integer mbrId);
}
