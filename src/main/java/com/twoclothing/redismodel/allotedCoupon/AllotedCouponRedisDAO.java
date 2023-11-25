package com.twoclothing.redismodel.allotedCoupon;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.twoclothing.utils.JedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class AllotedCouponRedisDAO implements AllotedCouponDAO{
	
	private final JedisPool jedisPool = JedisPoolUtil.getJedisPool();

    private static final int REDIS_NUMBER = 9;
   
    private static final String COUPON_PREFIX = "tagId:";

	@Override
	public void allot(AllotedCoupon allotedCoupon) {
		
		try (Jedis jedis = jedisPool.getResource()) {
			
			jedis.select(REDIS_NUMBER);
			String key = String.valueOf(COUPON_PREFIX+allotedCoupon.getCpnId());
			Gson gson = new Gson();
	        String jsonCoupon = gson.toJson(allotedCoupon);

            String result = (String) jedis.eval(ALLOT_LUA_SCRIPT, 1, key, jsonCoupon);

//            System.out.println("Lua script result: " + result);
        }
		
	}

	@Override
	public List<AllotedCoupon> getAll() {
		List<AllotedCoupon> allotedCouponList = new ArrayList<>();

		try (Jedis jedis = jedisPool.getResource()) {
		    jedis.select(REDIS_NUMBER);
		    Set<String> keys = jedis.keys(COUPON_PREFIX);
		    
		    
		    String jsonString = (String)jedis.eval(GETALL_LUA_SCRIPT, 1, COUPON_PREFIX);
		    
		    if (!isEmptyJsonObject(jsonString)) {
		    	allotedCouponList = new Gson().fromJson(jsonString, new TypeToken<List<AllotedCoupon>>() {}.getType());	     	        	
	        }
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
	public int stopIssuingCoupon(AllotedCoupon allotedCoupon) {
		int status;
		try (Jedis jedis = jedisPool.getResource()) {
		    jedis.select(REDIS_NUMBER);
		    String key = String.valueOf(COUPON_PREFIX+allotedCoupon.getCpnId());
		    String index = String.valueOf(allotedCoupon.getIndex());
		    
	        status = ((Long)jedis.eval(STOP_ISSUING_COUPON_LUA_SCRIPT, 1, key,index)).intValue();
		}
		return status;
		
	}
	
	private static final String  ALLOT_LUA_SCRIPT =
			"redis.replicate_commands()\n" +
            "local key = KEYS[1]\n" +
            "local existingJson = redis.call('LINDEX', key, 0)\n" +
            "if existingJson then\n" +
            "    local existingCoupon = cjson.decode(existingJson)\n" +
            "    if existingCoupon.status == 0 or existingCoupon.status == 1 then\n" +
            "        existingCoupon.status = 2\n" +
            "        redis.call('LSET', key, 0, cjson.encode(existingCoupon))\n" +
            "    end\n" +
            "    local existingIndex = existingCoupon.index\n" +
            "    local newIndex = existingIndex + 1\n" +
            "    local newCoupon = cjson.decode(ARGV[1])\n" +
            "    newCoupon.index = newIndex\n" +
            "    redis.call('LPUSH', key, cjson.encode(newCoupon))\n" +
            "else\n" +
            "    local newCoupon = cjson.decode(ARGV[1])\n" +
            "    newCoupon.index = 1\n" +
            "    redis.call('LPUSH', key, cjson.encode(newCoupon))\n" +
            "end\n" +
            "return 'OK'";
	
	private static final String  GETALL_LUA_SCRIPT = 
			"redis.replicate_commands()" +
			"local keys = redis.call('KEYS','*') " +
            "local allotedCouponList = {} " +
            "local currentTime = redis.call('TIME') " +
            "local currentTimestamp = tonumber(currentTime[1]) " +	
            "for _, key in ipairs(keys) do " +
            "   local values = redis.call('LRANGE', key, 0, -1) " +
            "   for i, jsonCoupon in ipairs(values) do " +
            "       local allotedCoupon = cjson.decode(jsonCoupon) " +
            "       local luaAllotDate = allotedCoupon.allotDate / 1000 " +
            "       if allotedCoupon.status == 0 and luaAllotDate and currentTimestamp > luaAllotDate then " +
            "           allotedCoupon.status = 1 " +
            "           redis.call('LSET', key, i - 1, cjson.encode(allotedCoupon)) " +
            "       end " +
            "       if allotedCoupon.expireDate ~= nil then\r\n" +
            "       	local luaExpireDate = allotedCoupon.expireDate / 1000 " +
            "       	if allotedCoupon.status == 1 and luaExpireDate and currentTimestamp > luaExpireDate then " +
            "           	allotedCoupon.status = -1 " +
            "           	redis.call('LSET', key, i - 1, cjson.encode(allotedCoupon)) " +
            "       	end " +
            "       end " +
            "       table.insert(allotedCouponList, allotedCoupon) " +
            "   end " +
            "end " +
            "return cjson.encode(allotedCouponList)";
	
	
	
	
	//如果 發放完畢 後台終止發放 或是 優惠券使用期限結束 回傳-1 其餘回傳剩餘數量
    private static final String  RECEIVE_LUA_SCRIPT = 
    		"redis.replicate_commands()" +
    		"local key = KEYS[1]\n" +
            "local index = ARGV[1]\n" +
            "local currentTime = redis.call('TIME') " +
            "local currentTimestamp = tonumber(currentTime[1]) " +	
            "for i, jsonData in ipairs(redis.call('LRANGE', key, 0, -1)) do\n" +
            "    local item = cjson.decode(jsonData)\n" +
            "    if item.index == index then\n" +
			"        if item.expireDate ~= nil then\r\n" +
			"       	 local luaExpireDate = item.expireDate / 1000 " +
            "        	 if item.status == 1 and luaExpireDate and currentTimestamp > luaExpireDate then\n" +
            "            	 item.status = -1\n" +
            "            	 redis.call('LSET', key, i - 1, cjson.encode(item))\n" +
            "            	 return -1\n" +
            "        	 end\n" +
            "        end\n" +
            "        if item.status ~= 1 then\n" +
            "            return item.status\n" +
            "        end\n" +
            "        if item.remainingQuantity > 0 then\n" +
            "            item.remainingQuantity = item.remainingQuantity - 1\n" +
            "            if item.remainingQuantity == 0 then\n" +
            "                item.status = 3" +       
            "        	 end\n" +
            "            redis.call('LSET', key, i - 1, cjson.encode(item))\n" +
            "            return item.remainingQuantity\n" +
            "        end\n" +
            "    end\n" +
            "end\n" +
            "return -2";
	
    private static final String STOP_ISSUING_COUPON_LUA_SCRIPT = 
    		"redis.replicate_commands()\n" +
            "local key = KEYS[1]\n" +
            "local index = tonumber(ARGV[1])\n" +
            "local currentTime = redis.call('TIME') " +
            "local currentTimestamp = tonumber(currentTime[1]) " +	
            "for i, jsonData in ipairs(redis.call('LRANGE', key, 0, -1)) do\n" +
            "    local item = cjson.decode(jsonData)\n" +
            "    if item.index == index then\n" +
            "        if item.status == 0 or item.status == 1 then\n" +
            "       	if item.expireDate ~= nil then\r\n" +
            "       		local luaExpireDate = item.expireDate / 1000 " +
            "            	if luaExpireDate and currentTimestamp > luaExpireDate then\n" +
            "                	item.status = -1\n" +
            "                	redis.call('LSET', key, i - 1, cjson.encode(item))\n" +
            "                	return -1\n" +
            "           	else\n" +
            "               	item.status = 2\n" +
            "               	redis.call('LSET', key, i - 1, cjson.encode(item))\n" +
            "               	return 2\n" +
            "           	end\n" +
            "           else\n" +
            "               item.status = 2\n" +
            "               redis.call('LSET', key, i - 1, cjson.encode(item))\n" +
            "               return 2\n" +
            "        	end\n" +
            "        else\n" +
            "            return item.status\n" +
            "        end\n" +
            "    end\n" +
            "end\n" +
            "\n" +
            "return nil";
    
    // 檢查 JSON 字串是否為空物件
    private static boolean isEmptyJsonObject(String jsonString) {
        try {
            JsonObject jsonObject = new Gson().fromJson(jsonString, JsonObject.class);
            return jsonObject.entrySet().isEmpty();
        } catch (JsonSyntaxException e) {
            // 處理 JSON 解析錯誤
            return false;
        }
    }
}
