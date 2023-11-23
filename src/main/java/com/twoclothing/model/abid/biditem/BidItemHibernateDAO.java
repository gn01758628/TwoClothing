package com.twoclothing.model.abid.biditem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
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

    private final String GET_ALL_SUB_BY_TAGID = "WITH RECURSIVE taghierarchy AS ( "
            + "SELECT tagid "
            + "FROM categorytags "
            + "WHERE tagid = :tagId "
            + "UNION ALL "
            + "SELECT ct.tagid "
            + "FROM categorytags ct "
            + "INNER JOIN taghierarchy th ON ct.supertagid = th.tagid "
            + ") "
            + "SELECT DISTINCT it.* "
            + "FROM taghierarchy th "
            + "INNER JOIN biditem it ON th.tagid = it.tagid "
            + "WHERE it.bidstatus = 4";

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
        return getSession().createNativeQuery(GET_ALL_SUB_BY_TAGID, BidItem.class)
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
        return getSession().createQuery("from BidItem where bidStatus = 4 and endTime < :time", BidItem.class)
                .setParameter("time", time)
                .list();
    }

    @Override
    public List<BidItem> getAllPassBidItemsByStartTime(Timestamp time) {
        return getSession().createQuery("from BidItem where bidStatus = 1 and startTime < :time", BidItem.class)
                .setParameter("time", time)
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

    // 瀏覽頁面相關
    @Override
    public List<BidItem> getAllLimit(int currentPage, int pageMaxResult) {
        int first = (currentPage - 1) * pageMaxResult;
        return getSession().createQuery("from BidItem where bidStatus = 4 order by endTime desc", BidItem.class)
                .setFirstResult(first)
                .setMaxResults(pageMaxResult)
                .list();
    }

    @Override
    public int countByActive() {
        long count = (long) getSession().createQuery("select count(*) from BidItem where bidStatus = 4")
                .uniqueResult();
        return (int) count;
    }

    @Override
    public List<BidItem> getAllByTagsLimit(int currentPage, int pageMaxResult, Integer tagId) {
        int first = (currentPage - 1) * pageMaxResult;
        return getSession().createNativeQuery(GET_ALL_SUB_BY_TAGID, BidItem.class)
                .setParameter("tagId", tagId)
                .setFirstResult(first)
                .setMaxResults(pageMaxResult)
                .list();
    }

    @Override
    public int countByTags(Integer tagId) {
        String countQuery = "SELECT COUNT(*) FROM (" + GET_ALL_SUB_BY_TAGID + ") as sub";
        BigInteger count = (BigInteger) getSession().createNativeQuery(countQuery)
                .setParameter("tagId", tagId)
                .getSingleResult();
        return count.intValue();
    }

    // 導覽列搜尋

    @Override
    public List<BidItem> getAllActiveByBidItemName(String bidName) {
        return getSession().createQuery("from BidItem where bidName like :bidName and bidStatus = 4", BidItem.class)
                .setParameter("bidName", "%" + bidName + "%")
                .list();
    }

    @Override
    public int countActiveByBidItemName(String bidName) {
        Long count = (Long) getSession().createQuery("select count(*) from BidItem where bidName like :bidName and bidStatus = 4")
                .setParameter("bidName", "%" + bidName + "%")
                .getSingleResult();
        return count.intValue();
    }
}
