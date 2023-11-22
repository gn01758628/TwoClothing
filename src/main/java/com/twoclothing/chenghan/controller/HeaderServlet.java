package com.twoclothing.chenghan.controller;

import com.google.gson.Gson;
import com.twoclothing.chenghan.dto.IsLoginDTO;
import com.twoclothing.chenghan.dto.MemberInfoDTO;
import com.twoclothing.model.abid.biditem.BidItemDAO;
import com.twoclothing.model.abid.biditem.BidItemHibernateDAO;
import com.twoclothing.model.aproduct.item.ItemDAO;
import com.twoclothing.model.aproduct.item.ItemHibernateDAO;
import com.twoclothing.model.members.Members;
import com.twoclothing.model.members.MembersDAO;
import com.twoclothing.model.members.MembersHibernateDAO;
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

    private final MembersDAO membersDAO = new MembersHibernateDAO(sessionFactory);

    private final JedisPool jedisPool = JedisPoolUtil.getJedisPool();

    private final NoticeDAO noticeDAO = new NoticeJedisDAO();

    private final MemberMessageDAO memberMessageDAO = new MemberMessageJedisDAO();

    private final ItemDAO itemDAO = new ItemHibernateDAO(sessionFactory);

    private final BidItemDAO bidITemDAO = new BidItemHibernateDAO(sessionFactory);

    private final Gson gson = new Gson();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/loginValidate" -> doLoginValidate(request, response);
            case "/searchInfo" -> doSearchInfo(request, response);
            case "/searchItem" -> doSearchItem(request, response);
        }
    }

    private void doLoginValidate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Integer mbrId = (Integer) request.getSession().getAttribute("mbrId");
        Members member = mbrId == null ? null : membersDAO.getByPrimaryKey(mbrId);
        PrintWriter out = response.getWriter();
        IsLoginDTO isLoginDTO = new IsLoginDTO();
        if (member == null) {
            isLoginDTO.setLogin(false);
            isLoginDTO.setMbrStatus("登入/註冊");
            out.write(gson.toJson(isLoginDTO));
        } else {
            isLoginDTO.setLogin(true);
            // 獲取會員資料
            isLoginDTO.setMbrStatus(member.getMbrName());
            isLoginDTO.setMbrAccount(member.getEmail().split("@")[0]);
            isLoginDTO.setBalance(member.getBalance());
            isLoginDTO.setMbrPoint(member.getMbrPoint());
            isLoginDTO.setMbrId(member.getMbrId());
            // 傳送IsLoginDTO
            out.write(gson.toJson(isLoginDTO));
        }
    }

    private void doSearchInfo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Integer mbrId = (Integer) request.getSession().getAttribute("mbrId");
        PrintWriter out = response.getWriter();
        MemberInfoDTO memberInfoDTO = new MemberInfoDTO();

        // 購物車數量
        try (Jedis jedis = jedisPool.getResource()) {
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

    private void doSearchItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        // 檢查是否有查詢到東西
        int itemNum = itemDAO.countActiveByItemName(name);
        int bidNum = bidITemDAO.countActiveByBidItemName(name);
        int totalCount = itemNum + bidNum;
        boolean haveResult = !(totalCount == 0);
        // 如果查詢有結果,將name保存在session
        if (haveResult) request.getSession().setAttribute("navSearch", name);
        // 回傳查詢結果
        out.write(String.valueOf(haveResult));
    }
}
