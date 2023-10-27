package com.twoclothing.gordon.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.gordon.service.ShipSettingServiceImpl;
import com.twoclothing.model.shipsetting.ShipSetting;

@WebServlet("/shipsetting/Shipsetting.do")
public class ShipSettingServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
		/***************************mbrId**********************/
		/***************************mbrId**********************/
		/***************************mbrId**********************/
		/***************************mbrId**********************/
		/***************************mbrId**********************/
		
		if("getAll_For_MbrId".equals(action)) {
			Integer mbrId =Integer.parseInt(req.getParameter("mbrId"));
			
System.out.println(mbrId);			
/*************************** 2.開始查詢資料 *****************************************/

			ShipSettingServiceImpl shipSettingServiceImpl = new ShipSettingServiceImpl();
			List<ShipSetting> shipSetting =  shipSettingServiceImpl.getByMbrId(mbrId);
/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("ShipSetting", shipSetting);
			String url = "/front_end/shipsetting/listAllShipSetting.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		
		}
		/*************************** update ****************************************/
		/*************************** update ****************************************/
		/*************************** update ****************************************/
		/*************************** update ****************************************/
		/*************************** update ****************************************/
		
		if("getOne_For_Update".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
/*************************** 1.接收請求參數 ****************************************/
			Integer mbrId = Integer.valueOf(req.getParameter("mbrId"));
			Integer shipId = Integer.valueOf(req.getParameter("shipId"));
			
/*************************** 2.開始查詢資料 ****************************************/
			ShipSettingServiceImpl shipSettingServiceImpl = new ShipSettingServiceImpl();
			ShipSetting shipSetting = shipSettingServiceImpl.getByPrimaryKey(shipId);			
			
			
			String param = "?shipId="+ shipSetting.getShipId() +
					"&mbrId="+ shipSetting.getMbrId() +
					"&receiveName="+ shipSetting.getReceiveName() +
					"&receivePhone="+ shipSetting.getReceivePhone() +
					"&receiveAddress="+ shipSetting.getReceiveAddress();
				
			String url ="/front_end/shipsetting/update_ShipSetting_input.jsp" +param ;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
					
		}
		
		if("update" .equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			Integer shipId = Integer.valueOf(req.getParameter("shipId"));
			Integer mbrId = Integer.valueOf(req.getParameter("mbrId"));
			
			Integer receivePhone = null;
			try {
			    String receivePhoneStr = req.getParameter("receivePhone").trim();
			    
			    if (receivePhoneStr.matches("^09\\d{8}$")) {
			        receivePhone = Integer.valueOf(receivePhoneStr);
			    } else {
			        errorMsgs.put("receivePhone", "請輸入有效的台灣手機號碼");
			    }
			} catch (NumberFormatException e) {
			    errorMsgs.put("receivePhone", "請輸入有效的台灣手機號碼");
			}
		
		}
		
	
	}
}
