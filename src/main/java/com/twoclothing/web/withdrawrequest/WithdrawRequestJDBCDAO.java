package com.twoclothing.web.withdrawrequest;

import com.twoclothing.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WithdrawRequestJDBCDAO implements WithdrawRequestDAO {

    private static final String INSERT = "INSERT INTO withdrawrequest (mbrid, amount, mbraccount, reqdate, reqstatus, empid, checkdate, note) VALUES (?,?,?,?,?,?,?,?)";

    private static final String GET_BY_PK = "SELECT * FROM withdrawrequest WHERE wrid = ?";

    private static final String GET_All = "SELECT * FROM withdrawrequest";

    private static final String GET_All_BY_EMPID = "SELECT * FROM withdrawrequest WHERE empid = ? ORDER BY reqdate";

    private static final String GET_All_BY_MBRID = "SELECT * FROM withdrawrequest WHERE mbrid = ? ORDER BY reqdate";

    private static final String GET_All_BY_REQSTATUS = "SELECT * FROM withdrawrequest WHERE reqstatus = ? ORDER BY reqdate";

    private static final String UPDATE = "UPDATE withdrawrequest SET mbrid = ?, amount = ?, mbraccount = ?, reqdate = ?, reqstatus = ?, empid = ?, checkdate = ?, note = ? WHERE wrid = ?";

    @Override
    public void insert(WithdrawRequest withdrawRequest) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, withdrawRequest.getMbrId());
            ps.setInt(2, withdrawRequest.getAmount());
            ps.setString(3, withdrawRequest.getMbrAccount());
            ps.setTimestamp(4, withdrawRequest.getReqDate());
            ps.setInt(5, withdrawRequest.getReqStatus());
            ps.setObject(6, withdrawRequest.getEmpId(), Types.INTEGER);
            ps.setObject(7, withdrawRequest.getCheckDate(), Types.TIMESTAMP);
            ps.setObject(8, withdrawRequest.getNote(), Types.VARCHAR);
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
    public WithdrawRequest getByPrimaryKey(Integer wrId) {
        WithdrawRequest withdrawRequest = new WithdrawRequest();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(GET_BY_PK);
            ps.setInt(1, wrId);
            rs = ps.executeQuery();

            if (rs.next()) {
                withdrawRequest = setWithdrawRequest(rs);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }
        return withdrawRequest;
    }

    @Override
    public List<WithdrawRequest> getAll() {
        return getAllBy(GET_All);
    }

    @Override
    public List<WithdrawRequest> getAllByEmpID(Integer empId) {
        return getAllBy(GET_All_BY_EMPID, empId);
    }

    @Override
    public List<WithdrawRequest> getAllByMbrid(Integer mbrId) {
        return getAllBy(GET_All_BY_MBRID, mbrId);
    }

    @Override
    public List<WithdrawRequest> getAllByReqStatus(Integer reqstatus) {
        return getAllBy(GET_All_BY_REQSTATUS, reqstatus);
    }

    @Override
    public void update(WithdrawRequest withdrawRequest) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setInt(1, withdrawRequest.getMbrId());
            ps.setInt(2, withdrawRequest.getAmount());
            ps.setString(3, withdrawRequest.getMbrAccount());
            ps.setTimestamp(4, withdrawRequest.getReqDate());
            ps.setInt(5, withdrawRequest.getReqStatus());
            ps.setObject(6, withdrawRequest.getEmpId(), Types.INTEGER);
            ps.setObject(7, withdrawRequest.getCheckDate(), Types.TIMESTAMP);
            ps.setObject(8, withdrawRequest.getNote(), Types.VARCHAR);
            ps.setInt(9, withdrawRequest.getWrId());

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

    private WithdrawRequest setWithdrawRequest(ResultSet rs) {

        WithdrawRequest withdrawRequest = new WithdrawRequest();

        try {
            withdrawRequest.setWrId(rs.getInt("wrid"));
            withdrawRequest.setMbrId(rs.getInt("mbrid"));
            withdrawRequest.setAmount(rs.getInt("amount"));
            withdrawRequest.setMbrAccount(rs.getString("mbraccount"));
            withdrawRequest.setReqDate(rs.getTimestamp("reqdate"));
            withdrawRequest.setReqStatus(rs.getInt("reqstatus"));
            withdrawRequest.setEmpId(rs.getObject("empid", Integer.class));
            withdrawRequest.setCheckDate(rs.getObject("checkdate", Timestamp.class));
            withdrawRequest.setNote(rs.getObject("note", String.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return withdrawRequest;
    }

    private List<WithdrawRequest> getAllBy(String by, Integer... byid) {
        if (byid.length > 1) return null;
        List<WithdrawRequest> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(by);
            if (byid.length == 1) ps.setInt(1, byid[0]);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(setWithdrawRequest(rs));
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

