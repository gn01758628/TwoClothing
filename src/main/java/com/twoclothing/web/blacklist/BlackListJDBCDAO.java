package com.twoclothing.web.blacklist;

import com.twoclothing.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlackListJDBCDAO implements BlackListDAO {
    private static final String INSERT = "INSERT INTO blacklist (mbrid, blackid) VALUES (?,?)";

    private static final String GET_BY_CPK = "SELECT * FROM blacklist WHERE mbrid = ? AND blackid = ?";

    private static final String GET_ALL = "SELECT * FROM blacklist";

    private static final String GET_ALL_BY_MBRID = "SELECT * FROM blacklist WHERE mbrid = ? ORDER BY blackid";

    private static final String GET_ALL_BY_BLACKID = "SELECT * FROM blacklist WHERE blackid = ? ORDER BY mbrid";

    private static final String DELETE = "DELETE FROM blacklist WHERE mbrid = ? AND blackid = ?";

    @Override
    public void insert(BlackList blackList) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, blackList.getMbrId());
            ps.setInt(2, blackList.getBlackId());
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
    public BlackList getByCompositeKey(Integer mbrId, Integer blackId) {
        BlackList blackList = new BlackList();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(GET_BY_CPK);
            ps.setInt(1, mbrId);
            ps.setInt(2, blackId);
            rs = ps.executeQuery();

            if (rs.next()) {
                blackList = setBlacklist(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }
        return blackList;
    }

    @Override
    public List<BlackList> getAll() {
        List<BlackList> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(setBlacklist(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }

        if (list.isEmpty()) list.add(null);
        return list;
    }

    @Override
    public List<BlackList> getAllByMbrId(Integer mbrId) {
        return getAllBy(GET_ALL_BY_MBRID, mbrId);
    }

    @Override
    public List<BlackList> getAllByBlackId(Integer blackId) {
        return getAllBy(GET_ALL_BY_BLACKID, blackId);
    }

    @Override
    public void delete(Integer mbrId, Integer blackId) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, mbrId);
            ps.setInt(2, blackId);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫刪除成功的執行代碼
            System.out.println("刪除成功");
        } else {
            // 編寫刪除失敗的執行代碼
            System.out.println("刪除失敗");
        }
    }

    private BlackList setBlacklist(ResultSet rs) {

        BlackList blackList = new BlackList();

        try {
            blackList.setMbrId(rs.getInt("mbrid"));
            blackList.setBlackId(rs.getInt("blackid"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blackList;
    }

    private List<BlackList> getAllBy(String by, Integer byid) {
        List<BlackList> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(by);
            ps.setInt(1, byid);
            rs = ps.executeQuery();

            while (rs.next()) {
                BlackList blackList = getByCompositeKey(rs.getInt("mbrid"), rs.getInt("blackid"));
                list.add(blackList);
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
