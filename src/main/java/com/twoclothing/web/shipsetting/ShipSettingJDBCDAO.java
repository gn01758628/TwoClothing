package com.twoclothing.web.shipsetting;

import com.twoclothing.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipSettingJDBCDAO implements ShipSettingDAO {

    private static final String INSERT = "INSERT INTO shipsetting (mbrid, receivename, receivephone, receiveaddress) VALUES (?,?,?,?)";

    private static final String FIND_BY_PK = "SELECT * FROM shipsetting WHERE shipid = ?";

    private static final String GET_ALL = "SELECT * FROM shipsetting";

    private static final String GET_ALL_BY_MBRID = "SELECT * FROM shipsetting WHERE mbrid = ? ORDER BY shipid";

    private static final String UPDATE = "UPDATE shipsetting SET mbrid = ?, receivename = ?, receivephone = ?, receiveaddress = ? WHERE shipid = ?";

    private static final String DELETE = "DELETE FROM shipsetting WHERE shipid = ?";

    @Override
    public void insert(ShipSetting shipSetting) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, shipSetting.getMbrId());
            ps.setString(2, shipSetting.getReceiveName());
            ps.setString(3, shipSetting.getReceivePhone());
            ps.setString(4, shipSetting.getReceiveAddress());
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
    public ShipSetting getByPrimaryKey(Integer shipId) {
        ShipSetting shipSetting = new ShipSetting();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(FIND_BY_PK);
            ps.setInt(1, shipId);
            rs = ps.executeQuery();

            if (rs.next()) {
                shipSetting = setShipSetting(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }
        return shipSetting;
    }

    @Override
    public List<ShipSetting> getAll() {
        List<ShipSetting> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                ShipSetting shipSetting = getByPrimaryKey(rs.getInt("shipid"));
                list.add(shipSetting);
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
    public List<ShipSetting> getAllByMbrId(Integer mbrId) {
        List<ShipSetting> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(GET_ALL_BY_MBRID);
            ps.setInt(1, mbrId);
            rs = ps.executeQuery();

            while (rs.next()) {
                ShipSetting shipSetting = setShipSetting(rs);
                list.add(shipSetting);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }

        if (list.isEmpty()) list.add(null);
        return list;
    }

    @Override
    public void update(ShipSetting shipSetting) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setInt(1, shipSetting.getMbrId());
            ps.setString(2, shipSetting.getReceiveName());
            ps.setString(3, shipSetting.getReceivePhone());
            ps.setString(4, shipSetting.getReceiveAddress());
            ps.setInt(5, shipSetting.getShipId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    @Override
    public void delete(Integer shipId) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, shipId);
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

    private ShipSetting setShipSetting(ResultSet rs) {

        ShipSetting shipSetting = new ShipSetting();

        try {
            shipSetting.setShipId(rs.getInt("shipId"));
            shipSetting.setMbrId(rs.getInt("mbrId"));
            shipSetting.setReceiveName(rs.getString("receivename"));
            shipSetting.setReceivePhone(rs.getString("receivephone"));
            shipSetting.setReceiveAddress(rs.getString("receiveaddress"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipSetting;
    }
}
