package com.twoclothing.web.pointhistory;

import com.twoclothing.utils.JDBCUtil;

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
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, pointHistory.getMbrId());
            ps.setInt(2, pointHistory.getOrderId());
            ps.setTimestamp(3, pointHistory.getChangeDate());
            ps.setInt(4, pointHistory.getChangeValue());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
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
            conn = JDBCUtil.getConnection();
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
            JDBCUtil.close(conn, ps, rs);
        }
        return pointHistory;
    }


    @Override
    public List<PointHistory> getAll() {
        return getAllBy(GET_ALL);
    }

    @Override
    public List<PointHistory> getAllByMbrId(Integer mbrId) {
        return getAllBy(GET_ALL_BY_MBRID, mbrId);
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

    private List<PointHistory> getAllBy(String by, Integer... byid) {
        if (byid.length > 1) return null;
        List<PointHistory> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(by);
            if (byid.length == 1) ps.setInt(1, byid[0]);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(setPointHistory(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }

        if (list.isEmpty()) list.add(null);
        return list;
    }
}
