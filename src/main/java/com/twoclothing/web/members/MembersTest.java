package com.twoclothing.web.members;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class MembersTest {
    public static void main(String[] args) throws IOException {
        Members m1 = new Members("email1@example.com", "hash1");
        Members m2 = new Members("email2@example.com", "hash2");
        Members m3 = new Members("email3@example.com", "hash3");
        Members m4 = new Members("email4@example.com", "hash4");
        Members m5 = new Members("email5@example.com", "hash5");
        Members m6 = new Members("email6@example.com", "hash6");
        Members m7 = new Members("email7@example.com", "hash7");
        Members m8 = new Members("email8@example.com", "hash8");
        Members m9 = new Members("email9@example.com", "hash9");
        Members m10 = new Members("email10@example.com", "hash10");

        // MembersTest
        MembersDAO membersDAO = new MembersJDBCDAO();

        // 插入測試
        Members[] arr = {m1, m2, m3, m4, m5, m6, m7, m8, m9, m10};
        for (Members m : arr) {
            membersDAO.insert(m);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢測試
        // 單筆查詢
        Members byPrimaryKey = membersDAO.getByPrimaryKey(1);
        Members byPrimaryKey1 = membersDAO.getByPrimaryKey(11);
        System.out.println(byPrimaryKey);
        System.out.println(byPrimaryKey1);

        System.out.println("=====================================================================================================================================");

        // 多筆查詢
        // 查詢全部
        List<Members> list = membersDAO.getAll();
        for (Members m : list) {
            System.out.println(m);
        }

        System.out.println("=====================================================================================================================================");

        // 會員名稱模糊查詢
        List<Members> list2 = membersDAO.getAllByMbrName("l");
        for (Members m : list2) {
            System.out.println(m);
        }

        // 查詢特定狀態的會員

        System.out.println("=====================================================================================================================================");

        List<Members> list3 = membersDAO.getAllByMbrStatus(1);
        for (Members m : list3) {
            System.out.println(m);
        }

        // 查詢賣家分數小於等於指定數字的會員

        System.out.println("=====================================================================================================================================");

        List<Members> list4 = membersDAO.getAllBySellScore(5);
        for (Members m : list4) {
            System.out.println(m);
        }

        // 查詢買家分數小於等於指定數字的會員

        System.out.println("=====================================================================================================================================");

        List<Members> list5 = membersDAO.getAllByBuyScore(5);
        for (Members m : list5) {
            System.out.println(m);
        }

        System.out.println("=====================================================================================================================================");

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        byte[] avatar01 = getPictureByteArray("images\\avatar01.jpg");
        byte[] shopimg01 = getPictureByteArray("images\\shopimg01.jpg");
        byte[] shopimg02 = getPictureByteArray("images\\shopimg02.jpg");

        Members members = new Members();
        members.setMbrId(10);
        members.setMbrName("影山茂夫");
        members.setPswdHash("Mob3345678");
        members.setMbrStatus(1);
        members.setAvatar(avatar01);
        members.setShopImg01(shopimg01);
        members.setShopImg02(shopimg02);
        members.setMbrPoint(5000);
        members.setBalance(6200);
        members.setBuyStar(100);
        members.setBuyRating(28);
        members.setSellStar(60);
        members.setSellRating(15);
        members.setLastLogin(timestamp);
        members.setSellScore(9);
        members.setBuyScore(8);

        // 修改測試

        membersDAO.updateMbrName(members);
        membersDAO.updatePSWDHash(members);
        membersDAO.updateMbrStatus(members);
        membersDAO.updateAvatar(members);
        membersDAO.updateShopImg01(members);
        membersDAO.updateShopImg02(members);
        membersDAO.updateMbrPoint(members);
        membersDAO.updateBalance(members);
        membersDAO.updateBuyStarRating(members);
        membersDAO.updateSellStarRating(members);
        membersDAO.updateLastLogin(members);
        membersDAO.updateSellScore(members);
        membersDAO.updateBuyScore(members);

        System.out.println("=====================================================================================================================================");

        // 刪除測試

        membersDAO.delete(1);
        membersDAO.delete(11);

    }

    // 圖片讀取
    public static byte[] getPictureByteArray(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        byte[] pictureArr = fis.readAllBytes();
        fis.close();
        return pictureArr;
    }


}
