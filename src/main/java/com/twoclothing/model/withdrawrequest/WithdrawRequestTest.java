package com.twoclothing.model.withdrawrequest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class WithdrawRequestTest {
    public static void main(String[] args) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp now = Timestamp.valueOf(currentDateTime);
        Timestamp[] arr = new Timestamp[6];
        for (int i = 0; i < arr.length; i++) {
            Random random = new Random();
            arr[i] = Timestamp.valueOf(currentDateTime.minusSeconds(Math.abs(random.nextInt() % 10000000)));
        }

        WithdrawRequest w1 = new WithdrawRequest(null, 20, 500, "1234567890", arr[0], 1, 201, now, "已完成匯款");
        WithdrawRequest w2 = new WithdrawRequest(null, 20, 900, "9876543210", arr[1], 0, null, null, null);
        WithdrawRequest w3 = new WithdrawRequest(null, 30, 880, "3456789012", arr[2], 1, 205, now, "已完成匯款");
        WithdrawRequest w4 = new WithdrawRequest(null, 40, 800, "9876123450", arr[3], 0, null, null, null);
        WithdrawRequest w5 = new WithdrawRequest(null, 20, 600, "5678901234", arr[4], 2, 205, now, "查無此銀行帳戶");
        WithdrawRequest w6 = new WithdrawRequest(1, 60, 7000, "5678901234", arr[5], 2, 208, now, "查無此銀行帳戶");
        WithdrawRequest w7 = new WithdrawRequest(1, 60, 7000, "5678901234", arr[5], 2, null, null, null);

        // WithdrawRequestTest
        WithdrawRequestDAO withdrawRequestDAO = new WithdrawRequestJDBCDAO();

        // 插入測試
        WithdrawRequest[] arr2 = {w1, w2, w3, w4, w5};
        for (WithdrawRequest w : arr2) {
            withdrawRequestDAO.insert(w);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢測試
        // 單筆查詢
        WithdrawRequest byPrimaryKey = withdrawRequestDAO.getByPrimaryKey(2);
        WithdrawRequest byPrimaryKey2 = withdrawRequestDAO.getByPrimaryKey(6);
        System.out.println(byPrimaryKey);
        System.out.println(byPrimaryKey2);

        System.out.println("=====================================================================================================================================");

        // 查詢全部
        List<WithdrawRequest> list = withdrawRequestDAO.getAll();
        for (WithdrawRequest w : list) {
            System.out.println(w);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢某個員工審核過的申請
        List<WithdrawRequest> list2 = withdrawRequestDAO.getAllByEmpID(205);
        for (WithdrawRequest w : list2) {
            System.out.println(w);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢某個會員的全部申請
        List<WithdrawRequest> list3 = withdrawRequestDAO.getAllByMbrid(20);
        for (WithdrawRequest w : list3) {
            System.out.println(w);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢某個審核狀態的申請
        List<WithdrawRequest> list4 = withdrawRequestDAO.getAllByReqStatus(1);
        for (WithdrawRequest w : list4) {
            System.out.println(w);
        }

        System.out.println("=====================================================================================================================================");

        // 修改測試
        withdrawRequestDAO.update(w6);
        withdrawRequestDAO.update(w7);

    }
}
