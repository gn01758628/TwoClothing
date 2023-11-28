package com.twoclothing.chijung;

import java.util.HashMap;
import java.util.Map;

public class Mapping {
    public static Map<Integer, String>  couponDisTypeMap;
    public static Map<Integer, String>  AllotedCouponStatusMap;
    public static Map<Integer, String>  OrderStatusMap;
    static {
        // 優惠券使用條件狀態Map
    	couponDisTypeMap = new HashMap<>();
    	couponDisTypeMap.put(0, "折抵金額");
    	couponDisTypeMap.put(1, "折%數");
    	// 優惠券發放狀態
    	AllotedCouponStatusMap = new HashMap<>();
    	AllotedCouponStatusMap.put(-1, "使用期限已到");
    	AllotedCouponStatusMap.put(0, "尚未發放");
    	AllotedCouponStatusMap.put(1, "發放中");
    	AllotedCouponStatusMap.put(2, "停止發放");
    	AllotedCouponStatusMap.put(3, "發放完了");
    	
    	OrderStatusMap = new HashMap<>();
    	OrderStatusMap.put(0,"待付款");
    	OrderStatusMap.put(1,"未出貨");
    	OrderStatusMap.put(2,"待收貨");
    	OrderStatusMap.put(3,"訂單完成");
    	OrderStatusMap.put(4,"訂單不成立");
    	
    }
}

