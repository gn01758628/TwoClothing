package com.twoclothing.redismodel.allotedCoupon;

import java.util.List;

import com.google.gson.Gson;
import com.twoclothing.utils.JedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class AllotedCouponRedisDAO implements AllotedCouponDAO{
	
	private final JedisPool jedisPool = JedisPoolUtil.getJedisPool();

    private static final int REDIS_NUMBER = 9;
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
	        String key = String.valueOf(allotedCoupon.getCpnId());
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void receiveCoupon(AllotedCoupon allotedCoupon) {
		 try (Jedis jedis = jedisPool.getResource()) {

	            jedis.select(REDIS_NUMBER);

	         // 設定KEYS和ARGV
	            String key = String.valueOf(allotedCoupon.getCpnId());
	            String index = String.valueOf(allotedCoupon.getIndex());
	            
	            // 執行Lua腳本
	            int result = (int)jedis.eval(RECEIVE_LUA_SCRIPT, 1, key,index);

	            // 輸出結果
	            System.out.println("Remaining Quantity: " + result);
	    }
	}

	@Override
	public void delete(AllotedCoupon allotedCoupon) {
		// TODO Auto-generated method stub
		
	}
	
	
	// 静态变量用于保存 Lua 脚本
    private static final String  RECEIVE_LUA_SCRIPT = "local key = KEYS[1]\n" +
            "local index = tonumber(ARGV[1])\n" +
            "local selectedData = redis.call('LINDEX', key, index)\n" +
            "local data = cjson.decode(selectedData)\n" +
            "if data.status == 1 then\n" +
            "    return -1\n" +
            "end\n" +
            "local remainingQuantity = tonumber(data.remainingQuantity)\n" +
            "if remainingQuantity > 0 then\n" +
            "    remainingQuantity = remainingQuantity - 1\n" +
            "    data.remainingQuantity = remainingQuantity\n" +
            "    local updatedData = cjson.encode(data)\n" +
            "    redis.call('LSET', key, index, updatedData)\n" +
            "    return remainingQuantity\n" +
            "else\n" +
            "    return 0\n" +
            "end";
	

}
