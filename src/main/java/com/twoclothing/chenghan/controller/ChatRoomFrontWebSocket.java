package com.twoclothing.chenghan.controller;

import com.google.gson.Gson;
import com.twoclothing.model.members.Members;
import com.twoclothing.model.members.MembersDAO;
import com.twoclothing.model.members.MembersHibernateDAO;
import com.twoclothing.redismodel.memberMessage.LastMessageInfo;
import com.twoclothing.redismodel.memberMessage.MemberMessage;
import com.twoclothing.redismodel.memberMessage.MemberMessageDAO;
import com.twoclothing.redismodel.memberMessage.MemberMessageJedisDAO;
import com.twoclothing.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


// ServerEndpoint的路徑註冊,可以使用{}來代表一個動態的路徑參數
// 等同Servlet的前置路徑對應註冊
// {}內的變數,可以利用@PathParam("mbrId")來取得,但只能用在方法參數上
@ServerEndpoint("/front/chatRoomWS/{mbrId}/{targetId}")
public class ChatRoomFrontWebSocket {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private final MembersDAO membersDAO = new MembersHibernateDAO(sessionFactory);

    private final MemberMessageDAO memberMessageDAO = new MemberMessageJedisDAO();

    private static final Gson gson = new Gson();

    // websocket的Session
    //  代表一個WebSocket對話
    //  每當一個客戶端與服務器建立連線時,都會創立一個session代表該連接
    private final static Map<Integer, Session> sessionMap = new ConcurrentHashMap<>();

    // 客戶端與服務器端建立連結時
    @OnOpen
    public void onOpen(@PathParam("mbrId") Integer mbrId, @PathParam("targetId") Integer targetId, Session userSession) {
        sessionMap.put(mbrId, userSession);
        // 通知其他關連會員,此會員上線
        // 取得該會員的會話列表
        List<Integer> partnerIdList = memberMessageDAO.getAllPartnerIdByMbrId(mbrId);
        // 收集列表中還在線上的Id
        List<Integer> onlineList = new ArrayList<>();
        // 建立message
        Map<String, String> message = new HashMap<>();
        message.put("type", "online");
        message.put("partnerId", String.valueOf(mbrId));
        String messageJson = gson.toJson(message);
        // 遍歷決定要向哪些session發送onMessage(告知上線)
        Session session;
        for (Integer partnerId : partnerIdList) {
            if (sessionMap.containsKey(partnerId) && (session = sessionMap.get(partnerId)).isOpen()) {
                session.getAsyncRemote().sendText(messageJson);
                onlineList.add(partnerId);
            }
        }

        // 向自己推播必要訊息來載入畫面
        // 遍歷所有partnerIdList,得到所有未讀訊息的數量
        Map<Integer, Integer> unreadNumMap = new HashMap<>();
        for (Integer id : partnerIdList) {
            int unreadNum = memberMessageDAO.getUnreadMessageCount(mbrId, id);
            unreadNumMap.put(id, unreadNum);
        }
        // 取得該會員與其他全部會員的最後一條訊息內容
        List<LastMessageInfo> lastMessagesList = memberMessageDAO.getLastMessagesByMbrId(mbrId);
        // 建立message
        Map<String, String> messageSelf = new HashMap<>();
        messageSelf.put("type", "first");
        messageSelf.put("targetId", String.valueOf(targetId));
        messageSelf.put("partnerIdList", gson.toJson(partnerIdList));
        messageSelf.put("onlineList", gson.toJson(onlineList));
        messageSelf.put("unreadNumMap", gson.toJson(unreadNumMap));
        messageSelf.put("lastMessagesList", gson.toJson(lastMessagesList));

        // 綁定對應會員的帳號或名稱
        Map<Integer, String[]> mbrInfoMap = new HashMap<>();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            for (Integer partnerId : partnerIdList) {
                Members mbr = membersDAO.getByPrimaryKey(partnerId);
                if (mbr != null) {
                    mbrInfoMap.put(partnerId, new String[]{mbr.getMbrName(), mbr.getEmail()});
                }
            }
            // 包括targetId(第一次對話可能不在列表中)
            Members targetMbr = membersDAO.getByPrimaryKey(targetId);
            if (targetMbr != null) {
                mbrInfoMap.put(targetId, new String[]{targetMbr.getMbrName(), targetMbr.getEmail()});
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (sessionFactory.getCurrentSession().getTransaction().isActive())
                sessionFactory.getCurrentSession().getTransaction().rollback();
        }
        messageSelf.put("mbrInfoMap", gson.toJson(mbrInfoMap));

        String messageSelfJson = gson.toJson(messageSelf);
        userSession.getAsyncRemote().sendText(messageSelfJson);
    }

