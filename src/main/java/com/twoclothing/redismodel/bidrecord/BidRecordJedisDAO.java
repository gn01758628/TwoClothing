package com.twoclothing.redismodel.bidrecord;

import com.google.gson.Gson;
import com.twoclothing.utils.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BidRecordJedisDAO implements BidRecordDAO {

    private final JedisPool jedisPool = JedisPoolUtil.getJedisPool();

    private final Gson gson = new Gson();

    private static final String BIDITEM_PREFIX = "bidItemId:";

    private static final String MBR_PREFIX = "mbrId:";

    @Override
    public void insert(BidRecord bidRecord, Integer bidItemId, LocalDateTime endTime) {
        Jedis jedis = jedisPool.getResource();
        jedis.select(14);
        String bidRecordJson = gson.toJson(bidRecord);
        if (!jedis.exists(BIDITEM_PREFIX + bidItemId)) {
            jedis.lpush(BIDITEM_PREFIX + bidItemId, bidRecordJson);
            LocalDateTime now = LocalDateTime.now();
            // 出價紀錄保留到競標結束D+7
            Duration duration = Duration.between(now, endTime.plusDays(7));
            jedis.expire(BIDITEM_PREFIX + bidItemId, (int) duration.getSeconds());
        } else {
            jedis.lpush(BIDITEM_PREFIX + bidItemId, bidRecordJson);
        }
        jedis.sadd(MBR_PREFIX + bidRecord.getMbrId(), String.valueOf(bidItemId));
        jedis.close();
    }

    @Override
    public List<BidRecord> getAll(Integer bidItemId) {
        Jedis jedis = jedisPool.getResource();
        jedis.select(14);
        List<BidRecord> bidRecordList = new ArrayList<>();
        List<String> jsonList = jedis.lrange(BIDITEM_PREFIX + bidItemId, 0, -1);
        for (String json : jsonList) {
            bidRecordList.add(gson.fromJson(json, BidRecord.class));
        }
        Collections.sort(bidRecordList);
        jedis.close();
        return bidRecordList;
    }
}
