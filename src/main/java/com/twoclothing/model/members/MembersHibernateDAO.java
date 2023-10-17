package com.twoclothing.model.members;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
//	public List<Members> getAll() {
//			return getSession().createQuery("from Members", Members.class).list();
//	}
	
	public List<Members> getAll() {
		List<Members> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<Members> query = session.createQuery("from Members", Members.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public List<Members> getAllByMbrName(String mbrName) {
			return getSession().createQuery("from Members where mbrname like :mbrname", Members.class)
					.setParameter("mbrname", "%" + mbrName + "%").list();
	}

	@Override
	public List<Members> getAllByMbrStatus(Integer mbrStatus) {
			return getSession().createQuery("from Members where mbrstatus = :mbrstatus", Members.class)
					.setParameter("mbrstatus", mbrStatus).list();
			
	}

	@Override
	public List<Members> getAllBySellScore(Integer sellScore) {
			return getSession().createQuery("from Members where sellscore = :sellscore", Members.class)
					.setParameter("sellscore", sellScore).list();
	}

	@Override
	public List<Members> getAllByBuyScore(Integer buyScore) {
			return getSession().createQuery("from Members where buyscore = :buyscore", Members.class)
					.setParameter("buyscore", buyScore).list();
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
