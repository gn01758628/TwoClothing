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
	
	
	@Override
	public int insert(ShipSetting shipSetting) {
		Integer shipId = (Integer) getSession().save(shipSetting);
		
		return shipId;
		
	}

	@Override
	public ShipSetting getByPrimaryKey(Integer shipId) {
		return getSession().get(ShipSetting.class, shipId);
	}

	@Override
	public List<ShipSetting> getAll() {
		return getSession().createQuery("from ShipSetting", ShipSetting.class).list();
	}

	@Override
//	public List<ShipSetting> getAllByMbrId(Integer mbrId) {
//		return getSession().createQuery("from ShipSetting where mbrId = :mbrId", ShipSetting.class)
//				.setParameter("mbrId", mbrId).list();
//	}

	public List<ShipSetting> getAllByMbrId(Integer mbrId) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction transaction = null;
	    List<ShipSetting> shipSetting = null;
	    
	    try {
	        transaction = session.beginTransaction();
	        shipSetting =  session.createQuery("from ShipSetting where mbrId = :mbrId", ShipSetting.class)
	                .setParameter("mbrId", mbrId)
	                .list();
	        transaction.commit();
	    } catch (RuntimeException ex) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        throw ex;
	    }
	    return  shipSetting;
	}
	
	@Override
	public int update(ShipSetting shipSetting) {
		try {
			getSession().update(shipSetting);
			return 1;
		}catch (Exception e) {
			return -1;
		}
		
	}

	@Override
	public int delete(Integer shipId) {
		ShipSetting shipSetting = getSession().get(ShipSetting.class, shipId);
		if (shipSetting != null) {
			getSession().delete(shipSetting);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}

}
