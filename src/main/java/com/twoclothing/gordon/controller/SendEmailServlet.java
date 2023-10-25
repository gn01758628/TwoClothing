package com.twoclothing.gordon.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.twoclothing.gordon.service.MailService;
import com.twoclothing.gordon.service.MembersServiceImpl;
import com.twoclothing.model.members.Members;

@WebServlet("/back_end/members/SendEmailServlet")
public class SendEmailServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String origin = req.getHeader("Origin");
        if ("https://mail.google.com".equals(origin)) {
            // 允許Google的郵件服務訪問
            res.setHeader("Access-Control-Allow-Origin", origin);
            res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        }

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		String activeCode = RandomStringGenerator.generateRandomString(10);
		
//		===============================寄信================================
//		===============================寄信================================
//		===============================寄信================================
//		===============================寄信================================
//		===============================寄信================================
		
    	if("verificationEmail".equals(action)) {
    		
    		String to = req.getParameter("email");
    		String subject = "TwoClothing 驗證信2";
    		
    		MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
    		Members members = membersServiceImpl.getByEmail(to);
    		membersServiceImpl.updateMembers(members);
    		members.setMbrName(activeCode);
    		
    		
 //   		String messageText =  "註冊成功，請點擊<a href='http://64.190.63.111/back_end/members/SendEmailServlet?active=active&activeCode="
    		String messageText =  "註冊成功，請點擊<a href='http://192.168.0.32/TwoClothing/back_end/members/SendEmailServlet?action=action&activeCode="
    	            +activeCode+"&email="+to;
    	           
    		System.out.println(activeCode);
    		 MailService mailService = new MailService();
    		 mailService.sendMail(to, subject, messageText);
    		 
		        res.setContentType("text/plain");
		        res.getWriter().write("123");
		        res.sendRedirect(req.getContextPath() + "/front_end/members/verificationEmail.jsp");
    	}
//		===============================驗證================================
//		===============================驗證================================
//		===============================驗證================================
//		===============================驗證================================
//		===============================驗證================================

    	if("action".equals(action)) {
    		
    		String getActiveCode = req.getParameter("activeCode");
    		String email = req.getParameter("email");
    		MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
    		Members members = membersServiceImpl.getByEmail(email);
    		activeCode = members.getMbrName();
    		
    		if(activeCode.equals(getActiveCode)) {
    			System.out.println("激活成功");
    			System.out.println(activeCode);
    			System.out.println(getActiveCode);
    			members.setMbrName(null);
    			members.setMbrStatus(1);
    			res.sendRedirect(req.getContextPath() + "/index.jsp");
    		}else {
    			System.out.println(activeCode);
    			System.out.println(getActiveCode);
    			
    		}
    		
    		
    	}
    	}


}
