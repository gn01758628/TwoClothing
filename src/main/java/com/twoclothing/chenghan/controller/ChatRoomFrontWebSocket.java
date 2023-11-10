package com.twoclothing.chenghan.controller;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


// ServerEndpoint的路徑註冊,可以使用{}來代表一個動態的路徑參數
// 等同Servlet的前置路徑對應註冊
// {}內的變數,可以利用@PathParam("mbrId")來取得,但只能用在方法參數上
@ServerEndpoint("/front/chatRoomWS/{mbrId}/{targetId}")
public class ChatRoomFrontWebSocket {

    // websocket.Session
    //  代表一個WebSocket對話
    //  每當一個客戶端與服務器建立連線時,都會創立一個session代表該連接
    private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    // 客戶端與服務器端建立連結時
    @OnOpen
    public void onOpen(@PathParam("mbrId") Integer mbrId, @PathParam("targetId") Integer targetId, Session userSession) {
        System.out.println("Hello!!會員：" + mbrId + "號");
        System.out.println("你現在正要跟" + targetId + "號聊天");
    }

    // 服務器接收到客戶端的訊息時
//    @OnMessage
//    public void onMessage() {
//
//    }

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
