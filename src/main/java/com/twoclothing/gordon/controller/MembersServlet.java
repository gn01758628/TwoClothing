package com.twoclothing.gordon.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.twoclothing.gordon.service.MembersServiceImpl;
import com.twoclothing.model.members.Members;

@WebServlet("/members/Members.do")
public class MembersServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		/***************************pk查詢 **********************/
		/***************************pk查詢 **********************/
		/***************************pk查詢 **********************/
		/***************************pk查詢 **********************/
		/***************************pk查詢 **********************/
		/***************************pk查詢 **********************/

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("mbrId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("mbrId", "請輸入會員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/members/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer mbrId = null;
			try {
				mbrId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("mbrId", "會員編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/members/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/

			MembersServiceImpl mbrServiceHibernate = new MembersServiceImpl();
			Members members = (Members) mbrServiceHibernate.getByPrimaryKey(mbrId);
			if (members == null) {
				errorMsgs.put("mbrId", "查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/members/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("Members", members); // 資料庫取出的empVO物件,存入req
			String url = "/back_end/members/listOneMembers.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

		}
		/*************************** update ****************************************/
		/*************************** update ****************************************/
		/*************************** update ****************************************/
		/*************************** update ****************************************/
		/*************************** update ****************************************/

		if ("getOne_For_Update".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer mbrId = Integer.valueOf(req.getParameter("mbrId"));

			/*************************** 2.開始查詢資料 ****************************************/
			MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
			Members members = membersServiceImpl.getByPrimaryKey(mbrId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param = "?mbrId=" + members.getMbrId() + 
					"&mbrName=" + members.getMbrName() +
					"&email="+ members.getEmail() + 
					"&pswdHash=" + members.getPswdHash() + 
					"&mbrStatus=" + members.getMbrStatus()	+ 
					"&avatar=" + members.getAvatar() + 
					"&shopImg01=" + members.getShopImg01() + 
					"&shopImg02=" + members.getShopImg02() + 
					"&mbrPoint=" + members.getMbrPoint() + 
					"&balance=" + members.getBalance() + 
					"&buyStar=" + members.getBuyStar() + 
					"&buyRating=" + members.getBuyRating() + 
					"&sellStar=" + members.getSellStar() + 
					"&sellRating=" + members.getSellRating() + 
					"&lastLogin=" + members.getLastLogin() + 
					"&sellScore=" + members.getSellScore() + 
					"&buyScore=" + members.getBuyScore();
			String url = "/back_end/members/update_Members_input.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}
		/*************************** update ****************************************/
		/*************************** update ****************************************/
		/*************************** update ****************************************/
		/*************************** update ****************************************/
		/*************************** update ****************************************/

		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer mbrId= null;
			 mbrId = Integer.valueOf(req.getParameter("mbrId"));

			Integer sellScore  = null;
			try { 
			 sellScore = Integer.valueOf(req.getParameter("sellScore").trim());
			}catch (NumberFormatException e) {
				errorMsgs.put("sellScore","賣家分數請填數字");
			}
			
			Integer buyScore  = null;
			try {
				buyScore = Integer.valueOf(req.getParameter("buyScore").trim());
			}catch (NumberFormatException e) {
				errorMsgs.put("buyScore","賣家分數請填數字");
			}
		
			
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/members/update_Members_input.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/***************************2.開始修改資料*****************************************/
			MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
			Members members = membersServiceImpl.updateMembers(  mbrId,  sellScore,  buyScore);
		
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("Members", members); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back_end/members/listOneMembers.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
		/***********************註冊*************************/
		/***********************註冊*************************/
		/***********************註冊*************************/
		/***********************註冊*************************/
		/***********************註冊*************************/

		
		if ("register".equals(action)) {
		 
			res.setContentType("application/json; charset=UTF-8");
			res.setCharacterEncoding("UTF-8");
		    Map<String, Object> response = new HashMap<>();
		    Map<String, String> errors = new HashMap<>();
		    PrintWriter out = res.getWriter();
		    boolean success = false;
		    Gson gson = new Gson();
		   
		    

		    /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		    String email = req.getParameter("email");
		    String pswdHash = req.getParameter("pswdHash");
		    String userInputCode = req.getParameter("VerificationCode");

//===============================圖片的資料
		    HttpSession session = req.getSession();
		    String sessionCode = (String) session.getAttribute("randStr");
//===============================圖片的資料
		    MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
		    Members members = membersServiceImpl.getByEmail(email);

		    if (members == null || !members.getEmail().equals(email)) {
		    	
		        if (userInputCode != null && sessionCode != null && userInputCode.equals(sessionCode)) {

		        /***************************2.開始新增資料***************************************/
		        // MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
		        membersServiceImpl.addMembers(email, pswdHash);

		        /***************************3.修改完成,準備轉交(Send the Success view)*************/
		        String url = "/front_end/members/registerLogin.jsp";
		        RequestDispatcher successView = req.getRequestDispatcher(url);
		        successView.forward(req, res);

		        success = true;
		        
		         }else {
				       
				        errors.put("sessionCode", "驗證碼錯誤");
				        		      
				    }
//圖像驗證		       
		    } else {
		       
		        errors.put("email", "email重複");
		    		      
		    }
		    response.put("errors", errors);
		    
		    response.put("success", success);
		    // 设置JSON响应的Content-Type
		    
		   
		    
		    for (Map.Entry<String, Object> entry : response.entrySet()) {
		        String key = entry.getKey();
		        Object value = entry.getValue();
		        
		        System.out.println(key + ": " + value);
		    }
//		    String outResponse= gson.toJson(response);
		    // 将JSON响应发送回客户端
		    out.write(new Gson().toJson(response));
//		    out.println(outResponse);
//		    out.write(outResponse);

		    out.close();
		    
		}
	
			
		
			/***********************登入*************************/
			/***********************登入*************************/
			/***********************登入*************************/
			/***********************登入*************************/
			/***********************登入*************************/

			if ("login".equals(action)) {
			    Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			    req.setAttribute("errorMsgs", errorMsgs);
			  

			    // 接收请求参数
			    String email = req.getParameter("email2");
			    String pswdHash = req.getParameter("pswdHash2");
//			    String contextPath = req.getContextPath();

			    // 创建 JSON 响应
			    res.setContentType("application/json");
			    res.setCharacterEncoding("UTF-8");
			    Map<String, Object> response = new HashMap<>();
			    PrintWriter out = res.getWriter();
			    
			    
			                                                           
		    	HttpSession session = req.getSession();
		    	String location = (String) session.getAttribute("location");
		 
		    	

			    MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
			    Members members = membersServiceImpl.getByEmail(email);

			    // 设置 JSON 响应的成功标志
			    boolean success = false;

			    if (members != null) {
			        String storedPswdHash = members.getPswdHash();
			        
			        if (storedPswdHash.equals(pswdHash)) {
			        	
			            session.setAttribute("user", members);
			            success = true;
/////////////////			            
			           	if (location != null) {			    		
						    
						    session.removeAttribute("location");   
					    
						    response.put("location", location);
					    

				    	}
/////////////////			           	
			            if (members.getMbrStatus() == 0) {
			                // 设置 JSON 响应中的 mbrStatus
			                response.put("mbrStatus", 0);
			                
			            }
			        } else {
			            // 密码不匹配，显示错误消息
			            errorMsgs.put("error", "密码不正确");
			        }
			    } else {
			        // 未找到用户记录，显示错误消息
			        errorMsgs.put("error", "用户不存在");
			    }
			    
			   
			  
			    // 设置 JSON 响应的成功标志
			    response.put("success", success);

			    // 构建错误消息的 JSON 响应
			    response.put("errors", errorMsgs);

			    // 将 JSON 响应发送回客户端
			    out.write(new Gson().toJson(response));
			    out.close();
//============================================			            
//============================================			            
			}
			/***********************登出*************************/
			/***********************登出*************************/
			/***********************登出*************************/
			/***********************登出*************************/
			/***********************登出*************************/
			
			if("logout".equals(action)) {
				HttpSession  session = req.getSession();
				session.removeAttribute("user");
				session.removeAttribute("location");
				res.sendRedirect(req.getContextPath() + "/index.jsp");


			}
			
		
	}

}
