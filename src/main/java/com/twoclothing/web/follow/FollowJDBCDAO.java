package com.twoclothing.web.follow;

//import com.twoclothing.utils.JDBCUtil;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;

public class FollowJDBCDAO
//        implements FollowDAO
{
//
//    private static final String INSERT = "INSERT INTO follow (mbrid, followid) VALUES (?,?)";
//
//    private static final String GET_BY_CPK = "SELECT * FROM follow WHERE mbrid = ? AND followid = ?";
//
//    private static final String GET_ALL = "SELECT * FROM follow";
//
//    private static final String GET_ALL_BY_MBRID = "SELECT * FROM follow WHERE mbrid = ? ORDER BY followid";
//
//    private static final String GET_ALL_BY_FOLLOWID = "SELECT * FROM follow WHERE followid = ? ORDER BY mbrid";
//
//    private static final String DELETE = "DELETE FROM follow WHERE mbrid = ? AND followid = ?";
//
//    @Override
//    public void insert(Follow follow) {
//        Connection conn = null;
//        PreparedStatement ps = null;
//        int count = 0;
//
//        try {
//            conn = JDBCUtil.getConnection();
//            ps = conn.prepareStatement(INSERT);
//            ps.setInt(1, follow.getMbrId());
//            ps.setInt(2, follow.getFollowId());
//            count = ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JDBCUtil.close(conn, ps, null);
//        }
//        if (count == 1) {
//            // 編寫新增成功的執行代碼
//            System.out.println("新增成功");
//        } else {
//            // 編寫新增失敗的執行代碼
//            System.out.println("新增失敗");
//        }
//    }
//
//    @Override
//    public Follow getByCompositeKey(Integer mbrId, Integer followId) {
//        Follow follow = new Follow();
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//            conn = JDBCUtil.getConnection();
//            ps = conn.prepareStatement(GET_BY_CPK);
//            ps.setInt(1, mbrId);
//            ps.setInt(2, followId);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                follow = setFollow(rs);
//            } else {
//                return null;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JDBCUtil.close(conn, ps, rs);
//        }
//        return follow;
//    }
//
//    @Override
//    public List<Follow> getAll() {
//        return getAllBy(GET_ALL);
//    }
//
//    @Override
//    public List<Follow> getAllByMbrId(Integer mbrId) {
//        return getAllBy(GET_ALL_BY_MBRID, mbrId);
//    }
//
//    @Override
//    public List<Follow> getAllByFollowId(Integer followId) {
//        return getAllBy(GET_ALL_BY_FOLLOWID, followId);
//    }
//
//    @Override
//    public void delete(Integer mbrId, Integer followId) {
//        Connection conn = null;
//        PreparedStatement ps = null;
//        int count = 0;
//
//        try {
//            conn = JDBCUtil.getConnection();
//            ps = conn.prepareStatement(DELETE);
//            ps.setInt(1, mbrId);
//            ps.setInt(2, followId);
//            count = ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JDBCUtil.close(conn, ps, null);
//        }
//
//        if (count == 1) {
//            // 編寫刪除成功的執行代碼
//            System.out.println("刪除成功");
//        } else {
//            // 編寫刪除失敗的執行代碼
//            System.out.println("刪除失敗");
//        }
//    }
//
//    private Follow setFollow(ResultSet rs) {
//
//        Follow follow = new Follow();
//
//        try {
//            follow.setMbrId(rs.getInt("mbrid"));
//            follow.setFollowId(rs.getInt("followid"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return follow;
//    }
//
//    private List<Follow> getAllBy(String by, Integer... byid) {
//        if (byid.length > 1) return null;
//        List<Follow> list = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//            conn = JDBCUtil.getConnection();
//            ps = conn.prepareStatement(by);
//            if (byid.length == 1) ps.setInt(1, byid[0]);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                list.add(setFollow(rs));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JDBCUtil.close(conn, ps, rs);
//        }
//
//        if (list.isEmpty()) list.add(null);
//        return list;
//    }
}
