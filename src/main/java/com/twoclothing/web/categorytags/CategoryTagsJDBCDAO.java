package com.twoclothing.web.categorytags;

import com.twoclothing.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryTagsJDBCDAO implements CategoryTagsDAO {

    private static final String INSERT = "INSERT INTO categorytags (supertagid, categoryname, empid) VALUES (?,?,?)";

    private static final String GET_BY_PK = "SELECT * FROM categorytags WHERE tagid = ?";

    private static final String GET_ALL = "SELECT * FROM categorytags";

    private static final String GET_ALL_BY_EMPID = "SELECT * FROM categorytags WHERE empid = ?";

    private static final String GET_ALL_SUB_TAGS_BY_PK =
            "WITH RECURSIVE tahhierarchy AS (" +
                    "    SELECT tagid, supertagid, categoryname, empid " +
                    "    FROM categorytags " +
                    "    WHERE tagid = ? " +
                    "    UNION ALL " +
                    "    SELECT ct.tagid, ct.supertagid, ct.categoryname, ct.empid " +
                    "    FROM categorytags ct " +
                    "    INNER JOIN tahhierarchy th ON ct.supertagid = th.tagid " +
                    ") " +
                    "SELECT * FROM tahhierarchy;";

    private static final String UPDATE = "UPDATE categorytags SET supertagid = ?, categoryname = ?, empid = ? WHERE tagid = ?";

    @Override
    public void insert(CategoryTags categoryTags) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setObject(1, categoryTags.getSuperTagId(), Types.INTEGER);
            ps.setString(2, categoryTags.getCategoryName());
            ps.setInt(3, categoryTags.getEmpId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫新增成功的執行代碼
            System.out.println("新增成功");
        } else {
            // 編寫新增失敗的執行代碼
            System.out.println("新增失敗");
        }
    }

    @Override
    public CategoryTags getByPrimaryKey(Integer tagId) {
        CategoryTags categoryTags = new CategoryTags();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(GET_BY_PK);
            ps.setInt(1, tagId);
            rs = ps.executeQuery();

            if (rs.next()) {
                categoryTags = setCategoryTags(rs);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }
        return categoryTags;
    }

    @Override
    public List<CategoryTags> getAll() {
        return getAllBy(GET_ALL);
    }

    @Override
    public List<CategoryTags> getAllByEmpId(Integer empId) {
        return getAllBy(GET_ALL_BY_EMPID, empId);
    }

    @Override
    public List<CategoryTags> getAllSubByPrimaryKey(Integer tagId) {
        return getAllBy(GET_ALL_SUB_TAGS_BY_PK, tagId);
    }

    @Override
    public void update(CategoryTags categoryTags) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setObject(1, categoryTags.getSuperTagId(), Types.INTEGER);
            ps.setString(2, categoryTags.getCategoryName());
            ps.setInt(3, categoryTags.getEmpId());
            ps.setInt(4, categoryTags.getTagId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫修改成功的執行代碼
            System.out.println("修改成功");
        } else {
            // 編寫修改失敗的執行代碼
            System.out.println("修改失敗");
        }

    }

    private CategoryTags setCategoryTags(ResultSet rs) {

        CategoryTags categoryTags = new CategoryTags();

        try {
            categoryTags.setTagId(rs.getInt("tagid"));
            categoryTags.setSuperTagId(rs.getObject("supertagid", Integer.class));
            categoryTags.setCategoryName(rs.getString("categoryname"));
            categoryTags.setEmpId(rs.getInt("empid"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryTags;
    }

    private List<CategoryTags> getAllBy(String by, Integer... byid) {
        if (byid.length > 1) return null;
        List<CategoryTags> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(by);
            if (byid.length == 1) ps.setInt(1, byid[0]);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(setCategoryTags(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }

        if (list.isEmpty()) list.add(null);
        return list;
    }
}
