package com.twoclothing.chenghan.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/front/chatroom/*")
public class ChatRoomFrontServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer mbrId = (Integer) request.getSession().getAttribute("mbrId");
        String targetId = request.getPathInfo().split("[/.]")[1];
        request.setAttribute("targetId", targetId);
        request.setAttribute("mbrId", mbrId);
        request.getRequestDispatcher("/front_end/chatroom/ChatRoom.jsp").forward(request, response);
    }
}
