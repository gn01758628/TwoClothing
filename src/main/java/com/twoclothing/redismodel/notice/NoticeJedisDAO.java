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

    private static final String NOTICE_PREFIX = "noticeId:";

    private static final String MBR_PREFIX = "mbrId:";

    // 通知存活30天(2592000秒)
    private final int TTL = 2_592_000;


    @Override
    public void insert(Notice notice, Integer mbrId) {
        Jedis jedis = jedisPool.getResource();
        jedis.select(15);
        String noticeId = String.valueOf(jedis.incr("noticeId"));
        jedis.rpush(MBR_PREFIX + mbrId, NOTICE_PREFIX + noticeId);
        String noticeJson = gson.toJson(notice);
        jedis.set(NOTICE_PREFIX + noticeId, noticeJson);
        jedis.expire(NOTICE_PREFIX + noticeId, TTL);
        jedis.close();
    }

    @Override
    public List<Notice> getAllByMbrId(Integer mbrId) {
        List<Notice> noticeList = new ArrayList<>();
        Jedis jedis = jedisPool.getResource();
        jedis.select(15);
        List<String> noticeIds = jedis.lrange(MBR_PREFIX + mbrId, 0, -1);
        for (String id : noticeIds) {
            String noticeId = jedis.get(id);
            if (noticeId != null) {
                Notice notice = gson.fromJson(noticeId, Notice.class);
                noticeList.add(notice);
            } else {
                jedis.lrem(MBR_PREFIX + mbrId, 1, id);
            }
        }
        return noticeList;
    }
}
