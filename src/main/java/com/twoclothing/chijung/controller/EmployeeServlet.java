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
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.members.Members;
import com.twoclothing.tonyhsieh.service.EmployeeService;
import com.twoclothing.tonyhsieh.service.EmployeeServiceImpl;
import com.twoclothing.utils.generic.GenericService;

@WebServlet("/Employeee.do")
@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 5*1024*1024,maxRequestSize =5*5*1024*1024 )
public class EmployeeServlet extends HttpServlet {
	private GenericService gs;

//	@Override
	public void init() throws ServletException {
		this.gs = gs.getInstance();
	}

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
	            
	            Employee emp;
	            emp = gs.getByPrimaryKey(Employee.class, empid);
	            
	            if( emp == null || !str.equals(emp.getFormatEmpId()) ||  !pwd.equals(emp.getPswdHash())  ){
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
	            
	            
	            // 假設這是後端要回傳的URL
	            String url = req.getContextPath()+"/empCenter.html";
	            
	            // 將物件和URL放入JSON物件中
	            JsonObject jsonResponse = new JsonObject();
	            jsonResponse.addProperty("url", url);
	            jsonResponse.add("emp", new Gson().toJsonTree(emp));
	            
	            // 設定回應的Content Type為JSON
	            res.setContentType("application/json;charset=UTF-8");
	            
	            // 獲取PrintWriter
	            PrintWriter out = res.getWriter();
	            
	            // 將JSON字串寫入回應
	            out.print(jsonResponse.toString());
	            out.flush();
	            
	            
				break;
			}
	
	}
	

	
}
