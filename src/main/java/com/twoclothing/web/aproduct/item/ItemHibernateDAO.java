package com.twoclothing.web.aproduct.item;

import java.util.List;

import org.hibernate.Session;

import com.twoclothing.utils.HibernateUtil;

public class ItemHibernateDAO implements ItemDAO {

	@Override
	public int insert(Item item) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer itemId = (Integer) session.save(item);
			session.getTransaction().commit();
			return itemId;
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
		
	}

	@Override
	public Item getByPrimaryKey(Integer itemId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Item item = session.get(Item.class, itemId);
			session.getTransaction().commit();
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Item> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Item> list = session.createQuery("from item", Item.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Item> getAllByTagId(Integer tagId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Item> list = session.createQuery("from item order by tagId", Item.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Item> getAllByMbrId(Integer mbrId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getAllByItemStatus(Integer itemStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Integer itemId) {
		// TODO Auto-generated method stub
		
	}
	
	

}
