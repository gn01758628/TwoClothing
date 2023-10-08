package com.twoclothing.web.balancehistory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class BalanceHistoryTest {
    public static void main(String[] args) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentDateTime);
        BalanceHistory b1 = new BalanceHistory(null, 5, null, null, 10, timestamp, -50);
        BalanceHistory b2 = new BalanceHistory(null, 6, 105, null, null, timestamp, 500);
        BalanceHistory b3 = new BalanceHistory(null, 5, null, 210, null, timestamp, -120);
        BalanceHistory b4 = new BalanceHistory(null, 5, null, null, 20, timestamp, -80);
        BalanceHistory b5 = new BalanceHistory(null, 6, 106, null, null, timestamp, -100);

        // BalanceHistoryTest
        BalanceHistoryDAO balanceHistoryDAO = new BalanceHistoryJDBCDAO();

        // 插入測試
        BalanceHistory[] arr = {b1, b2, b3, b4, b5};
        for (BalanceHistory b : arr) {
            balanceHistoryDAO.insert(b);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢測試
        // 單筆查詢
        BalanceHistory byPrimaryKey = balanceHistoryDAO.getByPrimaryKey(1);
        System.out.println(byPrimaryKey);

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
    }
}
