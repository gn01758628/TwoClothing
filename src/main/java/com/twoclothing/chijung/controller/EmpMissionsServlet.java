package com.twoclothing.chijung.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.ArrayList;
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
import com.twoclothing.model.permissions.Permissions;
import com.twoclothing.tonyhsieh.service.BidItemReportService;
import com.twoclothing.tonyhsieh.service.BidItemReportServiceImpl;
import com.twoclothing.tonyhsieh.service.DepartmentService;
import com.twoclothing.tonyhsieh.service.DepartmentServiceImpl;
import com.twoclothing.tonyhsieh.service.EmpMissionsService;
import com.twoclothing.tonyhsieh.service.EmpMissionsServiceImpl;
import com.twoclothing.tonyhsieh.service.EmployeeServiceImpl;
import com.twoclothing.utils.generic.GenericService;


@WebServlet("/back_end/empmissions/EmpMissions.do")
@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 5*1024*1024,maxRequestSize =5*5*1024*1024 )
public class EmpMissionsServlet extends HttpServlet {
	
	GenericService gs = GenericService.getInstance();

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
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			Integer empid = Integer.valueOf(req.getParameter("empId").trim());
			
			StringBuilder emStr =new StringBuilder("00000000");
			
			for( String entity : req.getParameterValues("empMissionsList[]")) {
				int position = Integer.parseInt(entity)-1;
				emStr.setCharAt(position, '1');
			}
			
			Employee emp = gs.getByPrimaryKey(Employee.class, empid);
			emp.setEmpMissions(emStr.toString());
			
			gs.update(emp);
			
			res.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("更新成功");
			return;
		}
		
	   	List<Employee> emplist = gs.getBy(Employee.class, "empStatus", 0);
	    req.setAttribute("emplist",emplist);
	    List<Permissions> pmlist = gs.getAll(Permissions.class);
	    req.setAttribute("pmlist",pmlist);
	    
	    String url = "/back_end/empmissions/listAllempmis.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
		successView.forward(req, res);
	
	}
	

	
}
