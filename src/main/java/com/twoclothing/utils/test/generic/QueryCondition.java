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


    public List<Map<String, Object>> getList() {
		return conditionList;
	}
    
    public void printList() {
    	int i = 0;
    	for (Map<String, Object> conditionMap : conditionList) {
    		i++;
    	    for (Map.Entry<String, Object> entry : conditionMap.entrySet()) {
    	        String key = entry.getKey();
    	        Object value = entry.getValue();

    	        // 如果值是数组，将数组转换为字符串并打印
    	        if (value instanceof Object[]) {
    	            String arrayString = Arrays.toString((Object[]) value);
    	            System.out.println("Key: " + key + ", Value: " + arrayString);
    	        } else {
    	            // 否则直接打印键值对
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

        // 根据不同的操作符处理值
        if ("between".equals(operator) && values.length == 2 && values[0] != null && values[1] != null) {
            // 如果操作符是 "between"，且有两个非空值，将它们放入数组或列表中，然后放入 conditionMap
            Object[] betweenValues = {values[0], values[1]};
            conditionMap.put("value", betweenValues);
        } else if (!"between".equals(operator) && values.length == 1 && values[0] != null) {
            // 如果操作符不是 "between"，且只有一个非空值，直接将该值放入 conditionMap
            conditionMap.put("value", values[0]);
        } else {
            // 如果不符合以上条件，可以选择抛出异常或进行其他处理
            throw new IllegalArgumentException("Invalid operator or values");
        }
        conditionList.add(conditionMap);
    }


    


}
