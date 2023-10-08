package com.twoclothing.web.chatroomlog;

import com.twoclothing.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatRoomLogJDBCDAO implements ChatRoomLogDAO {

    private static final String INSERT = "INSERT INTO chatroomlog (receiveid, sentid, empid, message, messagetime) VALUES (?,?,?,?,?)";

    private static final String GET_BY_PK = "SELECT * FROM chatroomlog WHERE logid = ?";

    private static final String GET_ALL = "SELECT * FROM chatroomlog";

    private static final String GET_MEMBER_LOG = "SELECT * FROM chatroomlog WHERE (receiveid = ? AND sentid = ?) OR (receiveid = ? AND sentid = ?) ORDER BY messagetime";

    private static final String GET_SERVICE_LOG = "SELECT * FROM chatroomlog WHERE (sentid = ? AND empid IS NOT NULL) OR (receiveid = ? AND empid IS NOT NULL) ORDER BY messagetime;";

    private static final String GET_LOG_BY_EMPID = "SELECT * FROM chatroomlog WHERE empid = ?";

    @Override
    public void insert(ChatRoomLog chatRoomLog) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setObject(1, chatRoomLog.getReceiveId(), Types.INTEGER);
            ps.setObject(2, chatRoomLog.getSentId(), Types.INTEGER);
            ps.setObject(3, chatRoomLog.getEmpId(), Types.INTEGER);
            ps.setString(4, chatRoomLog.getMessage());
            ps.setTimestamp(5, chatRoomLog.getMessageTime());
            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, null);
        }
        if (count == 1) {
            // 編寫新增成功的執行代碼
            System.out.println("新增成功");
        } else {
            // 編寫新增失敗的執行代碼
            System.out.println("新增失敗");
        }
    }

    @Override
    public ChatRoomLog getByPrimaryKey(Integer logId) {
        ChatRoomLog chatRoomLog = new ChatRoomLog();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(GET_BY_PK);
            ps.setInt(1, logId);
            rs = ps.executeQuery();

            if (rs.next()) {
                chatRoomLog = setChatRoomLog(rs);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }
        return chatRoomLog;
    }

    @Override
    public List<ChatRoomLog> getAll() {
        List<ChatRoomLog> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(setChatRoomLog(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }

        if (list.isEmpty()) list.add(null);
        return list;
    }

    // 把兩個會員之間的對話裝再一起
    @Override
    public List<ChatRoomLog> getLogFromMembers(Integer memberAId, Integer memberBId) {
        List<ChatRoomLog> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(GET_MEMBER_LOG);
            ps.setInt(1, memberAId);
            ps.setInt(2, memberBId);
            ps.setInt(3, memberBId);
            ps.setInt(4, memberAId);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(setChatRoomLog(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }

        if (list.isEmpty()) list.add(null);
        return list;
    }

    // 把同一個會員跟客服的對話裝再一起(員工不一定相同)
    @Override
    public List<ChatRoomLog> getLogFromService(Integer memberId) {
        List<ChatRoomLog> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(GET_SERVICE_LOG);
            ps.setInt(1, memberId);
            ps.setInt(2, memberId);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(setChatRoomLog(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }

        if (list.isEmpty()) list.add(null);
        return list;
    }

    // 取得某個員工的回覆紀錄
    @Override
    public List<ChatRoomLog> getLogByEmpId(Integer empId) {
        List<ChatRoomLog> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(GET_LOG_BY_EMPID);
            ps.setInt(1, empId);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(setChatRoomLog(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }

        if (list.isEmpty()) list.add(null);
        return list;
    }


    private ChatRoomLog setChatRoomLog(ResultSet rs) {

        ChatRoomLog chatRoomLog = new ChatRoomLog();

        try {
            chatRoomLog.setLogId(rs.getInt("logid"));
            chatRoomLog.setReceiveId(rs.getObject("receiveid", Integer.class));
            chatRoomLog.setSentId(rs.getObject("sentid", Integer.class));
            chatRoomLog.setEmpId(rs.getObject("empid", Integer.class));
            chatRoomLog.setMessage(rs.getString("message"));
            chatRoomLog.setMessageTime(rs.getTimestamp("messagetime"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chatRoomLog;
    }

}
