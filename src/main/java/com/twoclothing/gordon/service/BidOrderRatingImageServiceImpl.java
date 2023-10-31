package com.twoclothing.gordon.service;

import java.util.List;

import com.twoclothing.model.abid.bidorderratingimage.BidOrderRatingImage;
import com.twoclothing.model.abid.bidorderratingimage.BidOrderRatingImageDAO;
import com.twoclothing.model.abid.bidorderratingimage.BidOrderRatingImageHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

public class BidOrderRatingImageServiceImpl implements BidOrderRatingImageService{
	
	private BidOrderRatingImageDAO dao;
	
	public BidOrderRatingImageServiceImpl() {
		dao = new BidOrderRatingImageHibernateDAO(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public BidOrderRatingImage addBidOrderRatingImage(Integer bidOrderId, byte[] image) {
		
		BidOrderRatingImage bidOrderRatingImage = new BidOrderRatingImage();
		
		bidOrderRatingImage.setBidOrderId(bidOrderId);
		bidOrderRatingImage.setImage(image);
		
		dao.insert(bidOrderRatingImage);
		
		return bidOrderRatingImage;
	}

	@Override
	public BidOrderRatingImage getByPrimaryKey(Integer imageId) {
		return dao.getByPrimaryKey(imageId);
	}

	@Override
	public List<BidOrderRatingImage> getAll() {
		return dao.getAll();
	}

	@Override
	public List<BidOrderRatingImage> getByBidOrderId(Integer bidOrderId) {
		List<BidOrderRatingImage> bidOrderRatingImage = dao.getAllByBidOrderId(bidOrderId);
		if(bidOrderRatingImage != null) {
			return bidOrderRatingImage;
		}
		return null;
	}

}
