package com.twoclothing.web.members;

import com.twoclothing.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MembersJDBCDAO implements MembersDAO {

    private static final String INSERT = "INSERT INTO members (email, pswdhash, mbrstatus) VALUES (?, ?, ?)";

    @Override
    public void insert(Members members) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, members.getEmail());
            ps.setString(2, members.getPswdHash());
            ps.setInt(3, members.getMbrStatus());
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
    public Members getByPrimaryKey(Integer mbrId) {
        return null;
    }

    @Override
    public List<Members> getAll() {
        return null;
    }

    @Override
    public List<Members> getAllByMbrName(String mbrName) {
        return null;
    }

    @Override
    public List<Members> getAllByMbrStatus(Integer mbrStatus) {
        return null;
    }

    @Override
    public List<Members> getAllBySellScore(Integer sellScore) {
        return null;
    }

    @Override
    public List<Members> getAllByBuyScore(Integer buyScore) {
        return null;
    }

    @Override
    public void updateMbrName(Members members) {

    }

    @Override
    public void updateMbrPSWDHASH(Members members) {

    }

    @Override
    public void updateMbrStatus(Members members) {

    }

    @Override
    public void updateAvatar(Members members) {

    }

    @Override
    public void updateShopImg01(Members members) {

    }

    @Override
    public void updateShopImg02(Members members) {

    }

    @Override
    public void updateMbrPoint(Members members) {

    }

    @Override
    public void updateBalance(Members members) {

    }

    @Override
    public void updateBuyStarRating(Members members) {

    }

    @Override
    public void updateSellStarRating(Members members) {

    }

    @Override
    public void updateLastLogin(Members members) {

    }

    @Override
    public void updateSellScore(Members members) {

    }

    @Override
    public void updateBuyScore(Members members) {

    }

    @Override
    public void delete(Integer mbrId) {

    }
}
