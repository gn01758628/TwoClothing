//package com.twoclothing.model.shipsetting;
//
//import com.twoclothing.utils.JDBCUtil;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class ShipSettingJDBCDAO implements ShipSettingDAO {
//
//    private static final String INSERT = "INSERT INTO shipsetting (mbrid, receivename, receivephone, receiveaddress) VALUES (?,?,?,?)";
//
//    private static final String GET_BY_PK = "SELECT * FROM shipsetting WHERE shipid = ?";
//
//    private static final String GET_ALL = "SELECT * FROM shipsetting";
//
//    private static final String GET_ALL_BY_MBRID = "SELECT * FROM shipsetting WHERE mbrid = ? ORDER BY shipid";
//
//    private static final String UPDATE = "UPDATE shipsetting SET mbrid = ?, receivename = ?, receivephone = ?, receiveaddress = ? WHERE shipid = ?";
//
//    private static final String DELETE = "DELETE FROM shipsetting WHERE shipid = ?";
//
//    @Override
//    public void insert(ShipSetting shipSetting) {
//        Connection conn = null;
//        PreparedStatement ps = null;
//        int count = 0;
//
//        try {
//            conn = JDBCUtil.getConnection();
//            ps = conn.prepareStatement(INSERT);
//            ps.setInt(1, shipSetting.getMbrId());
//            ps.setString(2, shipSetting.getReceiveName());
//            ps.setString(3, shipSetting.getReceivePhone());
//            ps.setString(4, shipSetting.getReceiveAddress());
//            count = ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JDBCUtil.close(conn, ps, null);
//        }
//
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
//    public ShipSetting getByPrimaryKey(Integer shipId) {
//        ShipSetting shipSetting = new ShipSetting();
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//            conn = JDBCUtil.getConnection();
//            ps = conn.prepareStatement(GET_BY_PK);
//            ps.setInt(1, shipId);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                shipSetting = setShipSetting(rs);
//            } else {
//                return null;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JDBCUtil.close(conn, ps, rs);
//        }
//        return shipSetting;
//    }
//
//    @Override
//    public List<ShipSetting> getAll() {
//        return getAllBy(GET_ALL);
//    }
//
//    @Override
//    public List<ShipSetting> getAllByMbrId(Integer mbrId) {
//        return getAllBy(GET_ALL_BY_MBRID,mbrId);
//    }
//
//    @Override
//    public void update(ShipSetting shipSetting) {
//        Connection conn = null;
//        PreparedStatement ps = null;
//        int count = 0;
//
//        try {
//            conn = JDBCUtil.getConnection();
//            ps = conn.prepareStatement(UPDATE);
//            ps.setInt(1, shipSetting.getMbrId());
//            ps.setString(2, shipSetting.getReceiveName());
//            ps.setString(3, shipSetting.getReceivePhone());
//            ps.setString(4, shipSetting.getReceiveAddress());
//            ps.setInt(5, shipSetting.getShipId());
//            count = ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JDBCUtil.close(conn, ps, null);
//        }
//
//        if (count == 1) {
//            // 編寫修改成功的執行代碼
//            System.out.println("修改成功");
//        } else {
//            // 編寫修改失敗的執行代碼
//            System.out.println("修改失敗");
//        }
//    }
//
//    @Override
//    public void delete(Integer shipId) {
//        Connection conn = null;
//        PreparedStatement ps = null;
//        int count = 0;
//
//        try {
//            conn = JDBCUtil.getConnection();
//            ps = conn.prepareStatement(DELETE);
//            ps.setInt(1, shipId);
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
//    private ShipSetting setShipSetting(ResultSet rs) {
//
//        ShipSetting shipSetting = new ShipSetting();
//
//        try {
//            shipSetting.setShipId(rs.getInt("shipId"));
//            shipSetting.setMbrId(rs.getInt("mbrId"));
//            shipSetting.setReceiveName(rs.getString("receivename"));
//            shipSetting.setReceivePhone(rs.getString("receivephone"));
//            shipSetting.setReceiveAddress(rs.getString("receiveaddress"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return shipSetting;
//    }
//
//    private List<ShipSetting> getAllBy(String by, Integer... byid) {
//        if (byid.length > 1) return null;
//        List<ShipSetting> list = new ArrayList<>();
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
//                list.add(setShipSetting(rs));
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
//}
