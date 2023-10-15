package com.twoclothing.model.chatroomlog;

import java.util.List;

public interface ChatRoomLogDAO {

    void insert(ChatRoomLog chatRoomLog);

    ChatRoomLog getByPrimaryKey(Integer logId);

    List<ChatRoomLog> getAll();

    // 取得會員間的對話紀錄
    List<ChatRoomLog> getLogFromMembers(Integer memberAId, Integer memberBId);

    // 取得客服對話紀錄(memberID = receiveid or sentid)
    List<ChatRoomLog> getLogFromService(Integer memberID);

    // 取得某個員工的回覆紀錄
    List<ChatRoomLog> getLogByEmpId(Integer empId);

}
