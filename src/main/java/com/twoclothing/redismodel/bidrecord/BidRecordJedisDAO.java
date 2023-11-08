package com.twoclothing.redismodel.bidrecord;

import com.google.gson.Gson;
import com.twoclothing.utils.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class BidRecordJedisDAO implements BidRecordDAO {

    private final JedisPool jedisPool = JedisPoolUtil.getJedisPool();

    private final Gson gson = new Gson();

    private static final int REDIS_NUMBER = 14;

    private static final String BIDITEM_PREFIX = "bidItemId:";

    private static final String MBR_PREFIX = "mbrId:";

    private static final String MBR_SUFFIX = ":bidding";

    @Override
    public void insert(BidRecord bidRecord, Integer bidItemId, LocalDateTime endTime) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(REDIS_NUMBER);
            String bidRecordJson = gson.toJson(bidRecord);
            String key = BIDITEM_PREFIX + bidItemId;
            if (!jedis.exists(key)) {
                jedis.lpush(key, bidRecordJson);
                LocalDateTime now = LocalDateTime.now();
                // 出價紀錄保留到競標結束D+7
                Duration duration = Duration.between(now, endTime.plusDays(7));
                jedis.expire(key, (int) duration.getSeconds());
            } else {
                jedis.lpush(key, bidRecordJson);
            }
            jedis.sadd(MBR_PREFIX + bidRecord.getMbrId() + MBR_SUFFIX, String.valueOf(bidItemId));
        }
    }

    @Override
    public List<BidRecord> getAll(Integer bidItemId) {
        try (Jedis jedis = jedisPool.getResource()) {

            jedis.select(REDIS_NUMBER);
            List<BidRecord> bidRecordList = new ArrayList<>();
            List<String> jsonList = jedis.lrange(BIDITEM_PREFIX + bidItemId, 0, -1);
            for (String json : jsonList) {
                bidRecordList.add(gson.fromJson(json, BidRecord.class));
            }
            Collections.sort(bidRecordList);
            return bidRecordList;
        }
    }

    @Override
    public BidRecord getIndexRecordByKey(Integer bidItemId, int index) {
        List<BidRecord> recordList = getAll(bidItemId);
        if (index >= 0 && index < recordList.size()) {
            return recordList.get(index);
        }
        return null;
    }

    @Override
    public Set<Integer> getAllMbrIdByKey(Integer bidItemId) {
        Set<Integer> mbrIdSet = new HashSet<>();
        List<BidRecord> recordList = getAll(bidItemId);
        for (BidRecord record : recordList) {
            mbrIdSet.add(record.getMbrId());
        }
        return mbrIdSet;
    }
}
