package com.twoclothing.redismodel.memberMessage;

import java.util.List;

public interface MemberMessageDAO {

    void insert(MemberMessage message);

    /**
     * @return 取得會話列表, 同時刪除已失效的索引
     */
    List<Integer> getAllPartnerIdByMbrId(Integer mbrId);

    /**
     * @return 取得會話列表裡每一個的最後內容信息
     */
    List<LastMessageInfo> getLastMessagesByMbrId(Integer mbrId);

    /**
     * @return 取得兩個會員間的最後內容信息
     */
    LastMessageInfo getLastMessageBetweenMembers(Integer mbrId, Integer partnerId);

    /**
     * @return 取得兩個會員間的聊天紀錄
     */
    List<MemberMessage> getHistoryBetweenMembers(Integer memberId1, Integer memberId2);

    /**
     * @return 計算接收者未讀訊息的數量
     */
    int getUnreadMessageCount(Integer receiverId, Integer senderId);

    /**
     * 將會話裡所有接收者是自己的訊息標示為已讀
     */
    void updateMessagesAsRead(Integer receiverId, Integer senderId);
}
