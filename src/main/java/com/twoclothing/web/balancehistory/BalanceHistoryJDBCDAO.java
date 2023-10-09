package com.twoclothing.web.balancehistory;

import com.twoclothing.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BalanceHistoryJDBCDAO implements BalanceHistoryDAO {

    public static final String INSERT = "INSERT INTO balancehistory (mbrid, orderid, bidorderid, wrid, changedate, changevalue) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String GET_BY_PK = "SELECT * FROM balancehistory WHERE balanceid = ?";
    public static final String GET_ALL = "SELECT * FROM balancehistory ORDER BY balanceid";
    public static final String GET_ALL_BY_MBRID = "SELECT * FROM balancehistory WHERE mbrid = ? ORDER BY changedate";

    @Override
    public void insert(BalanceHistory balanceHistory) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, balanceHistory.getMbrId());
            ps.setObject(2, balanceHistory.getOrderId(), Types.INTEGER);
            ps.setObject(3, balanceHistory.getBalanceId(), Types.INTEGER);
            ps.setObject(4, balanceHistory.getWrId(), Types.INTEGER);
            ps.setTimestamp(5, balanceHistory.getChangeDate());
            ps.setInt(6, balanceHistory.getChangeValue());
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
    public BalanceHistory getByPrimaryKey(Integer balanceId) {
        BalanceHistory balanceHistory = new BalanceHistory();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(GET_BY_PK);
            ps.setInt(1, balanceId);
            rs = ps.executeQuery();

            if (rs.next()) {
                balanceHistory = setBalanceHistory(rs);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }
        return balanceHistory;
    }

    @Override
    public List<BalanceHistory> getAll() {
        return getAllBy(GET_ALL);
    }

    @Override
    public List<BalanceHistory> getAllByMbrId(Integer mbrId) {
        return getAllBy(GET_ALL_BY_MBRID, mbrId);
    }

    private BalanceHistory setBalanceHistory(ResultSet rs) {

        BalanceHistory balanceHistory = new BalanceHistory();

        try {
            balanceHistory.setBalanceId(rs.getInt("balanceid"));
            balanceHistory.setMbrId(rs.getInt("mbrid"));
            balanceHistory.setOrderId(rs.getObject("orderid", Integer.class));
            balanceHistory.setBidOrderId(rs.getObject("bidorderid", Integer.class));
            balanceHistory.setWrId(rs.getObject("wrid", Integer.class));
            balanceHistory.setChangeDate(rs.getTimestamp("changedate"));
            balanceHistory.setChangeValue(rs.getInt("changevalue"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balanceHistory;
    }

    private List<BalanceHistory> getAllBy(String by, Integer... byid) {
        if (byid.length > 1) return null;
        List<BalanceHistory> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(by);
            if (byid.length == 1) ps.setInt(1, byid[0]);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(setBalanceHistory(rs));
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
