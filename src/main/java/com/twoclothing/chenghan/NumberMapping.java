package com.twoclothing.chenghan;

import java.util.HashMap;
import java.util.Map;

public class NumberMapping {
    public static Map<Integer, String>  bidStatusMap;
    public static Map<Integer, String>  gradeMap;
    public static Map<Integer, String>  sizeMap;

    static {
        // 競標商品狀態Map
        bidStatusMap = new HashMap<>();
        bidStatusMap.put(0, "待審核");
        bidStatusMap.put(1, "已過審");
        bidStatusMap.put(2, "結標");
        bidStatusMap.put(3, "流標");
        bidStatusMap.put(4, "上架中");
        bidStatusMap.put(5, "刪除");
        bidStatusMap.put(6, "被下架");
        // 競標商品新舊程度Map
        gradeMap = new HashMap<>();
        gradeMap.put(0, "全新");
        gradeMap.put(1, "9成5新");
        gradeMap.put(2, "9成新");
        gradeMap.put(3, "8成新");
        gradeMap.put(4, "5成新");
        // 競標商品尺寸Map
        sizeMap = new HashMap<>();
        sizeMap.put(0, "XS(含以下)");
        sizeMap.put(1, "S");
        sizeMap.put(2, "M");
        sizeMap.put(3, "L");
        sizeMap.put(4, "XL");
        sizeMap.put(5, "2XL");
        sizeMap.put(6, "3XL");
        sizeMap.put(7, "4XL(含以下)");
    }
}
