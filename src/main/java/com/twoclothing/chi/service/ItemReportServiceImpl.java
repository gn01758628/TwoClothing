package com.twoclothing.chi.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.twoclothing.model.aproduct.itemreport.ItemReport;
import com.twoclothing.model.aproduct.itemreport.ItemReportDAO;
import com.twoclothing.model.aproduct.itemreport.ItemReportHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

public class ItemReportServiceImpl implements ItemReportService {
	private ItemReportDAO dao;

	public ItemReportServiceImpl() {
		dao = new ItemReportHibernateDAO(HibernateUtil.getSessionFactory());
	}

	@Override
	public ItemReport addItemReport(ItemReport itemReport) {
		dao.insert(itemReport);
		return itemReport;
	}

	@Override
	public List<ItemReport> getAllByMbrId(Integer mbrId, int currentPage) {
		return dao.getAllByMbrId(mbrId, currentPage);
	}

	@Override
	public int getPageTotal(Integer mbrId) {
		long total = dao.getTotal(mbrId);
		int pageQty = (int) (total % 10 == 0 ? (total / 10) : (total / 10 + 1));
		return pageQty;
	}

	@Override
	public ItemReport getByPrimaryKey(Integer reportId) {
		return dao.getByPrimaryKey(reportId);
	}

	@Override
	public List<ItemReport> getAll(int currentPage) {
		return dao.getAll(currentPage);
	}

	@Override
	public List<ItemReport> getByCompositeQuery(Map<String, String[]> map, int currentPage) {
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
	public ItemReport updateItemReport(Integer reportId, Integer empId, Integer rStatus, Timestamp auditdate,
			Integer result, String note) {
		ItemReport itemReport = dao.getByPrimaryKey(reportId);
		if (itemReport != null) {
			itemReport.setEmpId(empId);
			itemReport.setrStatus(rStatus);
			itemReport.setAuditDate(auditdate);
			itemReport.setResult(result);
			itemReport.setNote(note);
			dao.update(itemReport);
		}
		return itemReport;
	}
}
