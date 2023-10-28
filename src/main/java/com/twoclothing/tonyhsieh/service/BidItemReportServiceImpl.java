package com.twoclothing.tonyhsieh.service;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.twoclothing.model.abid.biditem.BidItemHibernateDAO;
import com.twoclothing.model.abid.biditemreport.BidItemReport;
import com.twoclothing.model.abid.biditemreport.BidItemReportDAO;
import com.twoclothing.model.abid.biditemreport.BidItemReportHibernateDAO;

import com.twoclothing.utils.HibernateUtil;

public class BidItemReportServiceImpl implements BidItemReportService {

	private BidItemReportDAO bidItemReportDAO;
	
	public BidItemReportServiceImpl() {
		bidItemReportDAO = new BidItemReportHibernateDAO(HibernateUtil.getSessionFactory());
		}

	

	@Override
	public BidItemReport addBidItemReport(Integer bidItemId, Integer mbrId, Integer empId, Timestamp reportDate,
			String bidDescription, Integer bidStatus, Timestamp auditDate, Integer result, String note) {
		// TODO Auto-generated method stub
		BidItemReport bidItemReport = new BidItemReport();
		 bidItemReport.setBidItemId(bidItemId);
		 bidItemReport.setMbrId(mbrId);
		 bidItemReport.setEmpId(empId);
		 bidItemReport.setReportDate(reportDate);;
		 bidItemReport.setBidDescription(bidDescription);
		 bidItemReport.setBidStatus(bidStatus);
		 bidItemReport.setAuditDate(auditDate);
		 bidItemReport.setResult(result);
		 bidItemReport.setNote(note);		
		 bidItemReportDAO.insert(bidItemReport);
		 return bidItemReport;
	}


	@Override
	public BidItemReport update(Integer reportId, Integer bidItemId, Integer mbrId, Integer empId, Timestamp reportDate,
			String bidDescription, Integer bidStatus, Timestamp auditDate, Integer result, String note) {
		// TODO Auto-generated method stub
		BidItemReport bidItemReport = new BidItemReport();
		 bidItemReport.setReportId(reportId);
		 bidItemReport.setBidItemId(bidItemId);
		 bidItemReport.setMbrId(mbrId);
		 bidItemReport.setEmpId(empId);
		 bidItemReport.setReportDate(reportDate);;
		 bidItemReport.setBidDescription(bidDescription);
		 bidItemReport.setBidStatus(bidStatus);
		 bidItemReport.setAuditDate(auditDate);
		 bidItemReport.setResult(result);
		 bidItemReport.setNote(note);		
		 bidItemReportDAO.insert(bidItemReport);
		 return bidItemReport;
	}







	@Override
	public List<BidItemReport> getAll() {
		// TODO Auto-generated method stub
		return bidItemReportDAO.getAll();
	}



	@Override
	public List<BidItemReport> getBidITReportByCompositeQuery(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		Map<String, String> query = new HashMap<>();
		// Map.Entry即代表一組key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();
		
		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，做個去除動作
			if ("action".equals(key)) {
				continue;
			}
			// 若是value為空即代表沒有查詢條件，做個去除動作
			String value = row.getValue()[0];
			if (value.isEmpty() || value == null) {
				continue;
			}
			query.put(key, value);
		}
		
		System.out.println(query);
		
		return bidItemReportDAO.getByCompositeQuery(query);
	}




	
	





  
   
}
