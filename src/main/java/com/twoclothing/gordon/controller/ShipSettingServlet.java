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

import com.twoclothing.gordon.service.MembersServiceImpl;
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
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

			String param = "?shipId="+ shipSetting.getShipId() +
					"&mbrId="+ shipSetting.getMbrId() +
					"&receiveName="+ shipSetting.getReceiveName() +
					"&receivePhone="+ shipSetting.getReceivePhone() +
					"&receiveAddress="+ shipSetting.getReceiveAddress();
				
			String url ="/front_end/shipsetting/update_ShipSetting_input.jsp" +param ;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
					
		}
		/*************************** update ****************************************/
		/*************************** update ****************************************/
		/*************************** update ****************************************/
		/*************************** update ****************************************/
		/*************************** update ****************************************/

		if("update" .equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			Integer shipId = Integer.valueOf(req.getParameter("shipId"));
			Integer mbrId = Integer.valueOf(req.getParameter("mbrId"));
			
			String receiveName = req.getParameter("receiveName").trim();
		    String county = req.getParameter("county");
		    String district = req.getParameter("district");
		    String zipcode = req.getParameter("zipcode");
		    String address = req.getParameter("address").trim();
		    String receiveAddress =  county.concat(zipcode).concat(district).concat(address);
			String receivePhone = null;
			try {
			    String receivePhoneStr = req.getParameter("receivePhone").trim();
			    
			    if (receivePhoneStr.matches("^09\\d{8}$")) {
			        receivePhone = receivePhoneStr;
			    } else {
			        errorMsgs.put("receivePhone", "請輸入有效的台灣手機號碼");
			    }
			} catch (NumberFormatException e) {
			    errorMsgs.put("receivePhone", "請輸入有效的台灣手機號碼");
			}
			
		
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/shipsetting/update_ShipSetting_input.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
/***************************2.開始修改資料*****************************************/
			ShipSettingServiceImpl shipSettingServiceImpl = new ShipSettingServiceImpl();
			ShipSetting shipSetting =shipSettingServiceImpl.getByPrimaryKey(shipId);
			
			
			shipSetting.setMbrId(mbrId);
			shipSetting.setReceiveName(receiveName);
			shipSetting.setReceivePhone(receivePhone);
			shipSetting.setReceiveAddress(receiveAddress);
			
			shipSettingServiceImpl.updateShipSetting(shipSetting);
/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("ShipSetting", shipSetting); 
//			String url = "/front_end/shipsetting/listOneShipSetting.jsp";
			String url = "/shipsetting/Shipsetting.do?action=getAll_For_MbrId&mbrId="+mbrId;
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		
		
		}
		/***********************新增*************************/
		/***********************新增*************************/
		/***********************新增*************************/
		/***********************新增*************************/
		/***********************新增*************************/
		if ("insert".equals(action)) {
			
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
		    /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			Integer mbrId = Integer.valueOf(req.getParameter("mbrId"));
			
			String receiveName = req.getParameter("receiveName");
		    String county = req.getParameter("county");
		    String district = req.getParameter("district");
		    String zipcode = req.getParameter("zipcode");
		    String address = req.getParameter("address");
		  
		    
			String receivePhone = null;
			try {
			    String receivePhoneStr = req.getParameter("receivePhone").trim();
			    
			    if (receivePhoneStr.matches("^09\\d{8}$")) {
			        receivePhone = receivePhoneStr;
			    } else {
			        errorMsgs.put("receivePhone", "請輸入有效的台灣手機號碼");
			    }
			} catch (NumberFormatException e) {
			    errorMsgs.put("receivePhone", "請輸入有效的台灣手機號碼");
			}
		    
//		    String receiveAddress = req.getParameter("receiveAddress");
		    String receiveAddress =  county.concat(zipcode).concat(district).concat(address);
	
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/shipsetting/addShipSetting.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

	        /***************************2.開始新增資料***************************************/
			ShipSetting shipSetting = new ShipSetting();
			shipSetting.setMbrId(mbrId);
			shipSetting.setReceiveName(receiveName);
			shipSetting.setReceivePhone(receivePhone);
			shipSetting.setReceiveAddress(receiveAddress);
			
			

			ShipSettingServiceImpl shipSettingServiceImpl = new ShipSettingServiceImpl();
		    shipSettingServiceImpl.addShipSetting(shipSetting);
			
	        
		    /***************************3.修改完成,準備轉交(Send the Success view)*************/
		    String url = "/shipsetting/Shipsetting.do?action=getAll_For_MbrId&mbrId="+mbrId;

			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		/***********************刪除*************************/
		/***********************刪除*************************/
		/***********************刪除*************************/
		/***********************刪除*************************/
		/***********************刪除*************************/
		if ("delete".equals(action)) {
			
			/***************************1.接收請求參數***************************************/
			
			Integer shipId = Integer.valueOf(req.getParameter("shipId"));
			Integer mbrId =Integer.parseInt(req.getParameter("mbrId"));


			
		    /***************************2.開始刪除資料***************************************/
			
			ShipSettingServiceImpl shipSettingServiceImpl = new ShipSettingServiceImpl();
			shipSettingServiceImpl.deleteShipSetting(shipId);

		    /***************************3.刪除完成,準備轉交(Send the Success view)***********/
			
		    String url = "/shipsetting/Shipsetting.do?action=getAll_For_MbrId&mbrId="+mbrId;
		    RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
//		    String url1 ="/shipsetting/Shipsetting.do?action=getAll_For_MbrId&mbrId="+mbrId;
//
//			res.sendRedirect(url1);
			
		}
		
		
	
	}
}
