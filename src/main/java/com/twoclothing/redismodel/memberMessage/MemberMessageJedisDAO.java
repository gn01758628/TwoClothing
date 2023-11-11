package com.twoclothing.redismodel.memberMessage;

import com.twoclothing.utils.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MemberMessageJedisDAO implements MemberMessageDAO {

    private final JedisPool jedisPool = JedisPoolUtil.getJedisPool();

    private static final int REDIS_NUMBER = 11;

    private static final String SESSION_PREFIX = "sessionId:";

    private static final String MESSAGE_PREFIX = "messageId:";

    private static final String MEMBER_LIST_PREFIX = "memberList:";

    private static final String PARTNER_ID_PREFIX = "partnerId:";

    private final int SHORT_TTL = 60 * 60 * 24 * 30;

    private final int LONG_TTL = 60 * 60 * 24 * 60;

    @Override
    public void insert(MemberMessage message) {

        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(REDIS_NUMBER);
            // 1.儲存對話索引
            //  1.1 取得對話的key(兩個會員ID的組合,小到大排序)
            Integer senderId = message.getSenderId();
            Integer receiverId = message.getReceiverId();
            String sessionKey = getSessionKey(senderId, receiverId);
            //  1.2 取的自增的messageID
            String messageId = MESSAGE_PREFIX + jedis.incr("messageId");
            //  1.3 Sorted Set儲存(timestamp當分數)
            double timestamp = (double) message.getTimestamp();
            jedis.zadd(sessionKey, timestamp, messageId);
            jedis.expire(sessionKey, SHORT_TTL);

            // 2.儲存訊息內容與狀態
            //  2.1 Key = messageId
            //  2.2 Hash儲存
            jedis.hset(messageId, "senderId", String.valueOf(senderId));
            jedis.hset(messageId, "receiverId", String.valueOf(receiverId));
            jedis.hset(messageId, "content", message.getContent());
            jedis.hset(messageId, "status", message.getStatus().toString());
            jedis.expire(messageId, LONG_TTL);

            // 3.儲存會話列表
            //  3.1 Key跟value互為雙方Id(每次更新兩個)
            //  3.2 Sorted Set儲存(timestamp當分數)
            jedis.zadd(MEMBER_LIST_PREFIX + senderId, timestamp, PARTNER_ID_PREFIX + receiverId);
            jedis.zadd(MEMBER_LIST_PREFIX + receiverId, timestamp, PARTNER_ID_PREFIX + senderId);
            jedis.expire(MEMBER_LIST_PREFIX + senderId, LONG_TTL);
            jedis.expire(MEMBER_LIST_PREFIX + receiverId, LONG_TTL);
        }
    }

    @Override
    public List<Integer> getAllPartnerIdByMbrId(Integer mbrId) {
        List<Integer> partnerIdList = new ArrayList<>();
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(REDIS_NUMBER);
            String memberListKey = MEMBER_LIST_PREFIX + mbrId;
            Set<String> partnerIdSet = jedis.zrevrange(memberListKey, 0, -1);
            if (partnerIdSet != null) {
                for (String partnerId : partnerIdSet) {
                    // 檢查對應的sessionKey是否還存在(不存在就移除)
                    String sessionKey = getSessionKey(mbrId, Integer.parseInt(partnerId));
                    if (!jedis.exists(sessionKey)) jedis.zrem(memberListKey, partnerId);

                    partnerIdList.add(Integer.valueOf(partnerId));
                }
            }
            return partnerIdList;
        }
    }

    @Override
    public List<LastMessageInfo> getLastMessagesByMbrId(Integer mbrId) {
        List<LastMessageInfo> lastMessageList = new ArrayList<>();
        List<Integer> partnerIdList = getAllPartnerIdByMbrId(mbrId);
        if (partnerIdList == null) return null;
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(REDIS_NUMBER);

            for (Integer partnerId : partnerIdList) {
                String sessionKey = getSessionKey(mbrId, partnerId);
                String lastMessageId = jedis.zrange(sessionKey, -1, -1).stream().findFirst().orElse(null);

                if (lastMessageId != null) {
                    Map<String, String> lastMessageData = jedis.hgetAll(lastMessageId);
                    String content = lastMessageData.get("content");
                    String senderIdStr = lastMessageData.get("senderId");
                    boolean isSender = mbrId.equals(Integer.parseInt(senderIdStr));
                    double timestamp = jedis.zscore(sessionKey, lastMessageId);
                    LastMessageInfo lastMessageInfo = new LastMessageInfo(partnerId, content, isSender, (long) timestamp);
                    lastMessageList.add(lastMessageInfo);
                }
            }
            return lastMessageList;
        }
    }

    @Override
    public List<MemberMessage> getHistoryBetweenMembers(Integer memberId1, Integer memberId2) {
        List<MemberMessage> messageList = new ArrayList<>();
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(REDIS_NUMBER);
            String sessionKey = getSessionKey(memberId1, memberId2);
            Set<String> messageIdSet = jedis.zrange(sessionKey, 0, -1);
            if (messageIdSet == null) return null;
            for (String messageId : messageIdSet) {
                Map<String, String> messageData = jedis.hgetAll(messageId);
                Integer senderId = Integer.parseInt(messageData.get("senderId"));
                Integer receiverId = Integer.parseInt(messageData.get("receiverId"));
                String content = messageData.get("content");
                String status = messageData.get("status");
                double timestamp = jedis.zscore(sessionKey, messageId);
                MemberMessage message = new MemberMessage();
                message.setSenderId(senderId);
                message.setReceiverId(receiverId);
                message.setContent(content);
                message.setStatus(MemberMessage.MessageStatus.valueOf(status));
                message.setTimestamp((long) timestamp);
                messageList.add(message);
            }
            return messageList;
        }
    }

    @Override
    public void markMessagesAsRead(Integer receiverId, Integer senderId) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(REDIS_NUMBER);
            String sessionKey = getSessionKey(receiverId, senderId);
            Set<String> messageIdSet = jedis.zrange(sessionKey, 0, -1);
            if (messageIdSet == null) return;
            for (String messageId : messageIdSet) {
                // 接收者是自己的才改為已讀
                String receiverIdStr = jedis.hget(messageId, "receiverId");
                if (receiverId.equals(Integer.parseInt(receiverIdStr))) {
                    jedis.hset(messageId, "status", MemberMessage.MessageStatus.READ.toString());
                }
            }
        }
    }

    private String getSessionKey(Integer memberId1, Integer memberId2) {
        return (memberId1 < memberId2) ? SESSION_PREFIX + memberId1 + "_" + memberId2 : SESSION_PREFIX + memberId2 + "_" + memberId1;
    }
}
