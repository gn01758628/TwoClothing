package com.twoclothing.gordon.service;

import java.util.*;

import com.twoclothing.model.abid.biditem.BidItemDAO;
import com.twoclothing.model.abid.biditem.BidItemHibernateDAO;
import com.twoclothing.model.abid.bidorder.BidOrder;
import com.twoclothing.model.abid.bidorder.BidOrderDAO;
import com.twoclothing.model.abid.bidorder.BidOrderHIbernateDAO;
import com.twoclothing.model.members.MembersDAO;
import com.twoclothing.model.members.MembersHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

public class BidOrderServiceImpl implements BidOrderService {
	
	private BidOrderDAO dao;
	
	public BidOrderServiceImpl() {
		dao = new BidOrderHIbernateDAO(HibernateUtil.getSessionFactory());
	}
//    private  BidItemDAO bidItemDAO = new BidItemHibernateDAO(HibernateUtil.getSessionFactory());
//
//    private final MembersDAO membersDAO = new MembersHibernateDAO(HibernateUtil.getSessionFactory());

    
	@Override
	public BidOrder addBidOrder(BidOrder bidorder) {

		dao.insert(bidorder);
		return bidorder;
	}

	@Override
	public List<BidOrder> getAll() {
		return dao.getAll();
	}

	@Override
	public BidOrder getByPrimaryKey(Integer bidOrderId) {
		return dao.getByPrimaryKey(bidOrderId);
	}

	@Override
	public List<BidOrder> getAllBuyMbrId(Integer buyMbrId) {
		return  dao.getAllByBuyMbrId(buyMbrId);
		
	}

	@Override
	public List<BidOrder> getAllSellMbrId(Integer SellMbrId) {
		return dao.getAllBySellMbrId(SellMbrId); 
			 
		}
		
	@Override
	public List<BidOrder> getAllOrderStatusAndBuyer(Integer orderStatus, Integer buyMbrId) {

		return dao.getAllByOrderStatusAndBuyer(orderStatus, buyMbrId);
		
	}

	@Override
	public List<BidOrder> getAllOrderStatusAndSeller(Integer orderStatus, Integer sellMbrId) {
		return dao.getAllByOrderStatusAndSeller(orderStatus, sellMbrId);
		
	}

	@Override
	public BidOrder updateBuyStarAndBuyerRatingDesc(Integer buyStar, String buyerRatingDesc) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public BidOrder updateSellStarAndsellerRatingDesc(Integer sellStar, String sellerRatingDesc) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public BidOrder updateOrderStatus(Integer orderStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteBidOrder(Integer bidOrderId) {
		// TODO Auto-generated method stub
		return null;
	}
}
