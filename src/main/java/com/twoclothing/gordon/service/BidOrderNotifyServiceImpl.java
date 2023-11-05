package com.twoclothing.gordon.service;

import java.sql.Timestamp;
import java.util.List;

import com.twoclothing.model.abid.bidordernotify.BidOrderNotify;
import com.twoclothing.utils.HibernateUtil;
import com.twoclothing.model.abid.bidordernotify.BidOrderNotifyDAO;
import com.twoclothing.model.abid.bidordernotify.BidOrderNotifyHibernateDAO;
import com.twoclothing.model.members.Members;

public class BidOrderNotifyServiceImpl  implements BidOrderNotifyService{
	private BidOrderNotifyDAO dao;

	public BidOrderNotifyServiceImpl() {
		dao = new BidOrderNotifyHibernateDAO(HibernateUtil.getSessionFactory());
	}

	@Override
	public BidOrderNotify addBidOrderNotify(Integer mbrId, Integer bidOrderId, Timestamp notifyDate, String title,
			String content) {
		
		BidOrderNotify bidOrderNotify = new BidOrderNotify();
		
		bidOrderNotify.setMbrId(mbrId);
		bidOrderNotify.setBidOrderId(bidOrderId);
		bidOrderNotify.setNotifyDate(notifyDate);
		bidOrderNotify.setTitle(title);
		bidOrderNotify.setContent(content);
		
		dao.insert(bidOrderNotify);
		return bidOrderNotify;
	}

	@Override
	public List<BidOrderNotify> getAll() {
		return dao.getAll();
	}

	@Override
	public BidOrderNotify getByPrimaryKey(Integer notifyId) {
		return dao.getByPrimaryKey(notifyId);
	}

	@Override
	public List<BidOrderNotify> getByMbrId(Integer mbrId) {
		List<BidOrderNotify> bidOrderNotify = dao.getAllByMbrId(mbrId);
	    if (bidOrderNotify != null ) {
	        return bidOrderNotify; // 返回列表中的第一个对象
	    }
	    return null;
	}

	@Override
	public List<BidOrderNotify> getByBidOrderId(Integer bidOrderId) {
		List<BidOrderNotify> bidOrderNotify = dao.getAllByBidOrderId(bidOrderId);
	    if (bidOrderNotify != null ) {
	        return bidOrderNotify; // 返回列表中的第一个对象
	    }
	    return null;
	} 

}
