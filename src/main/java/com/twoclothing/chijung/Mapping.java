package com.twoclothing.chijung;

import java.util.HashMap;
import java.util.Map;

public class Mapping {
    public static Map<Integer, String>  couponDisTypeMap;

    static {
        // 優惠券使用條件狀態Map
    	couponDisTypeMap = new HashMap<>();
    	couponDisTypeMap.put(0, "折抵金額");
    	couponDisTypeMap.put(1, "折%數");
    }
}

