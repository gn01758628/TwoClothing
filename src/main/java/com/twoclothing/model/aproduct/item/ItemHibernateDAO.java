package com.twoclothing.model.aproduct.item;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.twoclothing.huiwen.Constants.ITEM_PAGE_MAX_RESULT;

public class ItemHibernateDAO implements ItemDAO {

    private final SessionFactory factory;

    public ItemHibernateDAO(SessionFactory factory) {
        this.factory = factory;
    }

    private Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public int insert(Item item) {
        return (Integer) getSession().save(item);
    }


    @Override
    public Item getByPrimaryKey(Integer itemId) {
        return getSession().get(Item.class, itemId);
    }


    @Override
    public List<Item> getAllByTagId(Integer tagId) {
        return getSession().createQuery("from Item where tagId = :tagId", Item.class)
                .setParameter("tagId", tagId)
                .list();
    }

    @Override
    public List<Item> getAllByMbrId(Integer mbrId) {
        return getSession().createQuery("from Item where mbrId = :mbrId", Item.class)
                .setParameter("mbrId", mbrId)
                .list();
    }


    @Override
    public List<Item> getAllByItemStatus(Integer itemStatus) {
        return getSession().createQuery("from Item where itemStatus = :itemStatus", Item.class)
                .setParameter("itemStatus", itemStatus)
                .list();
    }

    @Override
    public List<Item> getAllSubByTagId(Integer tagId) {
        String sql = "WITH RECURSIVE tahhierarchy AS ( "
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
                + "INNER JOIN item it ON th.tagid = it.tagid "
                + "WHERE it.itemstatus = 0;";

        return getSession().createNativeQuery(sql, Item.class)
                .setParameter("tagId", tagId)
                .list();
    }

    @Override
    public int update(Item item) {
        try {
            getSession().update(item);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public List<Item> getByCompositeQuery(Map<String, String> map, int page) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Item> criteria = builder.createQuery(Item.class);
        Root<Item> root = criteria.from(Item.class);

        List<Predicate> predicates = new ArrayList<>();

        if (map.containsKey("itemPriceSearchStart") && map.containsKey("itemPriceSearchEnd"))
            predicates.add(builder.between(root.get("price"), new BigDecimal(map.get("itemPriceSearchStart")), new BigDecimal(map.get("itemPriceSearchEnd"))));

        if (map.containsKey("itemQuantityStart") && map.containsKey("itemQuantityEnd")) {
            predicates.add(builder.between(root.get("quantity"), new BigDecimal(map.get("itemQuantityStart")), new BigDecimal(map.get("itemQuantityEnd"))));
        }

        boolean hasItemStatusCondition = false; // 用來檢查是否已經有添加 itemStatus 的條件

        for (Map.Entry<String, String> row : map.entrySet()) {

            if ("itemNameSearch".equals(row.getKey())) {
                predicates.add(builder.like(root.get("itemName"), "%" + row.getValue() + "%"));
            }
            if ("itemPriceSearchStart".equals(row.getKey())) {
                if (!map.containsKey("itemPriceSearchEnd"))
                    predicates.add(builder.greaterThanOrEqualTo(root.get("price"), new BigDecimal(row.getValue())));
            }
            if ("itemPriceSearchEnd".equals(row.getKey())) {
                if (!map.containsKey("itemPriceSearchStart"))
                    predicates.add(builder.lessThanOrEqualTo(root.get("price"), new BigDecimal(row.getValue())));
            }
            if ("itemGrade".equals(row.getKey())) {
                predicates.add(builder.equal(root.get("grade"), new BigDecimal(row.getValue())));
            }
            if ("itemSize".equals(row.getKey())) {
                predicates.add(builder.equal(root.get("size"), new BigDecimal(row.getValue())));
            }
            if ("itemStatus".equals(row.getKey())) {
                predicates.add(builder.equal(root.get("itemStatus"), new BigDecimal(row.getValue())));
                hasItemStatusCondition = true;
            }

            if ("itemQuantityEnd".equals(row.getKey())) {
                if (!map.containsKey("itemQuantityStart"))
                    predicates.add(builder.lessThanOrEqualTo(root.get("quantity"), new BigDecimal(row.getValue())));
            }
            if ("itemQuantityStart".equals(row.getKey())) {
                if (!map.containsKey("itemQuantityend"))
                    predicates.add(builder.greaterThanOrEqualTo(root.get("quantity"), new BigDecimal(row.getValue())));
            }
            if ("tagId".equals(row.getKey())) {
                predicates.add(builder.equal(root.get("tagId"), new BigDecimal(row.getValue())));
            }

        }
        // 如果沒有添加 itemStatus 的條件，則添加一個不等於 2 的條件
        if (!hasItemStatusCondition) {
            predicates.add(builder.notEqual(root.get("itemStatus"), new BigDecimal(2)));
        }
        criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        criteria.orderBy(builder.asc(root.get("itemId")));
        TypedQuery<Item> query = getSession().createQuery(criteria);


        int first = (page - 1) * ITEM_PAGE_MAX_RESULT;

        List<Item> resultList = query.getResultList();

        int total = resultList.size();

        return (query.setFirstResult(first)
                .setMaxResults(ITEM_PAGE_MAX_RESULT)
                .getResultList());
    }

    @Override
    public int getResultTotal(Map<String, String> map) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Item> criteria = builder.createQuery(Item.class);
        Root<Item> root = criteria.from(Item.class);

        List<Predicate> predicates = new ArrayList<>();

        if (map.containsKey("itemPriceSearchStart") && map.containsKey("itemPriceSearchEnd"))
            predicates.add(builder.between(root.get("price"), new BigDecimal(map.get("itemPriceSearchStart")), new BigDecimal(map.get("itemPriceSearchEnd"))));

        if (map.containsKey("itemQuantityStart") && map.containsKey("itemQuantityEnd")) {
            predicates.add(builder.between(root.get("quantity"), new BigDecimal(map.get("itemQuantityStart")), new BigDecimal(map.get("itemQuantityEnd"))));
        }
        boolean hasItemStatusCondition = false; // 用來檢查是否已經有添加 itemStatus 的條件

        for (Map.Entry<String, String> row : map.entrySet()) {

            if ("itemNameSearch".equals(row.getKey())) {
                predicates.add(builder.like(root.get("itemName"), "%" + row.getValue() + "%"));
            }
            if ("itemPriceSearchStart".equals(row.getKey())) {
                if (!map.containsKey("itemPriceSearchEnd"))
                    predicates.add(builder.greaterThanOrEqualTo(root.get("price"), new BigDecimal(row.getValue())));
            }
            if ("itemPriceSearchEnd".equals(row.getKey())) {
                if (!map.containsKey("itemPriceSearchEnd"))
                    predicates.add(builder.lessThanOrEqualTo(root.get("price"), new BigDecimal(row.getValue())));
            }
            if ("itemGrade".equals(row.getKey())) {
                predicates.add(builder.equal(root.get("grade"), new BigDecimal(row.getValue())));
            }
            if ("itemSize".equals(row.getKey())) {
                predicates.add(builder.equal(root.get("size"), new BigDecimal(row.getValue())));
            }
            if ("itemStatus".equals(row.getKey())) {
                predicates.add(builder.equal(root.get("itemStatus"), new BigDecimal(row.getValue())));
                hasItemStatusCondition = true;
            }
            if ("itemQuantityEnd".equals(row.getKey())) {
                if (!map.containsKey("itemQuantityStart"))
                    predicates.add(builder.lessThanOrEqualTo(root.get("quantity"), new BigDecimal(row.getValue())));
            }
            if ("itemQuantityStart".equals(row.getKey())) {
                if (!map.containsKey("itemQuantityend"))
                    predicates.add(builder.greaterThanOrEqualTo(root.get("quantity"), new BigDecimal(row.getValue())));
            }
            if ("tagId".equals(row.getKey())) {
                predicates.add(builder.equal(root.get("tagId"), new BigDecimal(row.getValue())));
            }
        }
        if (!hasItemStatusCondition) {
            predicates.add(builder.notEqual(root.get("itemStatus"), new BigDecimal(2)));
        }

        criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        criteria.orderBy(builder.asc(root.get("itemId")));
        TypedQuery<Item> query = getSession().createQuery(criteria);

        List<Item> resultList = query.getResultList();

        int total = resultList.size();

        return total;
    }

