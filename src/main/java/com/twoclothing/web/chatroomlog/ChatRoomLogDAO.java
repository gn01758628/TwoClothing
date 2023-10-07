package com.twoclothing.web.chatroomlog;

import java.util.List;

public interface ChatRoomLogDAO {

    public void insert(ChatRoomLog chatRoomLog);

    public ChatRoomLog getByPrimaryKey(Integer logId);

    public List<ChatRoomLog> getAll();

    // 取得會員間的對話紀錄
    public List<ChatRoomLog> getLogFromMembers(Integer receiveId, Integer sentID);

    // 取得客服對話紀錄(會員接收)
    public List<ChatRoomLog> getLogFromEmployeeRe(Integer empID, Integer receiveID);

    // 取得客服對話紀錄(會員發送)
    public List<ChatRoomLog> getLogFromEmployeeSe(Integer empID, Integer sentID);

}
