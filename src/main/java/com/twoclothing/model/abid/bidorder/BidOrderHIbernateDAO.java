package com.twoclothing.model.abid.bidorder;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;




public class BidOrderHIbernateDAO implements BidOrderDAO {

	private SessionFactory factory;

	public BidOrderHIbernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	
	@Override
	public int insert(BidOrder bidorder) {
		Integer bidItemId = (Integer) getSession().save(bidorder);
		return bidItemId;
		}
		
	
	@Override
	public List<BidOrder> getAll() {
		return getSession().createQuery("from BidOrder", BidOrder.class).list();
		
	}

	@Override
	public BidOrder getByPrimaryKey(Integer bidOrderId) {
		return getSession().get(BidOrder.class, bidOrderId);

	}


	@Override
	public List<BidOrder> getAllByBuyMbrId(Integer buyMbrId) {
		return getSession().createQuery("from BidOrder where buyMbrId = :buyMbrId", BidOrder.class)
				.setParameter("buyMbrId", buyMbrId).list();

	}

	@Override
	public List<BidOrder> getAllBySellMbrId(Integer sellMbrId) {
		return getSession().createQuery("from BidOrder where sellMbrId = :sellMbrId", BidOrder.class)
				.setParameter("sellMbrId", sellMbrId).list();

	}
	
	@Override
	public List<BidOrder> getAllBySellMbrIdAndBuyMbrId(Integer sellMbrId, Integer buyMbrId, Integer bidItemId) {
		return getSession().createQuery("FROM BidOrder WHERE sellMbrId = :sellMbrId AND buyMbrId = :buyMbrId AND bidItemId = :bidItemId", BidOrder.class)
                .setParameter("sellMbrId", sellMbrId)
                .setParameter("buyMbrId", buyMbrId)
                .setParameter("bidItemId", bidItemId)
                .list();
	}
	
	

	@Override
	public List<BidOrder> getAllByOrderStatusAndBuyer(Integer orderStatus, Integer buyMbrId) {
	    // 買家查詢的實現
		return getSession().createQuery("FROM BidOrder WHERE orderStatus = :orderStatus AND buyMbrId = :buyMbrId", BidOrder.class)
                .setParameter("orderStatus", orderStatus)
                .setParameter("buyMbrId", buyMbrId)
                .list();
		
	}
	@Override
	public List<BidOrder> getAllByOrderStatusAndSeller(Integer orderStatus, Integer sellMbrId) {
		return getSession().createQuery("FROM BidOrder WHERE orderStatus = :orderStatus AND sellMbrId = :sellMbrId", BidOrder.class)
                .setParameter("orderStatus", orderStatus)
                .setParameter("sellMbrId", sellMbrId)
                .list();
	}

	@Override
	public int update(BidOrder bidorder) {
		try {
			getSession().update(bidorder);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer bidOrderId) {
		BidOrder bidOrder = getSession().get(BidOrder.class, bidOrderId);
		if (bidOrder != null) {
			getSession().delete(bidOrder);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		
	}
	
	
	}

	
}