    @Override
    public List<Item> getAll(int page) {

        int first = (page - 1) * ITEM_PAGE_MAX_RESULT;

        return getSession().createQuery("from Item where itemStatus = 0 order by itemId desc", Item.class)
                .setFirstResult(first)
                .setMaxResults(ITEM_PAGE_MAX_RESULT)
                .list();
    }

    @Override
    public long getTotal() {
        return getSession().createQuery("select count(*) from Item", Long.class).uniqueResult();
    }

    @Override
    public Integer getPointByMbrId(Integer mbrId) {
        return (Integer) getSession().createQuery("select m.mbrPoint from Members m where m.mbrId = :mbrId").setParameter("mbrId", mbrId).uniqueResult();
    }

    @Override
    public Integer getSellScoreByMbrId(Integer mbrId) {
        return (Integer) getSession().createQuery("select m.sellScore from Members m where m.mbrId = :mbrId").setParameter("mbrId", mbrId).uniqueResult();
    }

    @Override
    public Integer getbalanceByMbrId(Integer mbrId) {
        return (Integer) getSession().createQuery("select m.balance from Members m where m.mbrId = :mbrId").setParameter("mbrId", mbrId).uniqueResult();
    }

    @Override
    public Integer getMbrIdById(Integer itemId) {
        return (Integer) getSession().createQuery("select mbrId from Item where itemId = :itemId").setParameter("itemId", itemId).uniqueResult();
    }

    @Override
    public List<Item> getItemByMbrIdAndStatus(Integer mbrId) {
        return getSession().createQuery("from Item where mbrId = :mbrId and itemStatus = 0", Item.class)
                .setParameter("mbrId", mbrId)
                .list();
    }

    @Override
    public List<Integer> getItemByMbrId(Integer mbrId) {
        return getSession().createQuery("select itemId from Item where mbrId = :mbrId")
                .setParameter("mbrId", mbrId).list();
    }

    @Override
    public List<Item> getAllActiveByItemName(String itemName) {
        return getSession().createQuery("from Item where itemName like :itemName and itemStatus = 0", Item.class)
                .setParameter("itemName", "%" + itemName + "%")
                .list();
    }

    @Override
    public int countActiveByItemName(String itemName) {
        Long count = (Long) getSession().createQuery("select count(*) from Item where itemName like :itemName and itemStatus = 0")
                .setParameter("itemName", "%" + itemName + "%")
                .getSingleResult();
        return count.intValue();
    }
}