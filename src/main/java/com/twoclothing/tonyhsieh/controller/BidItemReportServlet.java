package com.twoclothing.tonyhsieh.controller;

import java.io.IOException;
import java.io.InputStream;
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

import com.twoclothing.model.abid.biditemreport.BidItemReport;
import com.twoclothing.model.department.Department;
import com.twoclothing.tonyhsieh.service.BidItemReportService;
import com.twoclothing.tonyhsieh.service.BidItemReportServiceImpl;
import com.twoclothing.tonyhsieh.service.DepartmentService;
import com.twoclothing.tonyhsieh.service.DepartmentServiceImpl;


@WebServlet("/front_end/biditemreport/BidItemReport.do")
@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 5*1024*1024,maxRequestSize =5*5*1024*1024 )
public class BidItemReportServlet extends HttpServlet {
	
	private BidItemReportService bidItemReportService;

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
				BidItemReportServiceImpl bidItemReportServiceImpl = new BidItemReportServiceImpl();
				List<BidItemReport> list = bidItemReportServiceImpl.getAll();
				
				/***************************3.查詢完成,準備轉交(Send the Success view)***********/								
				req.setAttribute("list", list);
				String url = "/front_end/biditemreport/listAllbidItRe.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 查詢成功後,轉交回送出來源網頁
				successView.forward(req, resp);

			}
		
		
		if ("getCompositeQuery_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String, String[]> map = req.getParameterMap();
			if (map != null) {
				BidItemReportServiceImpl bidItemReportServiceImpl = new BidItemReportServiceImpl();
				List<BidItemReport> BidItReList = bidItemReportServiceImpl.getBidITReportByCompositeQuery(map);
				req.setAttribute("BidItReList", BidItReList);
			} 
			String url = "/front_end/biditemreport/listCompositeQuerybidItRe.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, resp);
		
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer deptid = Integer.valueOf(req.getParameter("deptId"));
				
				/***************************2.開始查詢資料****************************************/
				DepartmentServiceImpl departmentServiceImpl = new DepartmentServiceImpl();
				Department department = departmentServiceImpl.getDepartmentById(deptid);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?deptId="  +department.getDeptId()+
						       "&deptName="  +department.getDeptName();
				String url = "update_dept_input.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, resp);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			
				Integer deptid = null;
				try {
					deptid = Integer.valueOf(req.getParameter("deptid").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("deptId","部門ID請填數字");
				}	
				if (deptid == null) {
					errorMsgs.put("deptId","部門ID: 請勿空白");
				}
				
		
				String deptname = req.getParameter("deptname").trim();
				if (deptname == null || deptname.trim().length() == 0) {
					errorMsgs.put("deptname","部門名稱請勿空白");
				}
		
		   
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/department/update_dept_input.jsp");
					failureView.forward(req, resp);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
						
				DepartmentServiceImpl departmentServiceImpl = new DepartmentServiceImpl();
				Department department = departmentServiceImpl.updateDepartment(deptid, deptname);
								
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("Department", department); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back_end/department/listOneDept.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, resp);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

			Integer deptid = null;
			try {
				deptid = Integer.valueOf(req.getParameter("deptid").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("deptId","部門ID請填數字");
			}	
			if (deptid == null) {
				errorMsgs.put("deptId","部門ID: 請勿空白");
			}
			
	
			String deptname = req.getParameter("deptname").trim();
			if (deptname == null || deptname.trim().length() == 0) {
				errorMsgs.put("deptname","部門名稱請勿空白");
			}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/department/addDept.jsp");
					failureView.forward(req, resp);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				DepartmentServiceImpl departmentServiceImpl = new DepartmentServiceImpl();
				departmentServiceImpl.addDepartment(deptid, deptname);				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back_end/department/listAllDept.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, resp);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer deptid = Integer.valueOf(req.getParameter("deptId"));
				
				/***************************2.開始刪除資料***************************************/
				DepartmentServiceImpl departmentServiceImpl = new DepartmentServiceImpl();
				departmentServiceImpl.deleteDepartment(deptid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back_end/department/listAllDept.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, resp);
		}
	}
	

	
}
