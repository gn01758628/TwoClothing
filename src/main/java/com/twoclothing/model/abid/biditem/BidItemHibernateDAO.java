package com.twoclothing.model.abid.biditem;

import com.twoclothing.model.categorytags.CategoryTags;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.util.List;

public class BidItemHibernateDAO implements BiditemDAO {

    // SessionFactory為執行序安全,宣告為實體變數共用
    private final SessionFactory factory;

    public BidItemHibernateDAO(SessionFactory factory) {
        this.factory = factory;
    }

    // session執行序不安全,由各方法內部調用
    private Session getSession() {
        return factory.getCurrentSession();
    }

    /**
     * 新增
     * @return PrimaryKey
     */
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
    public List<BidItem> getAllByMbrId(Integer mbrId) {
        return getSession().createQuery("from BidItem where mbrId = :mbrid order by bidItemId", BidItem.class)
                .setParameter("mbrid", mbrId)
                .list();
    }

    @Override
    public List<BidItem> getAllByBidStatus(Integer bidStatus) {
        return getSession().createQuery("from BidItem where bidStatus = :bidStatus order by bidItemId", BidItem.class)
                .setParameter("bidStatus", bidStatus)
                .list();
    }

    @Override
    public List<BidItem> getAllByStartTime(Timestamp startTime) {
        return getSession().createQuery("from BidItem where Date(startTime) = Date(:startTime) order by bidItemId", BidItem.class)
                .setParameter("startTime", startTime)
                .list();
    }

    @Override
    public List<BidItem> getAllByStartTimeZone(Timestamp startTimeStart, Timestamp startTimeEnd) {
        if (startTimeStart.after(startTimeEnd)) {
            Timestamp temp = startTimeStart;
            startTimeStart = startTimeEnd;
            startTimeEnd = temp;
        }
        return getSession().createQuery("from BidItem where DATE(startTime) BETWEEN DATE(:startTimeStart) AND DATE(:startTimeEnd) order by bidItemId", BidItem.class)
                .setParameter("startTimeStart", startTimeStart)
                .setParameter("startTimeEnd", startTimeEnd)
                .list();
    }

    @Override
    public List<BidItem> getAllByEndtTime(Timestamp endtTime) {
        return getSession().createQuery("from BidItem where Date(endTime) = Date(:endTime) order by bidItemId", BidItem.class)
                .setParameter("endTime", endtTime)
                .list();
    }

    @Override
    public List<BidItem> getAllByEndtTimeZone(Timestamp endTimeStart, Timestamp endTimeEnd) {
        if (endTimeStart.after(endTimeEnd)) {
            Timestamp temp = endTimeStart;
            endTimeStart = endTimeEnd;
            endTimeEnd = temp;
        }
        return getSession().createQuery("from BidItem where DATE(endTime) BETWEEN DATE(:endTimeStart) AND DATE(:endTimeEnd) order by bidItemId", BidItem.class)
                .setParameter("endTimeStart", endTimeStart)
                .setParameter("endTimeEnd", endTimeEnd)
                .list();
    }

    @Override
    public List<BidItem> getAllByEmpId(Integer empId) {
        return getSession().createQuery("from BidItem where empId = :empId order by bidItemId", BidItem.class)
                .setParameter("empId", empId)
                .list();
    }

    @Override
    public List<BidItem> getAllByTagId(List<CategoryTags> list) {
        return null;
    }

    @Override
    public void delete(Integer bidItemId) {

    }
}
