package com.twoclothing.redismodel.memberMessage;

import java.util.List;

public interface MemberMessageDAO {

    void insert(MemberMessage message);

    /**
     * @return 取得會話列表,同時刪除已失效的索引
     */
    List<Integer> getAllPartnerIdByMbrId(Integer mbrId);

    /**
     * @return 取得會話列表裡的最後內容信息
     */
    List<LastMessageInfo> getLastMessagesByMbrId(Integer mbrId);

    /**
     * @return 取得兩個會員間的聊天紀錄
     */
    List<MemberMessage> getHistoryBetweenMembers(Integer memberId1, Integer memberId2);

    /**
     * 將會話裡所有接收者是自己的訊息標示為已讀
     */
    void markMessagesAsRead(Integer receiverId, Integer senderId);
}
