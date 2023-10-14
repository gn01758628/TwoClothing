package com.twoclothing.web.members;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
			  List<Members> list = session.createQuery("from Members where mbrName like :mbrName", Members.class)
			            .setParameter("mbrName", "%" + mbrName + "%")
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Members> getAllBySellScore(Integer sellScore) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Members> getAllByBuyScore(Integer buyScore) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMbrName(Members members) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePSWDHash(Members members) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMbrStatus(Members members) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAvatar(Members members) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateShopImg01(Members members) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateShopImg02(Members members) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMbrPoint(Members members) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBalance(Members members) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBuyStarRating(Members members) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSellStarRating(Members members) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateLastLogin(Members members) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSellScore(Members members) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBuyScore(Members members) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer mbrId) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
