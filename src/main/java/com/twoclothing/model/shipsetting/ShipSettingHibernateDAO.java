package com.twoclothing.model.shipsetting;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.twoclothing.utils.HibernateUtil;

@Transactional
public class ShipSettingHibernateDAO implements ShipSettingDAO{

	private SessionFactory factory;
	
	public ShipSettingHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}
	
//	@Transactional
//	@Override
//	public int insert(ShipSetting shipSetting) {
//		Integer shipId = (Integer) getSession().save(shipSetting);
//		
//		return shipId;
//		
//	}
	public int insert(ShipSetting shipSetting) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction transaction = null;
	    Integer shipId = null;
	    try {
	        transaction = session.beginTransaction();
	         shipId = (Integer) getSession().save(shipSetting);
	        transaction.commit();
	    } catch (RuntimeException ex) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        throw ex;
	    }
	    return  shipId;
	}
	
	
	@Transactional
	@Override
	public ShipSetting getByPrimaryKey(Integer shipId) {
		return getSession().get(ShipSetting.class, shipId);
	}
	@Transactional
	@Override
	public List<ShipSetting> getAll() {
		return getSession().createQuery("from ShipSetting", ShipSetting.class).list();
	}
	@Transactional
	@Override
	public List<ShipSetting> getAllByMbrId(Integer mbrId) {
		return getSession().createQuery("from ShipSetting where mbrId = :mbrId", ShipSetting.class)
				.setParameter("mbrId", mbrId).list();
	}
	
//	public List<ShipSetting> getAllByMbrId(Integer mbrId) {
//	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//	    Transaction transaction = null;
//	    List<ShipSetting> shipSetting = null;
//	    
//	    try {
//	        transaction = session.beginTransaction();
//	        shipSetting =  session.createQuery("from ShipSetting where mbrId = :mbrId", ShipSetting.class)
//	                .setParameter("mbrId", mbrId)
//	                .list();
//	        transaction.commit();
//	    } catch (RuntimeException ex) {
//	        if (transaction != null) {
//	            transaction.rollback();
//	        }
//	        throw ex;
//	    }
//	    return  shipSetting;
//	}
	@Transactional
	@Override
	public int update(ShipSetting shipSetting) {
		try {
			getSession().update(shipSetting);
			return 1;
		}catch (Exception e) {
			return -1;
		}
		
	}
//	@Transactional
//	@Override
//	public int delete(Integer shipId) {
//		ShipSetting shipSetting = getSession().get(ShipSetting.class, shipId);
//		if (shipSetting != null) {
//			getSession().delete(shipSetting);
//			// 回傳給 service，1代表刪除成功
//			return 1;
//		} else {
//			// 回傳給 service，-1代表刪除失敗
//			return -1;
//		}
//	}
	
	public int delete(Integer shipId) {
		   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		    Transaction transaction = null;
		
		    try {
		        transaction = session.beginTransaction();
		ShipSetting shipSetting = getSession().get(ShipSetting.class, shipId);
		if (shipSetting != null) {
			getSession().delete(shipSetting);
			// 回傳給 service，1代表刪除成功
			transaction.commit();
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	    } catch (RuntimeException ex) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        
	    }
		    return -1;
	}
}
