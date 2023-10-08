package com.twoclothing.web.follow;

import java.util.List;

public class FollowTest {
    public static void main(String[] args) {
        Follow f1 = new Follow(1, 2);
        Follow f2 = new Follow(1, 3);
        Follow f3 = new Follow(2, 1);
        Follow f4 = new Follow(3, 2);
        Follow f5 = new Follow(3, 1);
        Follow f6 = new Follow(3, 1);

        // FollowTest
        FollowDAO followDAO = new FollowJDBCDAO();

        // 插入測試
        Follow[] arr = {f1, f2, f3, f4, f5, f6};
        for (Follow f : arr) {
            followDAO.insert(f);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢測試
        // 單筆查詢
        Follow byCompositeKey = followDAO.getByCompositeKey(1, 2);
        Follow byCompositeKey2 = followDAO.getByCompositeKey(1, 4);
        System.out.println(byCompositeKey);
        System.out.println(byCompositeKey2);

        System.out.println("=====================================================================================================================================");

        // 全部查詢
        List<Follow> list = followDAO.getAll();
        for (Follow f : list) {
            System.out.println(f);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢某個會員的追蹤清單(依被追蹤的會員編號排序)
        List<Follow> list2 = followDAO.getAllByMbrId(3);
        for (Follow f : list2) {
            System.out.println(f);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢某個會員被多少會員黑名單的清單(依被黑他的會員編號排序)
        List<Follow> list3 = followDAO.getAllByFollowId(1);
        for (Follow f : list3) {
            System.out.println(f);
        }

        System.out.println("=====================================================================================================================================");

        //刪除測試
        followDAO.delete(1,2);
        followDAO.delete(1,6);

    }
}
