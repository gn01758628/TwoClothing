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
			String url = "/back_end/members/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

		}
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
				errorMsgs.put("sellScore","賣家分數薪水請填數字");
			}
			
			Integer buyScore  = null;
			try {
				buyScore = Integer.valueOf(req.getParameter("buyScore").trim());
			}catch (NumberFormatException e) {
				errorMsgs.put("buyScore","賣家分數薪水請填數字");
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
	}

}
