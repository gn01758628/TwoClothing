package com.twoclothing.chenghan.service;

import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditem.BidItemDAO;
import com.twoclothing.model.abid.biditem.BidItemHibernateDAO;
import com.twoclothing.model.abid.biditemimage.BidItemImage;
import com.twoclothing.model.abid.biditemimage.BidItemImageDAO;
import com.twoclothing.model.abid.biditemimage.BidItemImageHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

public class BidItemFrontServiceImpl implements BidItemFrontService {

    private BidItemDAO bidItemDAO;

    private BidItemImageDAO bidItemImageDAO;

    public BidItemFrontServiceImpl() {
        bidItemDAO = new BidItemHibernateDAO(HibernateUtil.getSessionFactory());
        bidItemImageDAO = new BidItemImageHibernateDAO(HibernateUtil.getSessionFactory());
    }

    @Override
    public void addBidItem(BidItem bidItem) {

    }

    @Override
    public void addBidItemImage(BidItemImage bidItemImage) {

    }
}
