package com.twoclothing.utils.test;

import com.google.gson.Gson;
import com.twoclothing.redismodel.notice.Notice;
import redis.clients.jedis.Jedis;

public class NoticeTest {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.select(15);
        // 如何儲存通知?
        // 1.先儲存一個通知ID的資料,以便達到自增主鍵
        //  會寫在監聽器,當服務器啟動時,檢查是否有值,沒有就給初值0;
        if (!jedis.exists("noticeId")) {
            jedis.set("noticeId", "0");
        }
        // 2.取得自增通知ID
        //  INCR在Redis是原子操作,有執行序安全
        String noticeId = String.valueOf(jedis.incr("noticeId"));

        // 3.儲存通知時,先儲存會員ID(key)與通知ID(value)(List儲存)
        String mbrId = "1";
        // key的命名方式
        jedis.rpush("mbrId:" + mbrId, "noticeId:" + noticeId);

        // 4.再利用取得的通知ID(key)與內容(value)(String儲存JSON格式)
        Notice notice = new Notice();
        // 可以直接利用建構子
        notice.setType("系統通知");
        notice.setHead("測試測試");
        notice.setContent("哩西咧工三小");
        notice.setLink("/ooo/ooo/ooo");
        notice.setImageLink("/xxx/xxx/xxx");
        String json = gson.toJson(notice);
        jedis.set("noticeId:" + noticeId ,json);

        // 1~4可以寫成一個方法包裝起來,參數為會員ID跟noticeDTO

    }
}
