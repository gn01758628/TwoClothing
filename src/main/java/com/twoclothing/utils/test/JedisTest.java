package com.twoclothing.utils.test;

import redis.clients.jedis.Jedis;

public class JedisTest {
    public static void main(String[] args) {
        // 建立連線,須先取得Jedis物件
        Jedis jedis = new Jedis("localhost",6379);
        // 利用Jedis物件來操作Redis資料庫
        System.out.println(jedis.ping());
        // 從0~15選一個空間來使用
        jedis.select(15);
        // Redis使用Key-Value的型式來儲存資料
        jedis.set("test","HelloRedis");
        System.out.println(jedis.get("test"));
        // 檢查Key是否存在
        System.out.println(jedis.exists("test"));
        // 刪除Key
        jedis.del("test");
        System.out.println(jedis.exists("test"));
        // 使用完一樣要關閉資源
        jedis.close();
    }
}
