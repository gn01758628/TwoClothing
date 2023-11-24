package com.twoclothing.model.categorytags;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CategoryTagsHibernateDAO implements CategoryTagsDAO {

    // SessionFactory為執行序安全,宣告為實體變數共用
    private final SessionFactory factory;

    public CategoryTagsHibernateDAO(SessionFactory factory) {
        this.factory = factory;
    }

    // session執行序不安全,由各方法內部調用
    private Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public int insert(CategoryTags categoryTags) {
        return (Integer) getSession().save(categoryTags);
    }

    @Override
    public CategoryTags getByPrimaryKey(Integer tagId) {
        return getSession().get(CategoryTags.class, tagId);
    }

    @Override
    public List<CategoryTags> getAll() {
        return getSession().createQuery("from CategoryTags order by tagId", CategoryTags.class).list();
    }

    @Override
    public List<CategoryTags> getAllByEmpId(Integer empId) {
        return getSession().createQuery("from CategoryTags where empId = :empId order by tagId", CategoryTags.class)
                .setParameter("empId", empId)
                .list();
    }

    @Override
    public List<Integer> getTagIdsWithoutChildren() {
        return getSession()
                .createQuery("SELECT t.tagId FROM CategoryTags t WHERE t.tagId NOT IN (SELECT DISTINCT s.superTagId FROM CategoryTags s WHERE s.superTagId IS NOT NULL)", Integer.class)
                .list();
    }

    @Override
    public List<CategoryTags> getAllSubByPrimaryKey(Integer tagId) {
        String sql =
                " WITH RECURSIVE tahhierarchy AS ( " +
                        " SELECT tagid, supertagid, categoryname, empid " +
                        " FROM categorytags " +
                        " WHERE tagid = :tagId " +
                        " UNION ALL " +
                        " SELECT ct.tagid, ct.supertagid, ct.categoryname, ct.empid " +
                        " FROM categorytags ct " +
                        " INNER JOIN tahhierarchy th ON ct.supertagid = th.tagid " +
                        " ) " +
                        "SELECT * FROM tahhierarchy;";
        return getSession().createNativeQuery(sql, CategoryTags.class)
                .setParameter("tagId", tagId)
                .list();
    }

    @Override
    public List<CategoryTags> getAllHaveSubTags() {
        return getSession().createQuery(
                "SELECT ct " +
                        "FROM CategoryTags ct " +
                        "WHERE ct.tagId IN (" +
                        "   SELECT DISTINCT c.superTagId " +
                        "   FROM CategoryTags c " +
                        "   WHERE c.superTagId IS NOT NULL) " +
                        "OR ct.superTagId IS NULL",
                CategoryTags.class
        ).getResultList();
    }

    @Override
    public boolean update(CategoryTags categoryTags) {
        try {
            getSession().update(categoryTags);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
