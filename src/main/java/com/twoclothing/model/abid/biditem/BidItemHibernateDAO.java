package com.twoclothing.model.abid.biditem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public List<BidItem> getAllByMbrId(Integer mbrId) {
        return getSession().createQuery("from BidItem where mbrId = :mbrId order by bidItemId", BidItem.class)
                .setParameter("mbrId", mbrId)
                .list();
    }

    @Override
    public List<BidItem> getAllSubByTagId(Integer tagId) {
        String sql ="WITH RECURSIVE tahhierarchy AS ( "
                + "SELECT tagid "
                + "FROM categorytags "
                + "WHERE tagid = :tagId "
                + "UNION ALL "
                + "SELECT ct.tagid "
                + "FROM categorytags ct "
                + "INNER JOIN tahhierarchy th ON ct.supertagid = th.tagid "
                + ") "
                + "SELECT DISTINCT it.* "
                + "FROM tahhierarchy th "
                + "INNER JOIN biditem it ON th.tagid = it.tagid "
                + "WHERE it.bidstatus = 4;";

        return getSession().createNativeQuery(sql, BidItem.class)
                .setParameter("tagId", tagId)
                .list();
    }

    @Override
    public List<BidItem> getAllByBidStatus(Integer bidStatus) {
        return getSession().createQuery("from BidItem where bidStatus = :bidStatus", BidItem.class)
                .setParameter("bidStatus", bidStatus)
                .list();
    }

    @Override
    public List<BidItem> getAllLegalByMbrId(Integer mbrId) {
        return getSession().createQuery("from BidItem where bidStatus not in (:statuses1,:statuses2) and mbrId = :mbrId order by bidItemId", BidItem.class)
                .setParameter("statuses1", 5)
                .setParameter("statuses2", 6)
                .setParameter("mbrId", mbrId)
                .list();
    }

    @Override
    public List<BidItem> getAllActiveBidItemsByEndTime(Timestamp time) {
        return getSession().createQuery("from BidItem where bidStatus = 4 and endTime < :time",BidItem.class)
                .setParameter("time",time)
                .list();
    }

    @Override
    public List<BidItem> getAllPassBidItemsByStartTime(Timestamp time) {
        return getSession().createQuery("from BidItem where bidStatus = 1 and startTime < :time",BidItem.class)
                .setParameter("time",time)
                .list();
    }

    @Override
    public List<BidItem> getAllByCompositeQuery(Map<String, String[]> compositeQuery) {
        // Criteria起手式
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<BidItem> criteriaQuery = builder.createQuery(BidItem.class);
        Root<BidItem> root = criteriaQuery.from(BidItem.class);

        // 構建查詢條件
        List<Predicate> predicates = new ArrayList<>();
        if (compositeQuery.containsKey("bidStatus")) {
            predicates.add(builder.equal(root.get("bidStatus"), Integer.parseInt(compositeQuery.get("bidStatus")[0])));
        }

        if (compositeQuery.containsKey("empId")) {
            predicates.add(builder.equal(root.get("empId"), Integer.parseInt(compositeQuery.get("empId")[0])));
        }

        if (compositeQuery.containsKey("bidName")) {
            predicates.add(builder.like(root.get("bidName"), "%" + compositeQuery.get("bidName")[0] + "%"));
        }

        if (compositeQuery.containsKey("mbrId")) {
            List<Predicate> mbrIdPredicates = new ArrayList<>();
            for (String mbrId : compositeQuery.get("mbrId")) {
                mbrIdPredicates.add(builder.equal(root.get("mbrId"), Integer.parseInt(mbrId)));
            }
            // 多個mbrId之間使用or
            predicates.add(builder.or(mbrIdPredicates.toArray(new Predicate[0])));
        }
        // 組裝條件
        criteriaQuery.where(builder.and(predicates.toArray(new Predicate[0])));
        criteriaQuery.orderBy(builder.asc(root.get("bidItemId")));
        // 返回結果
        TypedQuery<BidItem> query = getSession().createQuery(criteriaQuery);
        return query.getResultList();
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
