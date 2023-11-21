package com.twoclothing.redismodel.notice;

import com.google.gson.Gson;
import com.twoclothing.utils.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoticeJedisDAO implements NoticeDAO {

    private final JedisPool jedisPool = JedisPoolUtil.getJedisPool();

    private final Gson gson = new Gson();

    private static final int REDIS_NUMBER = 15;

    private static final String NOTICE_PREFIX = "notice:";

    private static final String MBR_PREFIX = "mbr:";

    private static final String MBR_SUFFIX = ":notice";

    // 通知存活30天
    private final int TTL = 60 * 60 * 24 * 30;


    @Override
    public void insert(Notice notice, Integer mbrId) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(REDIS_NUMBER);
            // List儲存 (Key:會員ID,value:通知自增主鍵)
            String value = NOTICE_PREFIX + jedis.incr("noticeId");
            jedis.lpush(MBR_PREFIX + mbrId + MBR_SUFFIX, value);

            // Hash儲存 (Key:通知的自增主鍵)
            jedis.hset(value, "type", notice.getType());
            jedis.hset(value, "head", notice.getHead());
            jedis.hset(value, "content", notice.getContent());
            jedis.hset(value, "link", notice.getLink());
            jedis.hset(value, "imageLink", notice.getImageLink());
            jedis.hset(value, "read", Boolean.toString(notice.isRead()));
            jedis.hset(value, "timestamp", String.valueOf(System.currentTimeMillis()));

            jedis.expire(value, TTL);
        }
    }

    @Override
    public List<Notice> getAllByMbrId(Integer mbrId) {
        List<Notice> noticeList = new ArrayList<>();
        try (Jedis jedis = jedisPool.getResource()) {
            // 獲取所有與會員有關的通知ID
            List<String> noticeIdList = jedis.lrange(MBR_PREFIX + mbrId + MBR_SUFFIX, 0, -1);
            if (noticeIdList != null && !noticeIdList.isEmpty()) {

                for (String noticeId : noticeIdList) {
                    Map<String, String> noticeData = jedis.hgetAll(noticeId);

                    if (noticeData == null || noticeData.isEmpty()) {
                        jedis.lrem(MBR_PREFIX + mbrId + MBR_SUFFIX, 0, noticeId);
                        continue;
                    }

                    Notice notice = new Notice();
                    notice.setType(noticeData.get("type"));
                    notice.setHead(noticeData.get("head"));
                    notice.setContent(noticeData.get("content"));
                    notice.setLink(noticeData.get("link"));
                    notice.setImageLink(noticeData.get("imageLink"));
                    notice.setRead(Boolean.parseBoolean(noticeData.get("read")));
                    notice.setNoticeId(noticeId);
                    notice.setTimestamp(Long.parseLong(noticeData.get("timestamp")));
                    noticeList.add(notice);
                }
            }
            return noticeList;
        }
    }

    @Override
    public List<Notice> getNoticesByMbrIdAndRead(Integer mbrId, boolean read) {
        List<Notice> noticeList = new ArrayList<>();
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(REDIS_NUMBER);

            List<String> noticeIdList = jedis.lrange(MBR_PREFIX + mbrId + MBR_SUFFIX, 0, -1);

            for (String noticeId : noticeIdList) {
                Map<String, String> noticeData = jedis.hgetAll(noticeId);

                if (noticeData == null || noticeData.isEmpty()) {
                    jedis.lrem(MBR_PREFIX + mbrId + MBR_SUFFIX, 0, noticeId);
                    continue;
                }

                boolean isRead = Boolean.parseBoolean(noticeData.get("read"));
                if (isRead == read) {
                    Notice notice = new Notice();
                    notice.setType(noticeData.get("type"));
                    notice.setHead(noticeData.get("head"));
                    notice.setContent(noticeData.get("content"));
                    notice.setLink(noticeData.get("link"));
                    notice.setImageLink(noticeData.get("imageLink"));
                    notice.setRead(isRead);
                    notice.setNoticeId(noticeId);
                    notice.setTimestamp(Long.parseLong(noticeData.get("timestamp")));
                    noticeList.add(notice);
                }
            }
        }
        return noticeList;
    }

    @Override
    public int getUnreadCountByMbrId(Integer mbrId) {
        int unreadCount = 0;
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(REDIS_NUMBER);

            List<String> noticeKeys = jedis.lrange(MBR_PREFIX + mbrId + MBR_SUFFIX, 0, -1);

            for (String noticeKey : noticeKeys) {
                String readStatus = jedis.hget(noticeKey, "read");

                if (readStatus == null) {
                    jedis.lrem(MBR_PREFIX + mbrId + MBR_SUFFIX, 0, noticeKey);
                    continue;
                }

                if ("false".equals(readStatus)) {
                    unreadCount++;
                }
            }
        }
        return unreadCount;
    }

    @Override
    public void markNoticesAsRead(String... noticeIds) {
        if (noticeIds == null || noticeIds.length == 0) {
            return;
        }

        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(REDIS_NUMBER);

            for (String noticeId : noticeIds) {
                if (noticeId != null && !noticeId.isEmpty()) {
                    jedis.hset(noticeId, "read", "true");
                }
            }
        }
    }

    @Override
    public void deleteNotices(Integer mbrId, String... noticeIds) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(REDIS_NUMBER);
            for (String noticeId : noticeIds) {
                jedis.lrem(MBR_PREFIX + mbrId + MBR_SUFFIX, 0, noticeId);
                jedis.del(noticeId);
            }
        }
    }
}
