package com.twoclothing.model.abid.biditemimage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class BidItemImageHibernateDAO implements BidItemImageDAO {

    // SessionFactory為執行序安全,宣告為實體變數共用
    private final SessionFactory factory;

    public BidItemImageHibernateDAO(SessionFactory factory) {
        this.factory = factory;
    }

    // session執行序不安全,由各方法內部調用
    private Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public int insert(BidItemImage bidItemImage) {
        return (Integer) getSession().save(bidItemImage);
    }

    @Override
    public BidItemImage getByPrimaryKey(Integer imageId) {
        return getSession().get(BidItemImage.class, imageId);
    }

    @Override
    public List<BidItemImage> getAllByBidItemId(Integer bidItemId) {
        return getSession().createQuery("from BidItemImage where bidItemId = :bidItemId order by imageId", BidItemImage.class)
                .setParameter("bidItemId", bidItemId)
                .list();
    }

    @Override
    public boolean update(BidItemImage bidItemImage) {
        try {
            getSession().update(bidItemImage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
