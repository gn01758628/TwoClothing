package com.twoclothing.redismodel.allotedCoupon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.twoclothing.utils.JedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class AllotedCouponRedisDAO implements AllotedCouponDAO{
	
	private final JedisPool jedisPool = JedisPoolUtil.getJedisPool();

    private static final int REDIS_NUMBER = 9;
    
    private static final String COUPON_PREFIX = "tagId:";
//
//    private static final String MBR_PREFIX = "mbrId:";
//
//    private static final String MBR_SUFFIX = ":bidItemView";
//
//    private static final String BIDITEM_PREFIX = "bidItemId:";


	@Override
	public void allot(AllotedCoupon allotedCoupon) {
		
		try (Jedis jedis = jedisPool.getResource()) {
	        jedis.select(REDIS_NUMBER);

	        // 根據 KEY 搜尋列表長度
	        String key = String.valueOf(COUPON_PREFIX+allotedCoupon.getCpnId());
	        Long listLength = jedis.llen(key);

	        // 設定 allotedCoupon 的 index 為列表長度 + 1
	        allotedCoupon.setIndex(listLength.intValue() + 1);

	        // 使用 GSON 將物件轉換成 JSON 字串
	        Gson gson = new Gson();
	        String jsonCoupon = gson.toJson(allotedCoupon);

	        // 在列表的最前面新增 JSON 字串
	        jedis.lpush(key, jsonCoupon);
	    }
		
	}

	@Override
	public List<AllotedCoupon> getAll() {
		List<AllotedCoupon> allotedCouponList = new ArrayList<>();

		try (Jedis jedis = jedisPool.getResource()) {
		    jedis.select(REDIS_NUMBER);
		    Set<String> keys = jedis.keys(COUPON_PREFIX);

		    List<AllotedCoupon> allotedCouponJsonList = (List<AllotedCoupon>) jedis.eval(GETALL_LUA_SCRIPT, 1, COUPON_PREFIX);

		    // 处理返回的 allotedCouponList
		    Gson gson = new Gson();
		    for (String jsonCoupon : allotedCouponJsonList) {
		        AllotedCoupon allotedCoupon = gson.fromJson(jsonCoupon, AllotedCoupon.class);
		        allotedCouponList.add(allotedCoupon);
		    }
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
//		    // 遍歷所有找到的鍵
//		    for (String key : keys) {
//		        // 使用 lrange 檢索列表的所有元素
//		        List<String> values = jedis.lrange(key, 0, -1);
//
//		        // 遍歷 values 中的每個 JSON 字串，並轉換為 AllotedCoupon 物件
//		        for (String jsonCoupon : values) {
//		            Gson gson = new Gson();
//		            AllotedCoupon allotedCoupon = gson.fromJson(jsonCoupon, AllotedCoupon.class);
//		            allotedCouponList.add(allotedCoupon);
//		        }
//		    }
		}

		// 在這裡可以使用 allotedCouponList 做進一步的處理，例如印出或者其他操作
		for (AllotedCoupon coupon : allotedCouponList) {
		    System.out.println(coupon);
		}


        return allotedCouponList;
    }

	@Override
	public void receiveCoupon(AllotedCoupon allotedCoupon) {
		 try (Jedis jedis = jedisPool.getResource()) {

	            jedis.select(REDIS_NUMBER);

	            // 設定KEYS和ARGV
	            String key = String.valueOf(allotedCoupon.getCpnId());
	            String index = String.valueOf(allotedCoupon.getIndex());
	            
	            // 執行Lua腳本
	            Long result = (Long) jedis.eval(RECEIVE_LUA_SCRIPT, 1, key, index);

	            // 將Long轉換為int
	            int intValue = result.intValue();

	            // 輸出結果
	            System.out.println("Remaining Quantity: " + intValue);
	    }
	}

	@Override
	public void delete(AllotedCoupon allotedCoupon) {
		// TODO Auto-generated method stub
		
	}
	
	private static final String  GETALL_LUA_SCRIPT = 
			"local keys = redis.call('KEYS','*') " +
	        "local allotedCouponList = {} " +
	        "local currentTime = redis.call('TIME') " +
	        "local currentTimestamp = tonumber(currentTime[1]) " +
	        "for _, key in ipairs(keys) do " +
	        "   local values = redis.call('LRANGE', key, 0, -1) " +
	        "   for i, jsonCoupon in ipairs(values) do " +
	        "       local allotedCoupon = cjson.decode(jsonCoupon) " +        
	        "       if tonumber(allotedCoupon.expireDate) and currentTimestamp > tonumber(allotedCoupon.expireDate) then " +
	        "           allotedCoupon.status = -1 " +
	        "           redis.call('LSET', key, i - 1, cjson.encode(allotedCoupon)) " +
	        "       end " +
	        "       table.insert(allotedCouponList, allotedCoupon) " +
	        "   end " +
	        "end " +
	        "return allotedCouponList";
	
	
	
	
	//如果 發放完畢 後台終止發放 或是 優惠券使用期限結束 回傳-1 其餘回傳剩餘數量
    private static final String  RECEIVE_LUA_SCRIPT = 
    		"local key = KEYS[1]\n" +
            "local index = ARGV[1]\n" +
            "for i, jsonData in ipairs(redis.call('LRANGE', key, 0, -1)) do\n" +
            "    local item = cjson.decode(jsonData)\n" +
            "    if item.index == index then\n" +
            "        if item.expireDate and os.time() > item.expireDate then\n" +
            "            item.status = -1\n" +
            "            redis.call('LSET', key, i - 1, cjson.encode(item))\n" +
            "            return -1\n" +
            "        end\n" +
            "        if item.status ~= 0 then\n" +
            "            return -1\n" +
            "        end\n" +
            "        if item.remainingQuantity > 0 then\n" +
            "            item.remainingQuantity = item.remainingQuantity - 1\n" +
            "            redis.call('LSET', key, i - 1, cjson.encode(item))\n" +
            "            return item.remainingQuantity\n" +
            "        end\n" +
            "    end\n" +
            "end\n" +
            "return -2";
	
    
    
    

}
