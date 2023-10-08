package com.twoclothing.web.pointhistory;

import com.twoclothing.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PointHistoryJDBCDAO implements PointHistoryDAO {

    public static final String INSERT = "INSERT INTO pointhistory (mbrid, orderid, changedate, changevalue) VALUES (?, ?, ?, ?)";
    public static final String GET_BY_PK = "SELECT * FROM pointhistory WHERE pointid = ?";
    public static final String GET_ALL = "SELECT * FROM pointhistory ORDER BY pointid";
    public static final String GET_ALL_BY_MBRID = "SELECT * FROM pointhistory WHERE mbrid = ? ORDER BY changedate";

    @Override
    public void insert(PointHistory pointHistory) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, pointHistory.getMbrId());
            ps.setInt(2, pointHistory.getOrderId());
            ps.setTimestamp(3, pointHistory.getChangeDate());
            ps.setInt(4, pointHistory.getChangeValue());
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
    public PointHistory getByPrimaryKey(Integer pointId) {
        PointHistory pointHistory = new PointHistory();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(GET_BY_PK);
            ps.setInt(1, pointId);
            rs = ps.executeQuery();

            if (rs.next()) {
                pointHistory = setPointHistory(rs);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }
        return pointHistory;
    }


    @Override
    public List<PointHistory> getAll() {
        List<PointHistory> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(setPointHistory(rs));
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
    public List<PointHistory> getAllByMbrId(Integer mbrId) {
        List<PointHistory> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(GET_ALL_BY_MBRID);
            ps.setInt(1, mbrId);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(setPointHistory(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }

        if (list.isEmpty()) list.add(null);
        return list;
    }

    private PointHistory setPointHistory(ResultSet rs) {

        PointHistory pointHistory = new PointHistory();

        try {
            pointHistory.setPointId(rs.getInt("pointid"));
            pointHistory.setMbrId(rs.getInt("mbrid"));
            pointHistory.setOrderId(rs.getInt("orderid"));
            pointHistory.setChangeDate(rs.getTimestamp("changedate"));
            pointHistory.setChangeValue(rs.getInt("changevalue"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pointHistory;
    }
}
