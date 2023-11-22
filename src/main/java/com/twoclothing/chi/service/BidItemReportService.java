package com.twoclothing.chi.service;

import java.util.List;
import java.util.Map;

import com.twoclothing.model.abid.biditemreport.BidItemReport;
import com.twoclothing.redismodel.notice.Notice;

public interface BidItemReportService {
	void addBidItemReport(BidItemReport bidItemReport);

	List<BidItemReport> getAllByMbrId(Integer mbrId, int currentPage);

	int getPageTotal(Integer mbrId);

	BidItemReport getByPrimaryKey(Integer reportId);

	List<BidItemReport> getAll(int currentPage);

	List<BidItemReport> getByCompositeQuery(Map<String, String[]> map, int currentPage);

	int getCompositeQueryPageTotal(Map<String, String[]> map);

	void updateBidItemReport(BidItemReport bidItemReport);

	void addNotice(Notice notice, Integer mbrId);
}
