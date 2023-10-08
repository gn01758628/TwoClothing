package com.twoclothing.web.blacklist;

import java.util.List;

public class BlackListTest {
    public static void main(String[] args) {
        BlackList b1 = new BlackList(1, 2);
        BlackList b2 = new BlackList(2, 3);
        BlackList b3 = new BlackList(3, 4);
        BlackList b4 = new BlackList(3, 2);
        BlackList b5 = new BlackList(4, 2);
        BlackList b6 = new BlackList(4, 2);

        // BlackListTest
        BlackListDAO blackListDAO = new BlackListJDBCDAO();

        // 插入測試
        BlackList[] arr = {b1, b2, b3, b4, b5, b6};
        for (BlackList b : arr) {
            blackListDAO.insert(b);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢測試
        // 單筆查詢
        BlackList byCompositeKey = blackListDAO.getByCompositeKey(1, 2);
        BlackList byCompositeKey1 = blackListDAO.getByCompositeKey(1, 4);
        System.out.println(byCompositeKey);
        System.out.println(byCompositeKey1);

        System.out.println("=====================================================================================================================================");

        // 全部查詢
        List<BlackList> list = blackListDAO.getAll();
        for (BlackList b : list) {
            System.out.println(b);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢某個會員的黑名單清單(依被黑會員的編號排序)
        List<BlackList> list2 = blackListDAO.getAllByMbrId(3);
        for (BlackList b : list2) {
            System.out.println(b);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢某個會員被多少會員黑名單的清單(依被黑他的會員編號排序)
        List<BlackList> list3 = blackListDAO.getAllByBlackId(2);
        for (BlackList b : list3) {
            System.out.println(b);
        }

        System.out.println("=====================================================================================================================================");

        //刪除測試
        blackListDAO.delete(1,2);
        blackListDAO.delete(1,6);

    }
}
