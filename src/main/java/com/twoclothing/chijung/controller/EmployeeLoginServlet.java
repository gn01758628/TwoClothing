package com.twoclothing.chijung.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.members.Members;
import com.twoclothing.tonyhsieh.service.EmployeeService;
import com.twoclothing.tonyhsieh.service.EmployeeServiceImpl;
import com.twoclothing.utils.generic.GenericService;

import at.favre.lib.crypto.bcrypt.BCrypt;

@WebServlet("/EmployeeLogin.do")
@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 5*1024*1024,maxRequestSize =5*5*1024*1024 )
public class EmployeeLoginServlet extends HttpServlet {
	private GenericService gs;
//	@Override
	public void init() throws ServletException {
		this.gs = gs.getInstance();
	}
	
	String errorUrl ="/TwoClothing/empLogin.html";
	String successUrl ="/TwoClothing/back_end/empCenter.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		switch (action) {
			case "login":
				login(req,res);
				break;
				
//			case "center":
//				center(req,res);
//				break;
				
			case "logout":
				logout(req,res);
				break;
				
//			case "check":
//				check(req,res);
//				break;
		}
	
	}
	private void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		Integer empid = null;
		String str = req.getParameter("empId");
		if (str == null || (str.trim()).length() == 0) {
			errorMsgs.put("empId","請輸入員工編號");
		}else {
			try {
				empid = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("empId","員工編號格式不正確");
			}
		}
		
        String pwd = req.getParameter("password");
        if (pwd == null || (pwd.trim()).length() == 0) {
			errorMsgs.put("password","請輸入密碼");
		}
        
        if (!errorMsgs.isEmpty()) {
    		res.setContentType("application/json; charset=UTF-8");
    	    PrintWriter out = res.getWriter();
    		res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 設置響應狀態碼為400
    		String errorMessage = new Gson().toJson(errorMsgs);
            out.print(errorMessage);
            out.flush();
            return;
		}
        
        Employee emp = gs.getByPrimaryKey(Employee.class, empid);
        
        BCrypt.Result result = BCrypt.verifyer().verify(pwd.toCharArray(), emp.getPswdHash());
        
        if( emp == null || !str.equals(emp.getFormatEmpId()) ||  !result.verified  ){
        	errorMsgs.put("idOrPassword","編號或密碼輸入錯誤");
        }
		
        if(!errorMsgs.isEmpty()){
        	res.setContentType("application/json; charset=UTF-8");
    	    PrintWriter out = res.getWriter();
    		res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 設置響應狀態碼為400
    		String errorMessage = new Gson().toJson(errorMsgs);
            out.print(errorMessage);
            out.flush();
            return;
        }
        //儲存該員工資料到Session
        HttpSession session = req.getSession();
        session.setAttribute("emp", emp);
        
        res.setContentType("text/html;charset=UTF-8");
        
        // 獲取PrintWriter
        PrintWriter out = res.getWriter();
        
        out.print(successUrl);
        out.flush();
	}

//	private void center(HttpServletRequest req, HttpServletResponse res) throws IOException{
//		HttpSession  session = req.getSession();
//		Employee emp = (Employee)session.getAttribute("emp");               
//		if (emp == null) {                                      
//			 res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//			 res.setContentType("text/plain");
//			 res.getWriter().write(errorUrl);
//			 return;
//		} 
//		res.setContentType("application/json; charset=UTF-8");
//		
//	    PrintWriter out = res.getWriter();
//		String empData = new Gson().toJson(emp);
//        out.print(empData);
//        out.flush();
//        return;
//		
//	}
	
	private void logout(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		HttpSession  session = req.getSession();
		session.removeAttribute("emp");
		
        
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.print(errorUrl);
        out.flush();
	}
	
//	private void check(HttpServletRequest req, HttpServletResponse res) throws IOException{
//		HttpSession  session = req.getSession();
//		Employee emp = (Employee)session.getAttribute("emp");               
//		if (emp == null) {                                      
//			 res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//			 res.setContentType("text/plain");
//			 res.getWriter().write(errorUrl);
//			 return;
//		}
//	}
}
