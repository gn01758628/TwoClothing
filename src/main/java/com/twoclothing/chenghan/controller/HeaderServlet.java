package com.twoclothing.chenghan.controller;

import com.google.gson.Gson;
import com.twoclothing.chenghan.IsLoginDTO;
import com.twoclothing.chenghan.MemberInfoDTO;
import com.twoclothing.model.members.Members;
import com.twoclothing.redismodel.memberMessage.MemberMessageDAO;
import com.twoclothing.redismodel.memberMessage.MemberMessageJedisDAO;
import com.twoclothing.redismodel.notice.NoticeDAO;
import com.twoclothing.redismodel.notice.NoticeJedisDAO;
import com.twoclothing.utils.HibernateUtil;
import com.twoclothing.utils.JedisPoolUtil;
import org.hibernate.SessionFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/headerHelper/*")
public class HeaderServlet extends HttpServlet {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private final JedisPool jedisPool = JedisPoolUtil.getJedisPool();

    private final NoticeDAO noticeDAO = new NoticeJedisDAO();

    private final MemberMessageDAO memberMessageDAO = new MemberMessageJedisDAO();

    private final Gson gson = new Gson();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/loginValidate" -> doLoginValidate(request, response);
            case "/search" -> doSearch(request, response);
//            case "/vent" -> doVent(request, response);
        }
    }

    private void doLoginValidate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Members user = (Members) request.getSession().getAttribute("user");
        PrintWriter out = response.getWriter();
        IsLoginDTO isLoginDTO = new IsLoginDTO();
        if (user == null) {
            isLoginDTO.setLogin(false);
            isLoginDTO.setMbrStatus("登入/註冊");
            out.write(gson.toJson(isLoginDTO));
        } else {
            isLoginDTO.setLogin(true);
            // 獲取會員資料
            isLoginDTO.setMbrStatus(user.getMbrName());
            isLoginDTO.setMbrAccount(user.getEmail().split("@")[0]);
            isLoginDTO.setBalance(user.getBalance());
            isLoginDTO.setMbrPoint(user.getMbrPoint());
            isLoginDTO.setMbrId(user.getMbrId());
            // 傳送IsLoginDTO
            out.write(gson.toJson(isLoginDTO));
        }
    }

    private void doSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Members user = (Members) request.getSession().getAttribute("user");
        Integer mbrId = user.getMbrId();
        PrintWriter out = response.getWriter();
        MemberInfoDTO memberInfoDTO = new MemberInfoDTO();

        // 購物車數量
        try(Jedis jedis = jedisPool.getResource()) {
            jedis.select(13);
            Long carNum = jedis.hlen(String.valueOf(mbrId));
            memberInfoDTO.setCarNum(carNum.intValue());
        }

        // 系統通知未讀數量
        int unreadNoticeCount = noticeDAO.getUnreadCountByMbrId(mbrId);
        memberInfoDTO.setNoticeNum(unreadNoticeCount);

        // 聊天室未讀訊息數量
        int unreadMessageCount = memberMessageDAO.getTotalUnreadMessageCount(mbrId);
        memberInfoDTO.setMessageNum(unreadMessageCount);

        // 傳送MemberInfoDTO
        out.write(gson.toJson(memberInfoDTO));
    }
}
