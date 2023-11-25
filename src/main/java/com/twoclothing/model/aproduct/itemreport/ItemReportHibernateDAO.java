package com.twoclothing.model.aproduct.itemreport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ItemReportHibernateDAO implements ItemReportDAO {
	private SessionFactory factory;

	public ItemReportHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(ItemReport itemReport) {
		getSession().save(itemReport);
	}

	@Override
	public ItemReport getByPrimaryKey(Integer reportId) {
		return getSession().get(ItemReport.class, reportId);
	}

	@Override
	public List<ItemReport> getAll() {
		return getSession().createQuery("from ItemReport", ItemReport.class).list();
	}

	@Override
	public List<ItemReport> getAll(int currentPage) {
		int first = (currentPage - 1) * 10;
		return getSession().createQuery("from ItemReport", ItemReport.class).setFirstResult(first).setMaxResults(10)
				.list();
	}

	@Override
	public List<ItemReport> getAllByMbrId(Integer mbrId, int currentPage) {
		int first = (currentPage - 1) * 10;
		return getSession().createQuery("from ItemReport where mbrId = :mbrId", ItemReport.class).setFirstResult(first)
				.setMaxResults(10).setParameter("mbrId", mbrId).list();
	}

	@Override
	public List<ItemReport> getAllByMbrId(Integer mbrId) {
		return getSession().createQuery("from ItemReport where mbrId = :mbrId", ItemReport.class)
				.setParameter("mbrId", mbrId).list();
	}

	@Override
	public long getTotal(Integer mbrId) {
		if (mbrId != -1) {
			return getSession().createQuery("select count(*) from ItemReport where mbrId = :mbrId", Long.class)
					.setParameter("mbrId", mbrId).uniqueResult();
		} else {
			return getSession().createQuery("select count(*) from ItemReport", Long.class).uniqueResult();
		}
	}

	@Override
	public List<ItemReport> getByCompositeQuery(Map<String, String> map, int currentPage) {
		int first = (currentPage - 1) * 10;

		if (map.size() == 0) {
			return getAll(currentPage);
		}

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<ItemReport> criteria = builder.createQuery(ItemReport.class);
		Root<ItemReport> root = criteria.from(ItemReport.class);

		List<Predicate> predicates = new ArrayList<>();

		for (Map.Entry<String, String> row : map.entrySet()) {
			if ("reportId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("reportId"), new BigDecimal(row.getValue())));
			}
			if ("mbrId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("mbrId"), new BigDecimal(row.getValue())));
			}
			if ("empId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("empId"), new BigDecimal(row.getValue())));
			}

			if ("rStatus".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("rStatus"), new BigDecimal(row.getValue())));
			}
			if ("result".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("result"), new BigDecimal(row.getValue())));
			}
		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("reportId")));
		TypedQuery<ItemReport> query = getSession().createQuery(criteria);

		return query.setFirstResult(first).setMaxResults(10).getResultList();
	}

	public int getMapTotal(Map<String, String> map) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<ItemReport> criteria = builder.createQuery(ItemReport.class);
		Root<ItemReport> root = criteria.from(ItemReport.class);

		List<Predicate> predicates = new ArrayList<>();

		for (Map.Entry<String, String> row : map.entrySet()) {
			if ("reportId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("reportId"), new BigDecimal(row.getValue())));
			}
			if ("mbrId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("mbrId"), new BigDecimal(row.getValue())));
			}
			if ("empId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("empId"), new BigDecimal(row.getValue())));
			}

			if ("rStatus".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("rStatus"), new BigDecimal(row.getValue())));
			}
			if ("result".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("result"), new BigDecimal(row.getValue())));
			}
		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("reportId")));
		TypedQuery<ItemReport> query = getSession().createQuery(criteria);

		List<ItemReport> resultList = query.getResultList();

		int total = resultList.size();
		int pageSize = 10;
		int totalPages = (total > 0) ? (int) Math.ceil((double) total / pageSize) : 1;

		return totalPages;
	}

	@Override
	public void update(ItemReport itemReport) {
		getSession().update(itemReport);
	}
}
