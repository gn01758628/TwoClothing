package com.twoclothing.gordon.controller;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.twoclothing.gordon.service.MailService;
import com.twoclothing.gordon.service.MembersServiceImpl;
import com.twoclothing.model.members.Members;
import redis.clients.jedis.Jedis;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@WebServlet("/members/SendEmailServlet")
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
		
//		===============================寄驗證信================================
//		===============================寄驗證信================================
//		===============================寄驗證信================================
//		===============================寄驗證信================================
//		===============================寄驗證信================================
		
    	if("verificationEmail".equals(action)) {
    		
    		String to = req.getParameter("email");
    		String subject = "TwoClothing 驗證信2";
    		
    		MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
    		Members members = membersServiceImpl.getByEmail(to);

    		
    		Map<String,String> verification =  new HashMap<>();
    		
    		verification.put("activeCode", activeCode);
    		
    		Jedis jedis = new Jedis("localhost", 6379);
			
			jedis.select(0);
			
			Gson gson = new Gson();
			    String verificationValue = gson.toJson(verification);

			    String email =  String.valueOf(to);

			// 存储数據
			jedis.set(email, verificationValue);
			
			jedis.close();
    		
/////////////////////////////////////redis測試////////////////////	

    		String messageText =  "註冊成功，請點擊<a href='http://192.168.0.32/TwoClothing/members/SendEmailServlet?action=action&activeCode="
    	            +activeCode+"&email="+to;
    	           
    		System.out.println(activeCode);
    		 MailService mailService = new MailService();
    		 mailService.sendMail(to, subject, messageText);
    		 
//		        res.setContentType("text/plain");
//		        res.getWriter().write("123");
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
/////////////////////////////////////redis測試////////////////////	
    		Jedis jedis = new Jedis("localhost", 6379);
    		jedis.select(0); 

    		String emailRdis = email; 

    		String verificationValue = jedis.get(emailRdis); 
    		if (verificationValue != null) {
    		   
    		    Gson gson = new Gson();
    		    Map<String, String> verification = gson.fromJson(verificationValue, new TypeToken<Map<String, String>>() {}.getType());

    		    
    		    activeCode = verification.get("activeCode");

    		    // 在这里使用activeCode

    		    System.out.println(emailRdis);
    		    System.out.println(activeCode);
    		    jedis.close();
    		} else {
    		   
    		    System.out.println("未找到對應的值");
    		}
    		
    		
/////////////////////////////////////redis測試////////////////////	

    		if(activeCode.equals(getActiveCode)) {
    			System.out.println("激活成功");
    			System.out.println(activeCode);
    			System.out.println(getActiveCode);
    			members.setMbrStatus(1);
    			res.sendRedirect(req.getContextPath() + "/index.jsp");
    		}else {
    			System.out.println(activeCode);
    			System.out.println(getActiveCode);
    			
    		}
    		
    		
    	}
//		===============================寄忘記密碼信================================
//		===============================寄忘記密碼信================================
//		===============================寄忘記密碼信================================
//		===============================寄忘記密碼信================================
//		===============================寄忘記密碼信================================

    	if("寄出驗證信".equals(action)) {
    		System.out.println("寄出驗證信");
    		String to = req.getParameter("email3");
    		String subject = "TwoClothing 忘記密碼";
    		
    		String messageText =  "註冊成功，請點擊<a href='http://192.168.0.32/TwoClothing/front_end/members/forgotPassword.jsp?email="+to;
    	           
    		 MailService mailService = new MailService();
    		 mailService.sendMail(to, subject, messageText);
    		 
    		 String url = "/front_end/members/registerLogin.jsp";
         	RequestDispatcher successView = req.getRequestDispatcher(url); 
         	successView.forward(req, res);
    	}
    	}


}
