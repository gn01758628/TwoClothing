package com.twoclothing.web.balancehistory;

import com.twoclothing.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BalanceHistoryJDBCDAO implements BalanceHistoryDAO {

    public static final String INSERT_PSSTMT = "INSERT INTO balancehistory (mbrid, orderid, bidorderid, wrid, changedate, changevalue) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String FIND_BY_PK = "SELECT * FROM balancehistory WHERE balanceid = ?";
    public static final String GET_ALL = "SELECT * FROM balancehistory ORDER BY balanceid";
    public static final String GET_ALL_BY_MBRID = "SELECT * FROM balancehistory WHERE mbrid = ? ORDER BY changedate";

    @Override
    public void insert(BalanceHistory balanceHistory) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(INSERT_PSSTMT);
            ps.setInt(1, balanceHistory.getMbrId());

            if (balanceHistory.getOrderId() == null) {
                ps.setNull(2, Types.NULL);
            } else {
                ps.setInt(2, balanceHistory.getOrderId());
            }

            if (balanceHistory.getBidOrderId() == null) {
                ps.setNull(3, Types.NULL);
            } else {
                ps.setInt(3, balanceHistory.getBidOrderId());
            }

            if (balanceHistory.getWrId() == null) {
                ps.setNull(4, Types.NULL);
            } else {
                ps.setInt(4, balanceHistory.getWrId());
            }

            ps.setTimestamp(5, balanceHistory.getChangeDate());
            ps.setInt(6, balanceHistory.getChangeValue());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, null);
        }
        if (count == 1) {
            // TODO 編寫新增成功的執行代碼
            System.out.println("新增成功");
        } else {
            // TODO 編寫新增成功的執行代碼
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
            ps = conn.prepareStatement(FIND_BY_PK);
            ps.setInt(1, balanceId);
            rs = ps.executeQuery();

            rs.next();
            balanceHistory = setBalanceHistory(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }
        return balanceHistory;
    }

    @Override
    public List<BalanceHistory> getAll() {
        List<BalanceHistory> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(getByPrimaryKey(rs.getInt("balanceId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public List<BalanceHistory> getAllByMbrId(Integer mbrId) {
        List<BalanceHistory> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(GET_ALL_BY_MBRID);
            ps.setInt(1, mbrId);
            rs = ps.executeQuery();

            while (rs.next()) {
                BalanceHistory balanceHistory = setBalanceHistory(rs);
                list.add(balanceHistory);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }

        return list;
    }

    private BalanceHistory setBalanceHistory(ResultSet rs) {

        BalanceHistory balanceHistory = new BalanceHistory();

        try {
            balanceHistory.setBalanceId(rs.getInt("balanceid"));
            balanceHistory.setMbrId(rs.getInt("mbrid"));

            if (rs.getObject("orderid") == null) {
                balanceHistory.setOrderId(null);
            } else {
                balanceHistory.setOrderId(rs.getInt("orderid"));
            }

            if (rs.getObject("bidorderid") == null) {
                balanceHistory.setBidOrderId(null);
            } else {
                balanceHistory.setBidOrderId(rs.getInt("bidorderid"));
            }

            if (rs.getObject("wrid") == null) {
                balanceHistory.setWrId(null);
            } else {
                balanceHistory.setWrId(rs.getInt("wrid"));
            }

            balanceHistory.setChangeDate(rs.getTimestamp("changedate"));
            balanceHistory.setChangeValue(rs.getInt("changevalue"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balanceHistory;
    }

}
