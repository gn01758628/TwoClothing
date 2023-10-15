package com.twoclothing.web.members;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import com.twoclothing.utils.HibernateUtil;


public class MembersHibernateDAO implements MembersDAO{
	

	@Override
	public int insert(Members members) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
		session.beginTransaction();
		Integer id = (Integer) session.save(members);
		session.getTransaction().commit();
		return id;
		}catch (ConstraintViolationException e) {
		    // 处理电子邮件重复数据的异常
		    e.printStackTrace();
		    session.getTransaction().rollback();
		    return -1;
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public Members getByPrimaryKey(Integer mbrId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Members members = session.get(Members.class, mbrId);
			session.getTransaction().commit();
			return members;
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	

	@Override
	public List<Members> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Members> list = session.createQuery("from Members", Members.class).list();
			session.getTransaction().commit();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Members> getAllByMbrName(String mbrName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			  List<Members> list = session.createQuery("from Members where mbrname like :mbrname", Members.class)
			            .setParameter("mbrname", "%" + mbrName + "%")
			            .list();
//			List<Members> list = session.createQuery("from mbrName", Members.class).list();
			session.getTransaction().commit();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Members> getAllByMbrStatus(Integer mbrStatus) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Members> list = session.createQuery("from Members where mbrstatus = :mbrstatus", Members.class)
			 .setParameter("mbrstatus", mbrStatus).list();
			session.getTransaction().commit();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Members> getAllBySellScore(Integer sellScore) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Members> list = session.createQuery("from Members where sellscore = :sellscore", Members.class)
			 .setParameter("sellscore", sellScore).list();
			session.getTransaction().commit();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Members> getAllByBuyScore(Integer buyScore) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Members> list = session.createQuery("from Members where buyscore = :buyscore", Members.class)
			 .setParameter("buyscore", buyScore).list();
			session.getTransaction().commit();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public int update(Members members) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
		session.beginTransaction();
		session.update(members);
		session.getTransaction().commit();
		return 1;
	} catch (Exception e) {
		e.printStackTrace();
		session.getTransaction().rollback();
	}
	return -1;
}

	
	
}
