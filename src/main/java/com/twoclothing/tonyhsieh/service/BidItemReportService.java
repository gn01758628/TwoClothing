package com.twoclothing.tonyhsieh.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.abid.biditemreport.BidItemReport;


public interface BidItemReportService {

	BidItemReport addBidItemReport(Integer reportId,Integer bidItemId, Integer mbrId, Integer empId, Timestamp reportDate,
			String bidDescription, Integer bidStatus, Timestamp auditDate, Integer result, String note);
	
	BidItemReport update(Integer reportId, Integer bidItemId, Integer mbrId, Integer empId, Timestamp reportDate,
			String bidDescription, Integer bidStatus, Timestamp auditDate, Integer result, String note);

	BidItemReport getByPrimaryKey(Integer reportId);
   
   List<BidItemReport> getAll();

   List<BidItemReport> getBidITReportByCompositeQuery(Map<String, String[]> map);
	
   
   
	
}
