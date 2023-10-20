package com.twoclothing.chenghan.service;

import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditem.BidItemDAO;
import com.twoclothing.model.abid.biditem.BidItemHibernateDAO;
import com.twoclothing.model.abid.biditemimage.BidItemImage;
import com.twoclothing.model.abid.biditemimage.BidItemImageDAO;
import com.twoclothing.model.abid.biditemimage.BidItemImageHibernateDAO;
import com.twoclothing.model.categorytags.CategoryTags;
import com.twoclothing.model.categorytags.CategoryTagsDAO;
import com.twoclothing.model.categorytags.CategoryTagsHibernateDAO;
import com.twoclothing.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.List;

public class BidItemFrontServiceImpl implements BidItemFrontService {

    private BidItemDAO bidItemDAO;

    private BidItemImageDAO bidItemImageDAO;

    private CategoryTagsDAO categoryTagsDAO;

    public BidItemFrontServiceImpl() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        bidItemDAO = new BidItemHibernateDAO(sessionFactory);
        bidItemImageDAO = new BidItemImageHibernateDAO(sessionFactory);
        categoryTagsDAO = new CategoryTagsHibernateDAO(sessionFactory);
    }

    @Override
    public void addBidItem(BidItem bidItem) {

    }

    @Override
    public void addBidItemImage(BidItemImage bidItemImage) {

    }

    @Override
    public List<CategoryTags> getAllCategoryTags() {
        return categoryTagsDAO.getAll();
    }
}
