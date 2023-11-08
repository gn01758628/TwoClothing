package com.twoclothing.model.aproduct.orderreport;

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

public class OrderReportHibernateDAO implements OrderReportDAO {
	private SessionFactory factory;

	public OrderReportHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(OrderReport orderReport) {
		getSession().save(orderReport);
	}

	@Override
	public OrderReport getByPrimaryKey(Integer reportId) {
		return getSession().get(OrderReport.class, reportId);
	}

	@Override
	public List<OrderReport> getAll(int currentPage) {
		int first = (currentPage - 1) * 10;
		return getSession().createQuery("from OrderReport", OrderReport.class).setFirstResult(first).setMaxResults(10)
				.list();
	}

	@Override
	public List<OrderReport> getAllByMbrId(Integer mbrId, int currentPage) {
		int first = (currentPage - 1) * 10;
		return getSession().createQuery("from OrderReport where mbrId = :mbrId", OrderReport.class)
				.setFirstResult(first).setMaxResults(10).setParameter("mbrId", mbrId).list();
	}

	@Override
	public long getTotal(Integer mbrId) {
		if (mbrId != -1) {
			return getSession().createQuery("select count(*) from OrderReport where mbrId = :mbrId", Long.class)
					.setParameter("mbrId", mbrId).uniqueResult();
		} else {
			return getSession().createQuery("select count(*) from OrderReport", Long.class).uniqueResult();
		}
	}

	@Override
	public List<OrderReport> getByCompositeQuery(Map<String, String> map, int currentPage) {
		int first = (currentPage - 1) * 10;

		if (map.size() == 0) {
			return getAll(currentPage);
		}

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<OrderReport> criteria = builder.createQuery(OrderReport.class);
		Root<OrderReport> root = criteria.from(OrderReport.class);

		List<Predicate> predicates = new ArrayList<>();

		for (Map.Entry<String, String> row : map.entrySet()) {
			if ("orderId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("orderId"), new BigDecimal(row.getValue())));
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
		TypedQuery<OrderReport> query = getSession().createQuery(criteria);

		return query.setFirstResult(first).setMaxResults(10).getResultList();
	}

	@Override
	public int getMapTotal(Map<String, String> map) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<OrderReport> criteria = builder.createQuery(OrderReport.class);
		Root<OrderReport> root = criteria.from(OrderReport.class);

		List<Predicate> predicates = new ArrayList<>();

		for (Map.Entry<String, String> row : map.entrySet()) {
			if ("orderId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("orderId"), new BigDecimal(row.getValue())));
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
		TypedQuery<OrderReport> query = getSession().createQuery(criteria);

		List<OrderReport> resultList = query.getResultList();

		int total = resultList.size();
		int pageSize = 10;
		int totalPages = (total > 0) ? (int) Math.ceil((double) total / pageSize) : 1;

		return totalPages;
	}

	@Override
	public void update(OrderReport orderReport) {
		getSession().update(orderReport);
	}
}
