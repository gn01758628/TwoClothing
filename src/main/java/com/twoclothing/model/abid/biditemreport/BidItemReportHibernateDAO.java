package com.twoclothing.model.abid.biditemreport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Transactional
public class BidItemReportHibernateDAO implements BidItemReportDAO {
	private SessionFactory factory;

	public BidItemReportHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(BidItemReport bidItemReport) {
		getSession().save(bidItemReport);
	}

	@Override
	public BidItemReport getByPrimaryKey(Integer reportId) {
		return getSession().get(BidItemReport.class, reportId);
	}

	@Override
	public List<BidItemReport> getAll() {
		return getSession().createQuery("from BidItemReport", BidItemReport.class).list();
	}

	@Override
	public List<BidItemReport> getAll(int currentPage) {
		int first = (currentPage - 1) * 10;
		return getSession().createQuery("from BidItemReport", BidItemReport.class).setFirstResult(first)
				.setMaxResults(10).list();
	}

	@Override
	public List<BidItemReport> getAllByMbrId(Integer mbrId, int currentPage) {
		int first = (currentPage - 1) * 10;
		return getSession().createQuery("from BidItemReport where mbrId = :mbrId", BidItemReport.class)
				.setFirstResult(first).setMaxResults(10).setParameter("mbrId", mbrId).list();
	}

	@Override
	public List<BidItemReport> getAllByMbrId(Integer mbrId) {
		return getSession().createQuery("from BidItemReport where mbrId = :mbrId", BidItemReport.class)
				.setParameter("mbrId", mbrId).list();
	}

	@Override
	public long getTotal(Integer mbrId) {
		if (mbrId != -1) {
			return getSession().createQuery("select count(*) from BidItemReport where mbrId = :mbrId", Long.class)
					.setParameter("mbrId", mbrId).uniqueResult();
		} else {
			return getSession().createQuery("select count(*) from BidItemReport", Long.class).uniqueResult();
		}
	}

	@Override
	public List<BidItemReport> getByCompositeQuery(Map<String, String> map, int currentPage) {
		int first = (currentPage - 1) * 10;

		if (map.size() == 0) {
			return getAll(currentPage);
		}

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<BidItemReport> criteria = builder.createQuery(BidItemReport.class);
		Root<BidItemReport> root = criteria.from(BidItemReport.class);

		List<Predicate> predicates = new ArrayList<>();

		for (Map.Entry<String, String> row : map.entrySet()) {
			if ("bidItemId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("bidItemId"), new BigDecimal(row.getValue())));
			}
			if ("mbrId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("mbrId"), new BigDecimal(row.getValue())));
			}
			if ("empId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("empId"), new BigDecimal(row.getValue())));
			}

			if ("BidStatus".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("BidStatus"), new BigDecimal(row.getValue())));
			}
			if ("result".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("result"), new BigDecimal(row.getValue())));
			}
		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("reportId")));
		TypedQuery<BidItemReport> query = getSession().createQuery(criteria);

		return query.setFirstResult(first).setMaxResults(10).getResultList();
	}

	@Override
	public int getMapTotal(Map<String, String> map) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<BidItemReport> criteria = builder.createQuery(BidItemReport.class);
		Root<BidItemReport> root = criteria.from(BidItemReport.class);

		List<Predicate> predicates = new ArrayList<>();

		for (Map.Entry<String, String> row : map.entrySet()) {
			if ("BidItemId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("BidItemId"), new BigDecimal(row.getValue())));
			}
			if ("mbrId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("mbrId"), new BigDecimal(row.getValue())));
			}
			if ("empId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("empId"), new BigDecimal(row.getValue())));
			}

			if ("BidStatus".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("BidStatus"), new BigDecimal(row.getValue())));
			}
			if ("result".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("result"), new BigDecimal(row.getValue())));
			}
		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("reportId")));
		TypedQuery<BidItemReport> query = getSession().createQuery(criteria);

		List<BidItemReport> resultList = query.getResultList();

		int total = resultList.size();
		int pageSize = 10;
		int totalPages = (total > 0) ? (int) Math.ceil((double) total / pageSize) : 1;

		return totalPages;
	}

	@Override
	public void update(BidItemReport bidItemReport) {
		getSession().update(bidItemReport);
	}

//	@Override
//	public List<BidItemReport> getAll() {
//		// TODO Auto-generated method stub
//		return getSession().createQuery("from BidItemReport", BidItemReport.class).list();
//		List<BidItemReport> list = null;
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			Query<BidItemReport> query = session.createQuery("from BidItemReport", BidItemReport.class);
//			list = query.getResultList();
//			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}
//		return list;
//	}

//	@Override
//	public int insert(BidItemReport bidItemReport) {
//		// TODO Auto-generated method stub
//		Integer reportId = (Integer) getSession().save(bidItemReport);
//		return reportId;
//		Integer reportId = null;
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			reportId = (Integer) getSession().save(bidItemReport);
//			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}
//		return reportId;
//	}

//	@Override
//	public int update(BidItemReport bidItemReport) {
//		// TODO Auto-generated method stub
//		try {
//			getSession().update(bidItemReport);
//			return 1;
//		} catch (Exception e) {
//			return -1;
//		}
//	}

//	@Override
//	public BidItemReport getByPrimaryKey(Integer reportId) {
//		// TODO Auto-generated method stub
//		return getSession().get(BidItemReport.class, reportId);
//	}

//	@Override
//	public List<BidItemReport> getByCompositeQuery(Map<String, String> map) {
//		// TODO Auto-generated method stub
//		if (map.size() == 0)
//			return getAll();
//		CriteriaBuilder builder = getSession().getCriteriaBuilder();
//		CriteriaQuery<BidItemReport> criteria = builder.createQuery(BidItemReport.class);
//		Root<BidItemReport> root = criteria.from(BidItemReport.class);
//		
//		List<Predicate> predicates = new ArrayList<>();
//		
//		if (map.containsKey("startdate") && map.containsKey("enddate"))
//			predicates.add(builder.between(root.get("reportDate"), Date.valueOf(map.get("startdate")), Date.valueOf(map.get("enddate"))));
//		
//		for (Map.Entry<String, String> row : map.entrySet()) {
//			
//			if ("reportId".equals(row.getKey())) {
//				predicates.add(builder.equal(root.get("reportId"), row.getValue()));
//			}
//
//			if ("bidItemId".equals(row.getKey())) {
//				predicates.add(builder.equal(root.get("bidItemId"), row.getValue()));
//			}
//			
//			if ("empId".equals(row.getKey())) {
//				predicates.add(builder.equal(root.get("empId"), row.getValue()));
//			}
//			
//			if ("bidStatus".equals(row.getKey())) {
//				predicates.add(builder.equal(root.get("bidStatus"), row.getValue()));
//			}
//			if ("result".equals(row.getKey())) {
//				predicates.add(builder.equal(root.get("result"), row.getValue()));
//			}
//
//			if ("startdate".equals(row.getKey())) {
//				if (!map.containsKey("enddate"))
//					predicates.add(builder.greaterThanOrEqualTo(root.get("reportDate"), Date.valueOf(row.getValue())));
//			}
//
//			if ("enddate".equals(row.getKey())) {
//				if (!map.containsKey("startdate"))
//					predicates.add(builder.lessThanOrEqualTo(root.get("reportDate"), Date.valueOf(row.getValue())));
//			}
//		}
//			criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
//			criteria.orderBy(builder.asc(root.get("reportId")));
//			TypedQuery<BidItemReport> query = getSession().createQuery(criteria);
//
//			return query.getResultList();
//	}

}
