package com.twoclothing.model.chatroomlog;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ChatRoomLogTest {
    public static void main(String[] args) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Timestamp> list = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            Random random = new Random();
            list.add(Timestamp.valueOf(currentDateTime.minusSeconds(Math.abs(random.nextInt() % 100000))));
        }
        Collections.sort(list);

        ChatRoomLog c1 = new ChatRoomLog(1, 105, 108, null, "哈囉你好嗎?", list.get(0));
        ChatRoomLog c2 = new ChatRoomLog(2, 108, 105, null, "衷心感謝,珍重再見", list.get(1));
        ChatRoomLog c3 = new ChatRoomLog(3, 105, 108, null, "期待再相逢", list.get(2));
        ChatRoomLog c4 = new ChatRoomLog(4, null, 110, 201, "我的商品怎麼還沒收到?", list.get(3));
        ChatRoomLog c5 = new ChatRoomLog(5, 110, null, 201, "商品沒收到,你問我幹嘛,問賣家阿", list.get(4));
        ChatRoomLog c6 = new ChatRoomLog(6, null, 110, 209, "你什麼口氣,我要客訴", list.get(5));
        ChatRoomLog c7 = new ChatRoomLog(7, 110, null, 209, "剛剛回復您的員工已經被開除了", list.get(6));
        ChatRoomLog c8 = new ChatRoomLog(8, 110, null, 209, "這樣您黑皮了嗎?", list.get(7));
        ChatRoomLog c9 = new ChatRoomLog(9, 108, 105, null, "哩洗勒工三小", list.get(8));

        // ChatRoomLogTest
        ChatRoomLogDAO chatRoomLogDAO = new ChatRoomLogJDBCDAO();

        // 插入測試
        ChatRoomLog[] arr = {c1, c2, c3, c4, c5, c6, c7, c8, c9};
        for (ChatRoomLog c : arr) {
            chatRoomLogDAO.insert(c);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢測試
        // 單筆查詢
        ChatRoomLog byPrimaryKey = chatRoomLogDAO.getByPrimaryKey(1);
        ChatRoomLog byPrimaryKey2 = chatRoomLogDAO.getByPrimaryKey(10);
        System.out.println(byPrimaryKey);
        System.out.println(byPrimaryKey2);

        System.out.println("=====================================================================================================================================");

        // 全部查詢
        List<ChatRoomLog> list1 = chatRoomLogDAO.getAll();
        for (ChatRoomLog c : list1) {
            System.out.println(c);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢兩個會員間的對話
        List<ChatRoomLog> list2 = chatRoomLogDAO.getLogFromMembers(105, 108);
        for (ChatRoomLog c : list2) {
            System.out.println(c);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢會員的客服對話
        List<ChatRoomLog> list3 = chatRoomLogDAO.getLogFromService(110);
        for (ChatRoomLog c : list3) {
            System.out.println(c);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢員工的回覆紀錄
        List<ChatRoomLog> list4 = chatRoomLogDAO.getLogByEmpId(209);
        for (ChatRoomLog c : list4) {
            System.out.println(c);
        }


    }
}
