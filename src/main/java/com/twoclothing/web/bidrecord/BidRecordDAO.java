package com.twoclothing.web.bidrecord;

import java.util.List;

public interface BidRecordDAO {
	
	void insert(BidRecord bidRecord);
	
	BidRecord getByPrimaryKey(Integer recordId);

	List<BidRecord> getAll();

	List<BidRecord> getAllByRecordId(Integer recordId);
	
	List<BidRecord> getAllByBidItemId(Integer bidItemId);
	
	List<BidRecord> getAllByMbrId(Integer mbrId);
}
