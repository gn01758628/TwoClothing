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

    @Override
    public void insert(Notice notice, String mbrId) {
        Jedis jedis = jedisPool.getResource();
        jedis.select(15);
        String noticeId = String.valueOf(jedis.incr("noticeId"));
        jedis.rpush("mbrId:" + mbrId, "noticeId:" + noticeId);
        String noticeJson = gson.toJson(notice);
        jedis.set("noticeId:" + noticeId, noticeJson);
        jedis.close();
    }

    @Override
    public List<Notice> getAllByMbrId(String mbrId) {
        List<Notice> noticeList = new ArrayList<>();
        Jedis jedis = jedisPool.getResource();
        jedis.select(15);
        List<String> noticeIds = jedis.lrange("mbrId:" + mbrId, 0, -1);
        for (String id : noticeIds) {
            noticeList.add(gson.fromJson(jedis.get(id), Notice.class));
        }
        return noticeList;
    }
}
