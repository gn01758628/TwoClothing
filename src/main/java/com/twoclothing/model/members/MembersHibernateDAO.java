package com.twoclothing.model.members;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.twoclothing.utils.HibernateUtil;


@Transactional
public class MembersHibernateDAO implements MembersDAO {

	private SessionFactory factory;

	public MembersHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(Members members) {
			Integer mbrId = (Integer) getSession().save(members);
			return mbrId;
			}
	
	public Members getByPrimaryKey(Integer mbrId) {
			return getSession().get(Members.class, mbrId);
		}

//	@Override
	public List<Members> getAll() {
			return getSession().createQuery("from Members", Members.class).list();
	}				
	
//	public List<Members> getAll() {
//		List<Members> list = null;
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			Query<Members> query = session.createQuery("from Members", Members.class);
//			list = query.getResultList();
//			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}
//		return list;
//	}

	@Override
	public List<Members> getAllByMbrName(String mbrName) {
			return getSession().createQuery("from Members where mbrName like :mbrName ORDER BY mbrId", Members.class)
					.setParameter("mbrName", "%" + mbrName + "%").list();
	}
	
	

	@Override
	public Members getByEmail(String email) {
		return (Members) getSession().createQuery("FROM Members WHERE email = :email")
                .setParameter("email", email)
                .uniqueResult();
	}

	@Override
	public  List<Members>  getAllByEmail(String email) {
		return getSession().createQuery("FROM Members WHERE email like :email ORDER BY mbrId", Members.class)
				.setParameter("email", "%" + email + "%")
				.list();
	}
//	public Members getByEmail(String email) {
//	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//	    Transaction transaction = null;
//	    Members member = null;
//
//	    try {
//	        transaction = session.beginTransaction();
//	        member = (Members) session.createQuery("FROM Members WHERE email = :email")
//	                .setParameter("email", email)
//	                .uniqueResult();
//	        transaction.commit();
//	    } catch (RuntimeException ex) {
//	        if (transaction != null) {
//	            transaction.rollback();
//	        }
//	        throw ex;
//	    }
//
//	    return member;
//	}

	@Override
	public List<Members> getAllByMbrStatus(Integer mbrStatus) {
			return getSession().createQuery("from Members where mbrStatus = :mbrStatus", Members.class)
					.setParameter("mbrStatus", mbrStatus).list();
			
	}

	@Override
	public List<Members> getAllBySellScore(Integer sellScore) {
			return getSession().createQuery("from Members where sellScore = :sellScore", Members.class)
					.setParameter("sellScore", sellScore).list();
	}

	@Override
	public List<Members> getAllByBuyScore(Integer buyScore) {
			return getSession().createQuery("from Members where buyScore = :buyScore", Members.class)
					.setParameter("buyScore", buyScore).list();
	}

	@Override
	public int update(Members members) {
		try {
			getSession().update(members);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}
	


	@Override
	public int delete(Integer mbrId) {
		Members members = getSession().get(Members.class, mbrId);
		if (members != null) {
			getSession().delete(members);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}



}
