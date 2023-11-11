package com.twoclothing.chenghan.controller;

import com.google.gson.Gson;
import com.twoclothing.model.members.Members;
import com.twoclothing.model.members.MembersDAO;
import com.twoclothing.model.members.MembersHibernateDAO;
import com.twoclothing.redismodel.memberMessage.MemberMessage;
import com.twoclothing.redismodel.memberMessage.MemberMessageDAO;
import com.twoclothing.redismodel.memberMessage.MemberMessageJedisDAO;
import com.twoclothing.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        // 通知自己刷新會話列表,並打開哪個對話框
        // 建立message
        Map<String, String> messageSelf = new HashMap<>();
        messageSelf.put("type", "first");
        messageSelf.put("targetId", String.valueOf(targetId));
        messageSelf.put("partnerIdList", gson.toJson(partnerIdList));
        messageSelf.put("onlineList", gson.toJson(onlineList));

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
        sessionMap.get(mbrId).getAsyncRemote().sendText(messageSelfJson);
    }

    // 服務器接收到客戶端的訊息時
    @OnMessage
    public void onMessage(Session session,String message) {
        String[] messageArr = gson.fromJson(message, String[].class);
        String type = messageArr[0];
        MemberMessage memberMessage = gson.fromJson(messageArr[1], MemberMessage.class);
        System.out.println(type);
        System.out.println(memberMessage);
    }

    // 客戶端與服務器連接關閉時
    @OnClose
    public void onClose() {
    }

//    // 連線過程發生錯誤時
//    @OnError
//    public void onError() {
//
//    }

}
