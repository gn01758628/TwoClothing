package com.twoclothing.model.abid.bidorderratingimage;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.twoclothing.model.members.Members;

public class BidOrderRatingImageHibernateDAO implements BidOrderRatingImageDAO{

	private SessionFactory factory;

	public BidOrderRatingImageHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	
	@Override
	public int insert(BidOrderRatingImage bidOrderRatingImage) {
		Integer imageId = (Integer)  getSession().save(bidOrderRatingImage);
		return imageId;
	}

	@Override
	public BidOrderRatingImage getByPrimaryKey(Integer imageId) {
		return getSession().get(BidOrderRatingImage.class, imageId);
	}

	@Override
	public List<BidOrderRatingImage> getAll() {
		return getSession().createQuery("from BidOrderRatingImage", BidOrderRatingImage.class).list();
	}

	@Override
	public List<BidOrderRatingImage> getAllByBidOrderId(Integer bidOrderId) {
		return getSession().createQuery("from getAllByMbrName where bidOrderId = :bidOrderId ORDER BY imageId", BidOrderRatingImage.class)
				.setParameter("bidOrderId",  bidOrderId ).list();
	}

}
