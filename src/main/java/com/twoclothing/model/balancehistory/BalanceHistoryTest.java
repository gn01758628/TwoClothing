package com.twoclothing.model.balancehistory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class BalanceHistoryTest {
    public static void main(String[] args) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp[] arr1 = new Timestamp[5];
        for (int i = 0; i < arr1.length; i++) {
            Random random = new Random();
            arr1[i] = Timestamp.valueOf(currentDateTime.minusSeconds(Math.abs(random.nextInt() % 10000000)));
        }
        BalanceHistory b1 = new BalanceHistory(null, 5, null, null, 10, arr1[0], -5000);
        BalanceHistory b2 = new BalanceHistory(null, 6, 105, null, null, arr1[1], 1200);
        BalanceHistory b3 = new BalanceHistory(null, 5, null, 210, null, arr1[2], -520);
        BalanceHistory b4 = new BalanceHistory(null, 5, null, null, 20, arr1[3], 800);
        BalanceHistory b5 = new BalanceHistory(null, 6, 106, null, null, arr1[4], -100);

        // BalanceHistoryTest
        BalanceHistoryDAO balanceHistoryDAO = new BalanceHistoryJDBCDAO();

        // 插入測試
        BalanceHistory[] arr2 = {b1, b2, b3, b4, b5};
        for (BalanceHistory b : arr2) {
            balanceHistoryDAO.insert(b);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢測試
        // 單筆查詢
        BalanceHistory byPrimaryKey = balanceHistoryDAO.getByPrimaryKey(5);
        BalanceHistory byPrimaryKey1 = balanceHistoryDAO.getByPrimaryKey(6);
        System.out.println(byPrimaryKey);
        System.out.println(byPrimaryKey1);

        System.out.println("=====================================================================================================================================");

        // 全部查詢
        List<BalanceHistory> list = balanceHistoryDAO.getAll();
        for (BalanceHistory b: list) {
            System.out.println(b);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢某個會員的全部紀錄(依時間排序)
        List<BalanceHistory> list2 = balanceHistoryDAO.getAllByMbrId(5);
        for (BalanceHistory b: list2) {
            System.out.println(b);
        }

        List<BalanceHistory> list3 = balanceHistoryDAO.getAllByMbrId(7);
        for (BalanceHistory b: list3) {
            System.out.println(b);
        }
    }
}
