package com.twoclothing.web.aproduct.item;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.twoclothing.utils.HibernateUtil;

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
			Integer itemId = (Integer) getSession().save(item);			
			return itemId;
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
		return getSession().createQuery("from Item where tagId = 1", Item.class).list();
	}

	@Override
	public List<Item> getAllByMbrId(Integer mbrId) {
		//???
		return getSession().createQuery("from Item where mbrId = 1", Item.class).list();
	}
	

	@Override
	public List<Item> getAllByItemStatus(Integer itemStatus) {
		//???
		return getSession().createQuery("from Item where itemStatus = 1", Item.class).list();
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
}
