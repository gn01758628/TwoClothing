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

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private final BidItemDAO bidItemDAO = new BidItemHibernateDAO(sessionFactory);

    private final BidItemImageDAO bidItemImageDAO = new BidItemImageHibernateDAO(sessionFactory);

    private final CategoryTagsDAO categoryTagsDAO = new CategoryTagsHibernateDAO(sessionFactory);

    public BidItemFrontServiceImpl() {
    }

    @Override
    public int addBidItem(BidItem bidItem) {
        return bidItemDAO.insert(bidItem);
    }

    @Override
    public void addBidItemImage(BidItemImage bidItemImage) {
        bidItemImageDAO.insert(bidItemImage);
    }

    @Override
    public BidItem getBidItemByBidItemId(Integer bidItemId) {
        return bidItemDAO.getByPrimaryKey(bidItemId);
    }

    @Override
    public List<BidItem> getAllBidItemByMbrid(Integer mbrId) {
        return bidItemDAO.getAllByMbrId(mbrId);
    }

    @Override
    public List<CategoryTags> getAllCategoryTags() {
        return categoryTagsDAO.getAll();
    }

    @Override
    public List<Integer> getAllSelectableTagsId() {
        return categoryTagsDAO.getTagIdsWithoutChildren();
    }
}
