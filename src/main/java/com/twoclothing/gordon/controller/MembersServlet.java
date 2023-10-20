package com.twoclothing.gordon.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.gordon.service.MembersServiceImpl;
import com.twoclothing.model.members.Members;

@WebServlet("/back_end/members/Members.do")
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
				errorMsgs.put("empno", "請輸入會員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("select_page.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("select_page.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("Members", members); // 資料庫取出的empVO物件,存入req
			String url = "/back_end/members/listOneEmp.jsp";
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
			String url = "update_emp_input.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

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
				RequestDispatcher failureView = req.getRequestDispatcher("update_emp_input.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/***************************2.開始修改資料*****************************************/
			MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
			Members members = membersServiceImpl.updateMembers(  mbrId,  sellScore,  buyScore);
		
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("Members", members); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
		/***********************註冊*************************/
		/***********************註冊*************************/
		/***********************註冊*************************/
		/***********************註冊*************************/
		/***********************註冊*************************/

//		if("insert".equals(action)) {
//
//
//			
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//			String email = req.getParameter("email");
//			String emailReg = "^[a-zA-Z0-9_!#$%&'\\*+/=?{|}~^.-]+@[a-zA-Z0-9.-]+$";
//			if (email == null || email.trim().length() == 0) {
//				errorMsgs.put("email","email: 請勿空白");
//			} else if(!email.trim().matches(emailReg)) { //以下練習正則(規)表示式(regular-expression)
//				errorMsgs.put("email","email格式不正確");
//            }
//			System.out.println(email);
//			String pswdHash = req.getParameter("pswdHash");
//			String pswdHashReg = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";
//			if (pswdHash == null || pswdHash.trim().length() == 0) {
//				errorMsgs.put("pswdHash","密碼: 請勿空白");
//			} else if(!pswdHash.trim().matches(pswdHashReg)) { //以下練習正則(規)表示式(regular-expression)
//				errorMsgs.put("pswdHash","包含至少一个小写字母、一个大写字母和一个数字");
//            }
//			System.out.println(pswdHash);
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("register.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			/***************************2.開始新增資料***************************************/
//			MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
//			membersServiceImpl.addMembers(email,pswdHash);
//		
//			/***************************3.修改完成,準備轉交(Send the Success view)*************/
//			String url = "/TwoClothing/front_end/members/logon.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//			successView.forward(req, res);
//			
//		}
		/***********************註冊*************************/
		/***********************註冊*************************/
		/***********************註冊*************************/
		/***********************註冊*************************/
		/***********************註冊*************************/
		
			if("register".equals(action)) {


			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String mbrName = req.getParameter("mbrName");
			String mbrNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (mbrName == null || mbrName.trim().length() == 0) {
				errorMsgs.put("mbrName","姓名: 請勿空白");
			} else if(!mbrName.trim().matches(mbrNameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("mbrName","姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			
			
			String email = req.getParameter("email");
			String emailReg = "^[a-zA-Z0-9_!#$%&'\\*+/=?{|}~^.-]+@[a-zA-Z0-9.-]+$";
			if (email == null || email.trim().length() == 0) {
				errorMsgs.put("email","email: 請勿空白");
			} else if(!email.trim().matches(emailReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("email","email格式不正確");
            }
			System.out.println(email);
			String pswdHash = req.getParameter("pswdHash");
			String pswdHashReg = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";
			if (pswdHash == null || pswdHash.trim().length() == 0) {
				errorMsgs.put("pswdHash","密碼: 請勿空白");
			} else if(!pswdHash.trim().matches(pswdHashReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("pswdHash","包含至少一个小写字母、一个大写字母和一个数字");
            }
			System.out.println(pswdHash);
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/members/registerLogin.jsp");
				failureView.forward(req, res);
				return;
			}

			/***************************2.開始新增資料***************************************/
			MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
			membersServiceImpl.addMembers(mbrName, email,pswdHash);
		
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			String url = "/front_end/members/registerLogin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
			
		}
			/***********************登入*************************/
			/***********************登入*************************/
			/***********************登入*************************/
			/***********************登入*************************/
			/***********************登入*************************/
			if("login".equals(action)) {
				
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
			}
		
	}

}
