package com.twoclothing.chi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.twoclothing.model.abid.biditemreport.BidItemReport;
import com.twoclothing.model.abid.biditemreport.BidItemReportDAO;
import com.twoclothing.model.abid.biditemreport.BidItemReportHibernateDAO;
import com.twoclothing.redismodel.notice.Notice;
import com.twoclothing.redismodel.notice.NoticeDAO;
import com.twoclothing.redismodel.notice.NoticeJedisDAO;
import com.twoclothing.utils.HibernateUtil;

public class BidItemReportServiceImpl implements BidItemReportService {
	private BidItemReportDAO dao;

	private NoticeDAO noticeDAO = new NoticeJedisDAO();

	public BidItemReportServiceImpl() {
		dao = new BidItemReportHibernateDAO(HibernateUtil.getSessionFactory());
	}

	@Override
	public void addBidItemReport(BidItemReport bidItemReport) {
		dao.insert(bidItemReport);
	}

	@Override
	public List<BidItemReport> getAllByMbrId(Integer mbrId, int currentPage) {
		return dao.getAllByMbrId(mbrId, currentPage);
	}

	@Override
	public int getPageTotal(Integer mbrId) {
		long total = dao.getTotal(mbrId);
		int pageQty = (int) (total % 10 == 0 ? (total / 10) : (total / 10 + 1));
		return pageQty;
	}

	@Override
	public BidItemReport getByPrimaryKey(Integer reportId) {
		return dao.getByPrimaryKey(reportId);
	}

	@Override
	public List<BidItemReport> getAll(int currentPage) {
		return dao.getAll(currentPage);
	}

	@Override
	public List<BidItemReport> getByCompositeQuery(Map<String, String[]> map, int currentPage) {
		Map<String, String> query = new HashMap<>();
		Set<Map.Entry<String, String[]>> entry = map.entrySet();

		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();

			if ("action".equals(key)) {
				continue;
			}

			String value = row.getValue()[0];
			if (value.isEmpty() || value == null) {
				continue;
			}
			query.put(key, value);
		}
		System.out.println(query);

		return dao.getByCompositeQuery(query, currentPage);
	}

	@Override
	public int getCompositeQueryPageTotal(Map<String, String[]> map) {
		Map<String, String> query = new HashMap<>();
		Set<Map.Entry<String, String[]>> entry = map.entrySet();

		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();

			if ("action".equals(key)) {
				continue;
			}

			String value = row.getValue()[0];
			if (value.isEmpty() || value == null) {
				continue;
			}
			query.put(key, value);
		}
		System.out.println(query);

		return dao.getMapTotal(query);
	}

	@Override
	public void updateBidItemReport(BidItemReport bidItemReport) {
		dao.update(bidItemReport);
	}

	@Override
	public void addNotice(Notice notice, Integer mbrId) {
		noticeDAO.insert(notice, mbrId);
	}
}
