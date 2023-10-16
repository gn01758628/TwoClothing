package com.twoclothing.model.abid.biditem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class BidItemHibernateDAO implements BidItemDAO {

    // SessionFactory為執行序安全,宣告為實體變數共用
    private final SessionFactory factory;

    public BidItemHibernateDAO(SessionFactory factory) {
        this.factory = factory;
    }

    // session執行序不安全,由各方法內部調用
    private Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public int insert(BidItem biditem) {
        return (Integer) getSession().save(biditem);
    }

    @Override
    public BidItem getByPrimaryKey(Integer bidItemId) {
        return getSession().get(BidItem.class, bidItemId);
    }

    @Override
    public List<BidItem> getAll() {
        return getSession().createQuery("from BidItem order by bidItemId desc ", BidItem.class).list();
    }

    @Override
    public List<BidItem> getAllByEmpId(Integer empId) {
        return getSession().createQuery("from BidItem where empId = :empId order by bidItemId", BidItem.class)
                .setParameter("empId", empId)
                .list();
    }

    @Override
    public boolean update(BidItem bidItem) {
        try {
            getSession().update(bidItem);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
