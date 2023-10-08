package com.twoclothing.web.chatroomlog;

import java.util.List;

public interface ChatRoomLogDAO {

    void insert(ChatRoomLog chatRoomLog);

    ChatRoomLog getByPrimaryKey(Integer logId);

    List<ChatRoomLog> getAll();

    // 取得會員間的對話紀錄
    List<ChatRoomLog> getLogFromMembers(Integer receiveId, Integer sentID);

    // 取得客服對話紀錄(會員接收)
    List<ChatRoomLog> getLogFromEmployeeRe(Integer empID, Integer receiveID);

    // 取得客服對話紀錄(會員發送)
    List<ChatRoomLog> getLogFromEmployeeSe(Integer empID, Integer sentID);

}
