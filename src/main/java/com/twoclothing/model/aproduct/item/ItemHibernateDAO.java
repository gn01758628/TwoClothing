package com.twoclothing.model.aproduct.item;

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

public class ItemHibernateDAO implements ItemDAO {

	private SessionFactory factory;

	public ItemHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int insert(Item item) {
		return (Integer) getSession().save(item);	
	}
	

	@Override
	public Item getByPrimaryKey(Integer itemId) {
			return getSession().get(Item.class, itemId);
	}

	@Override
	public List<Item> getAll() {		
		return getSession().createQuery("from Item", Item.class).list();			
	}

	@Override
	public List<Item> getAllByTagId(Integer tagId) {
		//???
		return getSession().createQuery("from Item where tagId = :tagId", Item.class)
				.setParameter("tagId", tagId)
				.list();
	}

	@Override
	public List<Item> getAllByMbrId(Integer mbrId) {
		//???
		return getSession().createQuery("from Item where mbrId = :mbrId", Item.class)
				.setParameter("mbrId", mbrId)
				.list();
	}
	

	@Override
	public List<Item> getAllByItemStatus(Integer itemStatus) {
		//???
		return getSession().createQuery("from Item where itemStatus = :itemStatus", Item.class)
				.setParameter("itemStatus", itemStatus)
				.list();
	}

	@Override
	public int update(Item item) {
		try {
			getSession().update(item);
			return 1;
		}catch(Exception e) {
			return -1;
		}
	}

	@Override
	public List<Item> getByCompositeQuery(Map<String, String> map) {
		if(map.size()==0) {
			return getAll();
		}
		

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Item> criteria = builder.createQuery(Item.class);
		Root<Item> root = criteria.from(Item.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (map.containsKey("itemPriceSearchStart") && map.containsKey("itemPriceSearchEnd"))
			predicates.add(builder.between(root.get("price"), new BigDecimal(map.get("itemPriceSearchStart")), new BigDecimal(map.get("endsal"))));

		
		for (Map.Entry<String, String> row : map.entrySet()) {

			if("itemNameSearch".equals(row.getKey())){
				predicates.add(builder.like(root.get("itemName"), "%" + row.getValue() + "%"));
			}
			if("itemPriceSearchStart".equals(row.getKey())){
				predicates.add(builder.greaterThanOrEqualTo(root.get("price"), new BigDecimal(row.getValue())));
			}
			if("itemPriceSearchEnd".equals(row.getKey())){
				predicates.add(builder.greaterThanOrEqualTo(root.get("price"), new BigDecimal(row.getValue())));
			}
			
		}
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("itemId")));
		TypedQuery<Item> query = getSession().createQuery(criteria);

		return query.getResultList();
	}
}
