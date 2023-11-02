package com.twoclothing.tonyhsieh.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.sql.Timestamp;
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

import com.twoclothing.model.abid.biditemreport.BidItemReport;
import com.twoclothing.model.department.Department;
import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.empmissions.EmpMissions;
import com.twoclothing.tonyhsieh.service.BidItemReportService;
import com.twoclothing.tonyhsieh.service.BidItemReportServiceImpl;
import com.twoclothing.tonyhsieh.service.DepartmentService;
import com.twoclothing.tonyhsieh.service.DepartmentServiceImpl;
import com.twoclothing.tonyhsieh.service.EmpMissionsService;
import com.twoclothing.tonyhsieh.service.EmpMissionsServiceImpl;
import com.twoclothing.tonyhsieh.service.EmployeeServiceImpl;


@WebServlet("/back_end/empmissions/EmpMissions.do")
@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 5*1024*1024,maxRequestSize =5*5*1024*1024 )
public class EmpMissionsServlet extends HttpServlet {
	
	private EmpMissionsService empMissionsService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getAll".equals(action)) {
	
				/***************************1.開始查詢資料***************************************/
			EmpMissionsService empMissionsService = new EmpMissionsServiceImpl();
				List<EmpMissions> list = empMissionsService.getAll();
				
				/***************************3.查詢完成,準備轉交(Send the Success view)***********/								
				req.setAttribute("list", list);
				String url = "/back_end/empmissions/listAllempmis.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 查詢成功後,轉交回送出來源網頁
				successView.forward(req, resp);

			}
		
		
		if ("getAll_By_Empid".equals(action)) { // 來自select_page.jsp的請求
			
			Integer empid = Integer.valueOf(req.getParameter("empid"));
			
			/***************************2.開始查詢資料***************************************/
			EmpMissionsService empMissionsService = new EmpMissionsServiceImpl();
				List<EmpMissions> list = empMissionsService.getAllByEmpId(empid);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)***********/								
				req.setAttribute("list", list);
				String url = "/back_end/empmissions/listAllempmis.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 查詢成功後,轉交回送出來源網頁
				successView.forward(req, resp);

		}
		
		
		if ("getAll_By_Permissionid".equals(action)) { // 來自select_page.jsp的請求
			
			Integer permissionid = Integer.valueOf(req.getParameter("permissionid"));
			
			/***************************2.開始查詢資料***************************************/
			EmpMissionsService empMissionsService = new EmpMissionsServiceImpl();
				List<EmpMissions> list = empMissionsService.getAllByPermissionId(permissionid);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)***********/								
				req.setAttribute("list", list);
				String url = "/back_end/empmissions/listAllempmis.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 查詢成功後,轉交回送出來源網頁
				successView.forward(req, resp);

		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

				
				/***************************1.接收請求參數****************************************/
				Integer empid = Integer.valueOf(req.getParameter("empid"));
				Integer permissionid = Integer.valueOf(req.getParameter("permissionid"));
				
				/***************************2.開始查詢資料****************************************/
				EmpMissionsService empMissionsService = new EmpMissionsServiceImpl();
				EmpMissions empMissions = empMissionsService.getByCompositeKey(empid, permissionid);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?empid="  +empMissions.getCompositeKey().getEmpId()+
						       "&permissionid="  +empMissions.getCompositeKey().getPermissionId();
						       
				String url = "update_empmis_input.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, resp);
		}
				
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
				
				/***************************1.接收請求參數 **********************/
			
			Integer empid = Integer.valueOf(req.getParameter("empid").trim());
			
			Integer permissionid = Integer.valueOf(req.getParameter("permissionid").trim());
		
				
				/***************************2.開始修改資料*****************************************/
						
			EmpMissionsService empMissionsService = new EmpMissionsServiceImpl();
			EmpMissions empMissions = empMissionsService.update(empid, permissionid);
								
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("EmpMissions", empMissions); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back_end/empmissions/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, resp);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			
				/***********************1.接收請求參數 *************************/
			Integer empid = Integer.valueOf(req.getParameter("empid").trim());
			// 获取用户选择的权限标识符
			String[] permissionIds = req.getParameterValues("permissionId");
			for(String Id : permissionIds) {
				Integer permissionid =Integer.valueOf(Id);
				EmpMissionsService empMissionsService = new EmpMissionsServiceImpl();
				empMissionsService.insert(empid, permissionid);			
			}
			
			
						
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back_end/empmissions/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, resp);				
		}
		
		
    	if ("delete".equals(action)) { // 來自listAllEmp.jsp

				/***************************1.接收請求參數***************************************/
				Integer empid = Integer.valueOf(req.getParameter("empid"));
				Integer permissionid = Integer.valueOf(req.getParameter("permissionid"));
				/***************************2.開始刪除資料***************************************/
				EmpMissionsService empMissionsService = new EmpMissionsServiceImpl();
				empMissionsService.delete(empid, permissionid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back_end/empmissions/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, resp);
								
		}
        
        
	
	}
	

	
}
