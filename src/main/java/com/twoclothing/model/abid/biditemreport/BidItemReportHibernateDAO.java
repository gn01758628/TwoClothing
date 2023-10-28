package com.twoclothing.model.abid.biditemreport;

import java.util.List;
import java.util.Map;
import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.twoclothing.utils.HibernateUtil;

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
	public List<BidItemReport> getAll() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from BidItemReport", BidItemReport.class).list();
	}

	@Override
	public int insert(BidItemReport bidItemReport) {
		// TODO Auto-generated method stub
		Integer reportId = (Integer) getSession().save(bidItemReport);
		return reportId;
	}

	@Override
	public int update(BidItemReport bidItemReport) {
		// TODO Auto-generated method stub
		try {
			getSession().merge(bidItemReport);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public List<BidItemReport> getByCompositeQuery(Map<String, String> map) {
		// TODO Auto-generated method stub
		if (map.size() == 0)
			return getAll();
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<BidItemReport> criteria = builder.createQuery(BidItemReport.class);
		Root<BidItemReport> root = criteria.from(BidItemReport.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (map.containsKey("startdate") && map.containsKey("enddate"))
			predicates.add(builder.between(root.get("reportDate"), Date.valueOf(map.get("startdate")), Date.valueOf(map.get("enddate"))));
		
		for (Map.Entry<String, String> row : map.entrySet()) {
			
			if ("reportId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("reportId"), row.getValue()));
			}

			if ("bidItemId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("bidItemId"), row.getValue()));
			}
			
			if ("empId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("empId"), row.getValue()));
			}
			
			if ("bidStatus".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("bidStatus"), row.getValue()));
			}
			if ("result".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("result"), row.getValue()));
			}

			if ("startdate".equals(row.getKey())) {
				if (!map.containsKey("enddate"))
					predicates.add(builder.greaterThanOrEqualTo(root.get("reportDate"), Date.valueOf(row.getValue())));
			}

			if ("enddate".equals(row.getKey())) {
				if (!map.containsKey("startdate"))
					predicates.add(builder.lessThanOrEqualTo(root.get("reportDate"), Date.valueOf(row.getValue())));
			}
		}
			criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
			criteria.orderBy(builder.asc(root.get("reportId")));
			TypedQuery<BidItemReport> query = getSession().createQuery(criteria);

			return query.getResultList();
	}

		

	
}
