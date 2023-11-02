package com.twoclothing.model.abid.bidordernotify;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.twoclothing.model.members.Members;

public class BidOrderNotifyHibernateDAO implements BidOrderNotifyDAO{

	
	private SessionFactory factory;

	public BidOrderNotifyHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(BidOrderNotify bidOrderNotif) {
		Integer notifyId = (Integer) getSession().save(bidOrderNotif);
		return notifyId;
	}

	@Override
	public BidOrderNotify getByPrimaryKey(Integer notifyId) {
		return getSession().get(BidOrderNotify.class, notifyId);

	}

	@Override
	public List<BidOrderNotify> getAll() {
		return getSession().createQuery("from BidOrderNotify", BidOrderNotify.class).list();

	}

	@Override
	public List<BidOrderNotify> getAllByMbrId(Integer mbrId) {
		return getSession().createQuery("from BidOrderNotify where mbrId = :mbrId ORDER BY mbrId", BidOrderNotify.class)
				.setParameter("mbrId", mbrId).list();
}

	@Override
	public List<BidOrderNotify> getAllByBidOrderId(Integer bidOrderId) {
		return getSession().createQuery("from BidOrderNotify where bidOrderId = :bidOrderId ORDER BY bidOrderId", BidOrderNotify.class)
				.setParameter("bidOrderId", bidOrderId).list();
	}

}
