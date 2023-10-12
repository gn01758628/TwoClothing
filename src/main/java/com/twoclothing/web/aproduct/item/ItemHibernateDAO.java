package com.twoclothing.web.aproduct.item;

import java.util.List;

import org.hibernate.Session;

import com.twoclothing.utils.HibernateUtil;

public class ItemHibernateDAO implements ItemDAO{

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
			List<Item> list = session.createQuery("from Item", Item.class).list();
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
			List<Item> list = session.createQuery("from Item order by tagid", Item.class).list();
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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Item> list = session.createQuery("from Item order by mbrid", Item.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Item> getAllByItemStatus(Integer itemStatus) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Item> list = session.createQuery("from Item order by itemstatus", Item.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public int update(Item item) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(item);
			session.getTransaction().commit();
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}
}
