package com.twoclothing.utils.test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

// 如何操作 java.sql.Timestamp
public class TimestampTest {
    public static void main(String[] args) {

        // 基本上都是利用java.time.LocalDateTime的方法,再轉回java.sql.Timestamp
        // 不把資料型態直接設定為LocalDateTime,而是Timestamp,因為Timestamp可以直接在MySQL存取
        // ResultSet 的方法 getTimestamp(),setTimestamp()
        // MySQL資料型態是Date,DateTime,Timestamp,都可以使用這兩個get,set

        // 創建LocalDateTime物件
        // 參數創建
        LocalDateTime localDateTime = LocalDateTime.of(2023, 10, 7, 15, 30, 45);
        System.out.println("參數創建時間：" + localDateTime);
        // 獲取當前時間
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("獲得當前時間：" + currentDateTime);

        // 個別獲取年月日時分秒
        System.out.println("年：" + currentDateTime.getYear());
        System.out.println("月：" + currentDateTime.getMonthValue());
        System.out.println("日：" + currentDateTime.getDayOfMonth());
        System.out.println("時：" + currentDateTime.getHour());
        System.out.println("分：" + currentDateTime.getMinute());
        System.out.println("秒：" + currentDateTime.getSecond());

        // 操作時間
        // 增加：plus + (Years/Months/Days/Hours/Minutes/Seconds)
        // 減少：minus + (Years/Months/Days/Hours/Minutes/Seconds)
        System.out.println(localDateTime + " 加25天等於 " + localDateTime.plusDays(25));
        System.out.println(localDateTime + " 減30小時等於 " + localDateTime.minusHours(30));

        // LocalDateTime 轉 Timestamp
        Timestamp timestamp = Timestamp.valueOf(currentDateTime);
        System.out.println("LocalDateTime 轉 Timestamp：" + timestamp);
        // Timestamp 轉 LocalDateTime
        LocalDateTime localDateTime2 = timestamp.toLocalDateTime();
        System.out.println("Timestamp 轉 LocalDateTime：" + localDateTime2);
    }
}
