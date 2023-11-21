package com.twoclothing.chenghan.controller;

import com.google.gson.Gson;
import com.twoclothing.redismodel.notice.Notice;
import com.twoclothing.redismodel.notice.NoticeDAO;
import com.twoclothing.redismodel.notice.NoticeJedisDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/noticeHelper/*")
public class NoticeListServlet extends HttpServlet {

    private final NoticeDAO noticeDAO = new NoticeJedisDAO();

    private final Gson gson = new Gson();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/unRead" -> doUnRead(request, response);
            case "/update" -> doUpdate(request, response);
            case "/read" -> doRead(request, response);
            case "/delete" -> doDEL(request, response);
        }
    }

    private void doUnRead(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Integer mbrId = (Integer) request.getSession().getAttribute("mbrId");
        List<Notice> noticeList = noticeDAO.getNoticesByMbrIdAndRead(mbrId, false);
        out.write(gson.toJson(noticeList));
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(456);
        String idListJSON = request.getParameter("idList");
        String id = request.getParameter("id");
        if (idListJSON != null) {
            String[] idList = gson.fromJson(idListJSON, String[].class);
            noticeDAO.markNoticesAsRead(idList);
            return;
        }
        if (id != null) {
            noticeDAO.markNoticesAsRead(id);
        }
    }

    private void doRead(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Integer mbrId = (Integer) request.getSession().getAttribute("mbrId");
        List<Notice> noticeList = noticeDAO.getNoticesByMbrIdAndRead(mbrId, true);
        out.write(gson.toJson(noticeList));
    }

    private void doDEL(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(123);
        String idListJSON = request.getParameter("idList");
        Integer mbrId = (Integer) request.getSession().getAttribute("mbrId");
        if (idListJSON != null) {
            String[] idList = gson.fromJson(idListJSON, String[].class);
            noticeDAO.deleteNotices(mbrId,idList);
        }
    }
}
