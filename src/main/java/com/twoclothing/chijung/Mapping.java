package com.twoclothing.chijung;

import java.util.HashMap;
import java.util.Map;

public class Mapping {
    public static Map<Integer, String>  couponDisTypeMap;
    public static Map<Integer, String>  AllotedCouponStatusMap;
    static {
        // 優惠券使用條件狀態Map
    	couponDisTypeMap = new HashMap<>();
    	couponDisTypeMap.put(0, "折抵金額");
    	couponDisTypeMap.put(1, "折%數");
    	// 優惠券發放狀態
    	AllotedCouponStatusMap = new HashMap<>();
    	AllotedCouponStatusMap.put(-1, "使用期限到");
    	AllotedCouponStatusMap.put(0, "發放中");
    	AllotedCouponStatusMap.put(1, "停止發放");
    	AllotedCouponStatusMap.put(2, "發放完了");
    }
}

