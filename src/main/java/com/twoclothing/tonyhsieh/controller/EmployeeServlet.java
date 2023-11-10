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
import javax.servlet.http.Part;

import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;

import com.twoclothing.model.employee.Employee;
import com.twoclothing.tonyhsieh.service.EmployeeService;
import com.twoclothing.tonyhsieh.service.EmployeeServiceImpl;
import com.twoclothing.utils.generic.GenericService;

@WebServlet("/back_end/employee/Employee.do")
@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 5*1024*1024,maxRequestSize =5*5*1024*1024 )
public class EmployeeServlet extends HttpServlet {
	
	private GenericService gs;
	
	public void init() throws ServletException {
		this.gs = gs.getInstance();
	}

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
		
		
		if ("get_On_Duty".equals(action)) {
			
			List<Employee> empList = gs.getBy(Employee.class, "empStatus", 0);
			
			req.setAttribute("empList", empList); // 資料庫取出的empVO物件,存入req
			String url = "/back_end/employee/listAllEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllEmp.jsp
			successView.forward(req, resp);
		}
		else if("get_Not_On_Duty".equals(action)) {
			
			List<Employee> empList = gs.getBy(Employee.class, "empStatus", 1);
			
			req.setAttribute("empList", empList); // 資料庫取出的empVO物件,存入req
			String url = "/back_end/employee/listAllEmp.jsp";
			
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllEmp.jsp
			successView.forward(req, resp);
		}
		else if("job_Status_Switch".equals(action)) {
			
			Integer empId = Integer.valueOf(req.getParameter("empId"));
			Employee emp = gs.getByPrimaryKey(Employee.class, empId);
			String url;
			if( 0 == emp.getEmpStatus()) {
				emp.setEmpStatus(1);
				url = "/back_end/employee/Employee.do?action=get_On_Duty";
			}else {
				emp.setEmpStatus(0);
				url = "/back_end/employee/Employee.do?action=get_Not_On_Duty";
			}
			resp.sendRedirect(req.getContextPath() + url);
			return;
		}
		
		
		else if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer empid = Integer.valueOf(req.getParameter("empId"));
				
				/***************************2.開始查詢資料****************************************/
				EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
				Employee employee = employeeServiceImpl.getByPrimaryKey(empid);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?empId="  +employee.getEmpId()+
						       "&address="  +employee.getAddress()+
						       "&email="    +employee.getEmail()+
						       "&pswdHash="    +employee.getPswdHash()+
						       "&phone="+employee.getPhone()+
						       "&empName="    +employee.getEmpName()+
						       "&deptId="   +employee.getDeptId()+
						       "&empStatus=" +employee.getEmpStatus()+
								"&avatar=" +employee.getAvatar();
				String url = "update_emp_input.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, resp);
		}
		
		
		else if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer empid = Integer.valueOf(req.getParameter("empid").trim());
				
				Integer deptid = null;
				try {
					deptid = Integer.valueOf(req.getParameter("deptid").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("deptid","部門請填數字");
				}				
				
				String empname = req.getParameter("empname");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (empname == null || empname.trim().length() == 0) {
					errorMsgs.put("empname","員工姓名: 請勿空白");
				} else if(!empname.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("ename","員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String phone = req.getParameter("phone").trim();
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.put("phone","手機請勿空白");
				}
				
				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.put("address","地址請勿空白");
				}
				
				String email = req.getParameter("email");
				String emailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
				if (!email.trim().matches(emailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("email","EMAIL: 請符合EMAIL格式");
	            }
				
				String pswdhash = req.getParameter("pswdhash").trim();
				if (pswdhash == null || pswdhash.trim().length() == 0) {
					errorMsgs.put("pswdhash","密碼請勿空白");
				}
								
				Integer empstatus = null;
				try {
					empstatus = Integer.valueOf(req.getParameter("empstatus").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("empstatus","狀態請填數字");
				}
						
			      Part image = null;
		            try {
		                Collection<Part> parts = req.getParts();
		                for (Part part : parts) {
		                    if ("image01".equals(part.getName())) image = part;
		                }
		            } catch (IllegalArgumentException e){
		                e.printStackTrace();
		            }
		            EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
		            byte[] avatar = null;
		            int length = image.getInputStream().available() ;
		            if(length != 0){
		                try (InputStream inputStream = image.getInputStream()){
		                	avatar = inputStream.readAllBytes();
		                    inputStream.close();
		                } catch (IOException e){
		                    e.printStackTrace();
		                }
		            }else {
		            	avatar=employeeServiceImpl.getByPrimaryKey(empid).getAvatar();
		            }
		            
		       
	   
//				Double sal = null;
//				try {
//					sal = Double.valueOf(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.put("sal","薪水請填數字");
//				}
//								
//				Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/employee/update_emp_input.jsp");
					failureView.forward(req, resp);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				Employee employee = employeeServiceImpl.update(empid, deptid, empname, phone, address, email, pswdhash, empstatus,avatar);

				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("employee", employee); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back_end/employee/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, resp);
		}

		else if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

			
				Integer deptid = null;
				try {
					deptid = Integer.valueOf(req.getParameter("deptid").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("deptid","部門請填數字");
				}		
			
				String empname = req.getParameter("empname");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (empname == null || empname.trim().length() == 0) {
					errorMsgs.put("empname","員工姓名: 請勿空白");
				} else if(!empname.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("ename","員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String phone = req.getParameter("phone").trim();
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.put("phone","手機請勿空白");
				}
												
				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.put("address","地址請勿空白");
				}
				
				String email = req.getParameter("email");
				String emailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
				if (!email.trim().matches(emailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("email","EMAIL: 請符合EMAIL格式");
	            }
			
				String pswdhash = req.getParameter("pswdhash").trim();
				if (pswdhash == null || pswdhash.trim().length() == 0) {
					errorMsgs.put("pswdhash","密碼請勿空白");
				}
								
				Part image = null;
	            try {
	            	image = req.getPart("avatar");
	            } catch (IllegalArgumentException e){
	                e.printStackTrace();
	            }
	            byte[] avatar = null;
	            if(image != null){
	                try (InputStream inputStream = image.getInputStream()){
	                	avatar = inputStream.readAllBytes();
	                    inputStream.close();
	                } catch (IOException e){
	                    e.printStackTrace();
	                }
	            }

//				Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/employee/addEmp.jsp");
					failureView.forward(req, resp);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
				employeeServiceImpl.insert(deptid, empname, phone, address, email, pswdhash,0,avatar);				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, resp);				
		}
		
//		
//		else {
//			EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
//			List<Employee> empList = employeeServiceImpl.getAll();
//			
//			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//			req.setAttribute("empList", empList); // 資料庫取出的empVO物件,存入req
//			String url = "/back_end/employee/listAllEmp.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//			successView.forward(req, resp);
//		}
        
	}
	

	
}
