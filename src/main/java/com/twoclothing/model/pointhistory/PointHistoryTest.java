package com.twoclothing.model.pointhistory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class PointHistoryTest {
    public static void main(String[] args) {

        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp[] arr1 = new Timestamp[5];
        for (int i = 0; i < arr1.length; i++) {
            Random random = new Random();
            arr1[i] = Timestamp.valueOf(currentDateTime.minusSeconds(Math.abs(random.nextInt() % 10000000)));
        }
        PointHistory p1 = new PointHistory(null, 10, 105, arr1[0], 50);
        PointHistory p2 = new PointHistory(null, 25, 115, arr1[1], -1000);
        PointHistory p3 = new PointHistory(null, 10, 125, arr1[2], -500);
        PointHistory p4 = new PointHistory(null, 10, 135, arr1[3], 500);
        PointHistory p5 = new PointHistory(null, 25, 145, arr1[4], -10);

        // PointHistory Test
        PointHistoryDAO pointHistoryDAO = new PointHistoryJDBCDAO();

        // 插入測試
        PointHistory[] arr2 = {p1, p2, p3, p4, p5};
        for (PointHistory p : arr2) {
            pointHistoryDAO.insert(p);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢測試
        // 單筆查詢
        PointHistory byPrimaryKey = pointHistoryDAO.getByPrimaryKey(1);
        PointHistory byPrimaryKey1 = pointHistoryDAO.getByPrimaryKey(6);
        System.out.println(byPrimaryKey);
        System.out.println(byPrimaryKey1);

        System.out.println("=====================================================================================================================================");

        // 全部查詢
        List<PointHistory> list = pointHistoryDAO.getAll();
        for (PointHistory p : list) {
            System.out.println(p);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢某個會員的全部紀錄(依時間排序)
        List<PointHistory> list2 = pointHistoryDAO.getAllByMbrId(10);
        for (PointHistory p : list2) {
            System.out.println(p);
        }

    }
}
