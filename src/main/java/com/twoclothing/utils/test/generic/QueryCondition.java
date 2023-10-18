package com.twoclothing.utils.test.generic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryCondition {
    private List<Map<String,Object>> conditionList = new ArrayList<>();

    public QueryCondition() {
    }


    public List<Map<String, Object>> getConditionList() {
		return conditionList;
	}
    
    public void printConditionList() {
    	int i = 0;
    	for (Map<String, Object> conditionMap : conditionList) {
    		i++;
    	    for (Map.Entry<String, Object> entry : conditionMap.entrySet()) {
    	        String key = entry.getKey();
    	        Object value = entry.getValue();

    	     // 如果值是陣列，將陣列轉換為字串並列印
    	        if (value instanceof Object[]) {
    	            String arrayString = Arrays.toString((Object[]) value);
    	            System.out.println("Key: " + key + ", Value: " + arrayString);
    	        } else {
    	        	// 否則直接列印鍵值對
    	            System.out.println("Key: " + key + ", Value: " + value);
    	        }
    	    }
    	    System.out.println("=============以上是Map"+i+"=============");
    	}
    }
    
    public void toMap(String logicalOperator, String fieldName, String operator, Object... values) {
        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("fieldName", fieldName);
        conditionMap.put("operator", operator);
        conditionMap.put("logicalOperator", logicalOperator);

     // 根據不同的運算符處理值
        if ("between".equals(operator) && values.length == 2 && values[0] != null && values[1] != null) {
        	// 如果運算符是 "between"，且有兩個非空值，將它們放入陣列或列表中，然後放入 conditionMap
            Object[] betweenValues = {values[0], values[1]};
            conditionMap.put("value", betweenValues);
        } else if (!"between".equals(operator) && values.length == 1 && values[0] != null) {
        	// 如果運算符不是 "between"，且只有一個非空值，直接將該值放入 conditionMap
            conditionMap.put("value", values[0]);
        } else {
        	// 如果不符合以上條件，可以選擇拋出異常或進行其他處理
            throw new IllegalArgumentException("Invalid operator or values");
        }
        conditionList.add(conditionMap);
    }


    


}
