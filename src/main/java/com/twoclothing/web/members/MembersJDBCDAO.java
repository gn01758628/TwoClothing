package com.twoclothing.web.members;

import com.twoclothing.utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembersJDBCDAO implements MembersDAO {

    private static final String INSERT = "INSERT INTO members (email, pswdhash) VALUES (?, ?)";

    private static final String GET_BY_PK = "SELECT * FROM members WHERE mbrid = ?";

    private static final String GET_ALL = "SELECT * FROM members";

    private static final String GET_ALL_BY_MBRNAME = "SELECT * FROM members WHERE mbrname LIKE ? ORDER BY mbrid";

    private static final String GET_ALL_BY_MBRSTATUS = "SELECT * FROM members WHERE mbrstatus = ?";

    private static final String GET_ALL_BY_SELLSCORE = "SELECT * FROM members WHERE sellscore <= ? ORDER BY sellscore";

    private static final String GET_ALL_BY_BUYSCORE = "SELECT * FROM members WHERE buyscore <= ? ORDER BY buyscore";

    private static final String UPDATE_NAME = "UPDATE members SET mbrname = ? WHERE mbrid = ?";

    private static final String UPDATE_PSWD_HASH = "UPDATE members SET pswdhash = ? WHERE mbrid = ?";

    private static final String UPDATE_MBR_STATUS = "UPDATE members SET mbrstatus = ? WHERE mbrid = ?";

    private static final String UPDATE_AVATAR = "UPDATE members SET avatar = ? WHERE mbrid = ?";

    private static final String UPDATE_SHOP_IMG01 = "UPDATE members SET shopimg01 = ? WHERE mbrid = ?";

    private static final String UPDATE_SHOP_IMG02 = "UPDATE members SET shopimg02 = ? WHERE mbrid = ?";

    private static final String UPDATE_MBR_POINT = "UPDATE members SET mbrpoint = ? WHERE mbrid = ?";

    private static final String UPDATE_BALANCE = "UPDATE members SET balance = ? WHERE mbrid = ?";

    private static final String UPDATE_BUY_STAR_RATING = "UPDATE members SET buystar = ?, buyrating = ? WHERE mbrid = ?";

    private static final String UPDATE_SELL_STAR_RATING = "UPDATE members SET sellstar = ?, sellrating = ? WHERE mbrid = ?";

    private static final String UPDATE_LAST_LOGIN = "UPDATE members SET lastlogin = ? WHERE mbrid = ?";

    private static final String UPDATE_SELL_SCORE = "UPDATE members SET sellscore = ? WHERE mbrid = ?";

    private static final String UPDATE_BUY_SCORE = "UPDATE members SET buyscore = ? WHERE mbrid = ?";

    private static final String DELETE = "DELETE FROM members WHERE mbrid = ?";


    @Override
    public void insert(Members members) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, members.getEmail());
            ps.setString(2, members.getPswdHash());
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
    public Members getByPrimaryKey(Integer mbrId) {
        Members members = new Members();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(GET_BY_PK);
            ps.setInt(1, mbrId);
            rs = ps.executeQuery();

            if (rs.next()) {
                members = setMembers(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return members;
    }

    @Override
    public List<Members> getAll() {
        return getAllBy(GET_ALL);
    }

    @Override
    public List<Members> getAllByMbrName(String mbrName) {
        return getAllBy(GET_ALL_BY_MBRNAME, "%" + mbrName + "%");
    }

    @Override
    public List<Members> getAllByMbrStatus(Integer mbrStatus) {
        return getAllBy(GET_ALL_BY_MBRSTATUS, mbrStatus);
    }

    @Override
    public List<Members> getAllBySellScore(Integer sellScore) {
        return getAllBy(GET_ALL_BY_SELLSCORE, sellScore);
    }

    @Override
    public List<Members> getAllByBuyScore(Integer buyScore) {
        return getAllBy(GET_ALL_BY_BUYSCORE, buyScore);
    }

    @Override
    public void updateMbrName(Members members) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(UPDATE_NAME);
            ps.setObject(1, members.getMbrName(), Types.VARCHAR);
            ps.setInt(2, members.getMbrId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫修改成功的執行代碼
            System.out.println("修改成功");
        } else {
            // 編寫修改失敗的執行代碼
            System.out.println("修改失敗");
        }
    }

    @Override
    public void updatePSWDHash(Members members) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(UPDATE_PSWD_HASH);
            ps.setString(1, members.getPswdHash());
            ps.setInt(2, members.getMbrId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫修改成功的執行代碼
            System.out.println("修改成功");
        } else {
            // 編寫修改失敗的執行代碼
            System.out.println("修改失敗");
        }
    }

    @Override
    public void updateMbrStatus(Members members) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(UPDATE_MBR_STATUS);
            ps.setInt(1, members.getMbrStatus());
            ps.setInt(2, members.getMbrId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫修改成功的執行代碼
            System.out.println("修改成功");
        } else {
            // 編寫修改失敗的執行代碼
            System.out.println("修改失敗");
        }
    }

    @Override
    public void updateAvatar(Members members) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(UPDATE_AVATAR);
            ps.setBytes(1, members.getAvatar());
            ps.setInt(2, members.getMbrId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫修改成功的執行代碼
            System.out.println("修改成功");
        } else {
            // 編寫修改失敗的執行代碼
            System.out.println("修改失敗");
        }
    }

    @Override
    public void updateShopImg01(Members members) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(UPDATE_SHOP_IMG01);
            ps.setBytes(1, members.getShopImg01());
            ps.setInt(2, members.getMbrId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫修改成功的執行代碼
            System.out.println("修改成功");
        } else {
            // 編寫修改失敗的執行代碼
            System.out.println("修改失敗");
        }
    }

    @Override
    public void updateShopImg02(Members members) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(UPDATE_SHOP_IMG02);
            ps.setBytes(1, members.getShopImg02());
            ps.setInt(2, members.getMbrId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫修改成功的執行代碼
            System.out.println("修改成功");
        } else {
            // 編寫修改失敗的執行代碼
            System.out.println("修改失敗");
        }
    }

    @Override
    public void updateMbrPoint(Members members) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(UPDATE_MBR_POINT);
            ps.setInt(1, members.getMbrPoint());
            ps.setInt(2, members.getMbrId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫修改成功的執行代碼
            System.out.println("修改成功");
        } else {
            // 編寫修改失敗的執行代碼
            System.out.println("修改失敗");
        }
    }

    @Override
    public void updateBalance(Members members) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(UPDATE_BALANCE);
            ps.setInt(1, members.getBalance());
            ps.setInt(2, members.getMbrId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫修改成功的執行代碼
            System.out.println("修改成功");
        } else {
            // 編寫修改失敗的執行代碼
            System.out.println("修改失敗");
        }
    }

    @Override
    public void updateBuyStarRating(Members members) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(UPDATE_BUY_STAR_RATING);
            ps.setInt(1, members.getBuyStar());
            ps.setInt(2, members.getBuyRating());
            ps.setInt(3, members.getMbrId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫修改成功的執行代碼
            System.out.println("修改成功");
        } else {
            // 編寫修改失敗的執行代碼
            System.out.println("修改失敗");
        }
    }

    @Override
    public void updateSellStarRating(Members members) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(UPDATE_SELL_STAR_RATING);
            ps.setInt(1, members.getSellStar());
            ps.setInt(2, members.getSellRating());
            ps.setInt(3, members.getMbrId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫修改成功的執行代碼
            System.out.println("修改成功");
        } else {
            // 編寫修改失敗的執行代碼
            System.out.println("修改失敗");
        }
    }

    @Override
    public void updateLastLogin(Members members) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(UPDATE_LAST_LOGIN);
            ps.setObject(1, members.getLastLogin(), Types.TIMESTAMP);
            ps.setInt(2, members.getMbrId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫修改成功的執行代碼
            System.out.println("修改成功");
        } else {
            // 編寫修改失敗的執行代碼
            System.out.println("修改失敗");
        }
    }

    @Override
    public void updateSellScore(Members members) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(UPDATE_SELL_SCORE);
            ps.setInt(1, members.getSellScore());
            ps.setInt(2, members.getMbrId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫修改成功的執行代碼
            System.out.println("修改成功");
        } else {
            // 編寫修改失敗的執行代碼
            System.out.println("修改失敗");
        }
    }

    @Override
    public void updateBuyScore(Members members) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(UPDATE_BUY_SCORE);
            ps.setInt(1, members.getBuyScore());
            ps.setInt(2, members.getMbrId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫修改成功的執行代碼
            System.out.println("修改成功");
        } else {
            // 編寫修改失敗的執行代碼
            System.out.println("修改失敗");
        }
    }

    @Override
    public void delete(Integer mbrId) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, mbrId);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫刪除成功的執行代碼
            System.out.println("刪除成功");
        } else {
            // 編寫刪除失敗的執行代碼
            System.out.println("刪除失敗");
        }
    }

    private Members setMembers(ResultSet rs) {

        Members members = new Members();

        try {
            members.setMbrId(rs.getInt("mbrid"));
            members.setMbrName(rs.getObject("mbrname", String.class));
            members.setEmail(rs.getString("email"));
            members.setPswdHash(rs.getString("pswdhash"));
            members.setMbrStatus(rs.getInt("mbrstatus"));

            members.setAvatar(rs.getBytes("avatar"));
            members.setShopImg01(rs.getBytes("shopimg01"));
            members.setShopImg02(rs.getBytes("shopimg02"));

            members.setMbrPoint(rs.getInt("mbrpoint"));
            members.setBalance(rs.getInt("balance"));
            members.setBuyStar(rs.getInt("buystar"));
            members.setBuyRating(rs.getInt("buyrating"));
            members.setSellStar(rs.getInt("sellstar"));
            members.setSellRating(rs.getInt("sellrating"));
            members.setLastLogin(rs.getObject("lastlogin", Timestamp.class));
            members.setSellScore(rs.getInt("sellscore"));
            members.setBuyScore(rs.getInt("buyscore"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    private List<Members> getAllBy(String by, Object... byid) {
        if (byid.length > 1) return null;
        List<Members> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(by);
            if (byid.length == 1) ps.setObject(1, byid[0]);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(setMembers(rs));
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
