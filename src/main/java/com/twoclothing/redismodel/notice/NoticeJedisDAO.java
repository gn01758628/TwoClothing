package com.twoclothing.redismodel.notice;

import com.google.gson.Gson;
import com.twoclothing.utils.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

public class NoticeJedisDAO implements NoticeDAO {

    private final JedisPool jedisPool = JedisPoolUtil.getJedisPool();

    private final Gson gson = new Gson();

    private static final int REDIS_NUMBER = 15;

    private static final String NOTICE_PREFIX = "notice:";

    private static final String MBR_PREFIX = "mbr:";

    private static final String MBR_SUFFIX = ":notice";

    // 通知存活30天(2592000秒)
    private final int TTL = 2_592_000;


    @Override
    public void insert(Notice notice, Integer mbrId) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(REDIS_NUMBER);
            String value = NOTICE_PREFIX + jedis.incr("noticeId");
            jedis.rpush(MBR_PREFIX + mbrId + MBR_SUFFIX, value);
            String noticeJson = gson.toJson(notice);
            jedis.set(value, noticeJson);
            jedis.expire(value, TTL);
        }
    }

    @Override
    public List<Notice> getAllByMbrId(Integer mbrId) {
        List<Notice> noticeList = new ArrayList<>();
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(REDIS_NUMBER);
            List<String> noticeIds = jedis.lrange(MBR_PREFIX + mbrId + MBR_SUFFIX, 0, -1);
            for (String id : noticeIds) {
                String noticeId = jedis.get(id);
                if (noticeId != null) {
                    Notice notice = gson.fromJson(noticeId, Notice.class);
                    noticeList.add(notice);
                } else {
                    jedis.lrem(MBR_PREFIX + mbrId + MBR_SUFFIX, 1, id);
                }
            }
            return noticeList;
        }
    }
}
