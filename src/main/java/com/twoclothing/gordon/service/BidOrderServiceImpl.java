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
import com.twoclothing.model.shipsetting.ShipSettingDAO;
import com.twoclothing.model.shipsetting.ShipSettingHibernateDAO;


public class BidOrderServiceImpl implements BidOrderService {
	
	private BidOrderDAO dao;
	
	public BidOrderServiceImpl() {
		dao = new BidOrderHIbernateDAO(HibernateUtil.getSessionFactory());
	}
//    private  BidItemDAO bidItemDAO = new BidItemHibernateDAO(HibernateUtil.getSessionFactory());
//
//    private final MembersDAO membersDAO = new MembersHibernateDAO(HibernateUtil.getSessionFactory());
    
//    private ShipSettingDAO shipSettingDAO = new ShipSettingHibernateDAO(HibernateUtil.getSessionFactory());
    
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
	public List<BidOrder> getAllSellMbrIdAndBuyMbrId(Integer sellMbrId, Integer buyMbrId, Integer bidItemId) {
		return dao.getAllBySellMbrIdAndBuyMbrId(sellMbrId, buyMbrId, bidItemId);
	}
		
	@Override
	public List<BidOrder> getAllOrderStatusAndBuyer(Integer orderStatus, Integer buyMbrId) {

		return dao.getAllByOrderStatusAndBuyer(orderStatus, buyMbrId);
		
	}
	
	public BidOrder updateAll(BidOrder bidorder) {
	    if (bidorder != null) {
	        int result = dao.update(bidorder);
	        if (result == 1) {
	            return bidorder;
	        }
	    }
	    return null;
	}
	
	

	@Override
	public List<BidOrder> getAllOrderStatusAndSeller(Integer orderStatus, Integer sellMbrId) {
		return dao.getAllByOrderStatusAndSeller(orderStatus, sellMbrId);
		
	}

	@Override
	public BidOrder updateBuyStarAndBuyerRatingDesc(Integer buyStar, String buyerRatingDesc) {
		BidOrder bidOrder =new BidOrder();
		
		bidOrder.setBuyStar(buyStar);
		bidOrder.setBuyerRatingDesc(buyerRatingDesc);
		
		
		return bidOrder;
	}


	@Override
	public BidOrder updateSellStarAndsellerRatingDesc(Integer sellStar, String sellerRatingDesc) {
		BidOrder bidOrder =new BidOrder();
		
		bidOrder.setSellStar(sellStar);
		bidOrder.setSellerRatingDesc(sellerRatingDesc);
		
		
		return bidOrder;
	}


	@Override
	public BidOrder updateOrderStatus(Integer orderStatus) {
		BidOrder bidOrder =new BidOrder();
		
		bidOrder.setOrderStatus(orderStatus);
		return bidOrder;
	}

	@Override
	public Integer deleteBidOrder(Integer bidOrderId) {
		return dao.delete(bidOrderId);
	}




}
