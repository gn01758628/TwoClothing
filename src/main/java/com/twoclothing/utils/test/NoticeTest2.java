package com.twoclothing.utils.test;

import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

import java.util.List;

public class NoticeTest2 {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.select(15);

        // 5.取得會員的所有通知
        String mbrId = "1";
        // 取得所有通知ID
        List<String> noticeIds = jedis.lrange("mbrId:" + mbrId, 0, -1);
        // 取得所有通知
        for (String s : noticeIds) {
            System.out.println("ID = " + s + "：" + jedis.get(s));
        }
        // 可以根據特定的通知ID進行操作
        // 設置過期時間,改變已讀狀態..

    }
}
