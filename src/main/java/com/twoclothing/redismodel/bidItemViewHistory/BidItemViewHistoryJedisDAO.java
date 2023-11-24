package com.twoclothing.redismodel.bidItemViewHistory;

import com.twoclothing.utils.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BidItemViewHistoryJedisDAO implements BidItemViewHistoryDAO {

    private final JedisPool jedisPool = JedisPoolUtil.getJedisPool();

    private static final int REDIS_NUMBER = 12;

    private static final String MBR_PREFIX = "mbrId:";

    private static final String MBR_SUFFIX = ":bidItemView";

    private static final String BIDITEM_PREFIX = "bidItemId:";

    // 瀏覽紀錄存活7天(7 * 24 * 60 * 60秒)
    private final int TTL = 7 * 24 * 60 * 60;

    // 單個會員最多紀錄幾筆瀏覽紀錄
    private final int MAX_VIEW = 30;

    @Override
    public void insert(BidItemViewHistory history) {
        try (Jedis jedis = jedisPool.getResource()) {

            jedis.select(REDIS_NUMBER);

            // 儲存
            long timestamp = history.getTimestamp();
            String key = MBR_PREFIX + history.getMbrId() + MBR_SUFFIX;
            String value = BIDITEM_PREFIX + history.getBidItemId();
            jedis.zadd(key, (double) timestamp, value);
            jedis.expire(key, TTL);

            // 判斷大小,刪除多餘的value
            Long valueSize = jedis.zcard(key);
            if (valueSize > MAX_VIEW) {
                jedis.zremrangeByRank(key, 0, valueSize - MAX_VIEW - 1);
            }
        }
    }

    @Override
    public List<Integer> getAllBidItemIdByMbrId(Integer mbrId) {
        List<Integer> bidItemIdList = new ArrayList<>();
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(REDIS_NUMBER);
            Set<String> bidItemIdSet = jedis.zrevrange(MBR_PREFIX + mbrId + MBR_SUFFIX, 0, -1);
            if (bidItemIdSet != null && !bidItemIdSet.isEmpty()) {
                for (String id : bidItemIdSet) {
                    bidItemIdList.add(Integer.valueOf(id.replace(BIDITEM_PREFIX, "")));
                }
            }
            return bidItemIdList;
        }
    }
}
