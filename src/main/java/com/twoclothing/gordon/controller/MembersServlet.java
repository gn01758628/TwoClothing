package com.twoclothing.gordon.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.twoclothing.gordon.service.MembersServiceImpl;
import com.twoclothing.model.members.Members;
@WebServlet("/Members.do")
public class MembersServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str = req.getParameter("mbrId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("empno","請輸入會員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/members/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			Integer mbrId = null;
			try {
				mbrId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("mbrId","會員編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/members/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************2.開始查詢資料*****************************************/

			MembersServiceImpl mbrServiceHibernate = new MembersServiceImpl();
			Members members = (Members) mbrServiceHibernate.getByPrimaryKey(mbrId);
			if(members == null) {
				errorMsgs.put("mbrId","查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/members/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("Members", members); // 資料庫取出的empVO物件,存入req
			String url = "/back_end/members/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
			
			
		}
		
		
		
		
		
		
		
	}
	
	

}