    // 服務器接收到客戶端的訊息時
    @OnMessage
    public void onMessage(Session userSession, String message, @PathParam("mbrId") Integer mbrId) {
        String[] messageArr = gson.fromJson(message, String[].class);
        String type = messageArr[0];
        // 測試用打印
//        System.out.println(Arrays.toString(messageArr));
//        System.out.println(type);
        // 訊息標籤:chat
        // 用戶發送聊天訊息
        if ("chat".equals(type)) {
            // 儲存訊息進redis
            MemberMessage memberMessage = gson.fromJson(messageArr[1], MemberMessage.class);
            memberMessageDAO.insert(memberMessage);
            // 檢查對應的session是否open
            Integer receiverId = memberMessage.getReceiverId();
            Session receiverSession = sessionMap.get(receiverId);
            if (receiverSession != null && receiverSession.isOpen()) {
                Map<String, String> sendMessage = new HashMap<>();
                sendMessage.put("type", "chat");
                sendMessage.put("singleMsg", gson.toJson(memberMessage));
                // 綁定自己的會員資訊(名稱&帳號)
                try {
                    sessionFactory.getCurrentSession().beginTransaction();
                    Members mbr = membersDAO.getByPrimaryKey(mbrId);
                    if (mbr != null) {
                        String[] mbrInfo = {mbr.getMbrName(), mbr.getEmail()};
                        sendMessage.put("mbrInfo", gson.toJson(mbrInfo));
                    }
                    sessionFactory.getCurrentSession().getTransaction().commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (sessionFactory.getCurrentSession().getTransaction().isActive())
                        sessionFactory.getCurrentSession().getTransaction().rollback();
                }
                receiverSession.getAsyncRemote().sendText(gson.toJson(sendMessage));
            }
            return;
        }

        // 訊息標籤:history
        // 用戶請求與對應用戶的歷史聊天訊息
        if ("history".equals(type)) {
            Integer currentPartnerId = Integer.parseInt(messageArr[1]);
            List<MemberMessage> historyList = memberMessageDAO.getHistoryBetweenMembers(mbrId, currentPartnerId);
            Map<String, String> sendMessage = new HashMap<>();
            sendMessage.put("type", "history");
            sendMessage.put("historyList", gson.toJson(historyList));
            userSession.getAsyncRemote().sendText(gson.toJson(sendMessage));
            return;
        }

        // 訊息標籤:update
        // 用戶閱讀訊息,更改為已讀狀態
        if ("update".equals(type)) {
            String[] idArr = gson.fromJson(messageArr[1], String[].class);
            // 更新歷史訊息
            if (idArr[0] != null) {
                memberMessageDAO.updateMessagesAsRead(mbrId, Integer.parseInt(idArr[0]));
            }
            if (idArr[1] != null && !idArr[1].equals(idArr[0])) {
                memberMessageDAO.updateMessagesAsRead(mbrId, Integer.parseInt(idArr[1]));
            }

            // 發送給對應的session(如果在線上),即時更新已讀未讀
            Map<String, String> sendMessage = new HashMap<>();
            sendMessage.put("type", "update");
            sendMessage.put("partnerId", String.valueOf(mbrId));
            String sendMessageJSON = gson.toJson(sendMessage);
            Session currentSession;
            if (idArr[0] != null) {
                currentSession = sessionMap.get(Integer.parseInt(idArr[0]));
                if (currentSession != null && currentSession.isOpen()) {
                    currentSession.getAsyncRemote().sendText(sendMessageJSON);
                }
            }
            if (idArr[1] != null && !idArr[1].equals(idArr[0])) {
                currentSession = sessionMap.get(Integer.parseInt(idArr[1]));
                if (currentSession != null && currentSession.isOpen()) {
                    currentSession.getAsyncRemote().sendText(sendMessageJSON);
                }
            }
            return;
        }

        // 訊息標籤:updateChatList
        // 用戶發送訊息,更新chatList的內容
        if ("updateChatList".equals(type)) {
            MemberMessage memberMessage = gson.fromJson(messageArr[1], MemberMessage.class);
            // 檢查對應的session是否open
            Integer receiverId = memberMessage.getReceiverId();
            Session receiverSession = sessionMap.get(receiverId);
            if (receiverSession != null && receiverSession.isOpen()) {
                Integer partnerId = memberMessage.getSenderId();
                String content = memberMessage.getContent();
                long timestamp = memberMessage.getTimestamp();
                LastMessageInfo lastMessageInfo = new LastMessageInfo(partnerId, content, false, timestamp);
                Map<String, String> sendMessage = new HashMap<>();
                sendMessage.put("type","updateChatList");
                sendMessage.put("lastMessageInfo",gson.toJson(lastMessageInfo));
                receiverSession.getAsyncRemote().sendText(gson.toJson(sendMessage));
            }
        }
    }

    // 客戶端與服務器連接關閉時
    @OnClose
    public void onClose(Session userSession, @PathParam("mbrId") Integer mbrId) {
        Set<Integer> sessionIdSet = sessionMap.keySet();
        // 離線時將userSession從sessionMap中移出
        for (Integer sessionId : sessionIdSet) {
            if (sessionMap.get(sessionId).equals(userSession)) {
                sessionMap.remove(sessionId);
                break;
            }
        }

        // 離線推播給會話列表的partner
        List<Integer> partnerIdList = memberMessageDAO.getAllPartnerIdByMbrId(mbrId);
        Map<String, String> message = new HashMap<>();
        message.put("type", "offline");
        message.put("partnerId", String.valueOf(mbrId));
        String messageJson = gson.toJson(message);
        // 遍歷決定要向哪些session發送onMessage(告知下限)
        Session session;
        for (Integer partnerId : partnerIdList) {
            if (sessionMap.containsKey(partnerId) && (session = sessionMap.get(partnerId)).isOpen()) {
                session.getAsyncRemote().sendText(messageJson);
            }
        }
    }

    // 連線過程發生錯誤時
    @OnError
    public void onError(Session userSession, Throwable e) {
        e.printStackTrace();
    }

}
