package com.twoclothing.tonyhsieh;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.model.employee.Employee;


@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	
	private EmployeeService employeeService;

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
		String forwardPath = "";
		switch (action) {
			case "getAll":
				forwardPath = getAllEmps(req, resp);
				break;
			default:
				forwardPath = "/index.jsp";
		}
		
		resp.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, resp);
	}

	private String getAllEmps(HttpServletRequest req, HttpServletResponse resp) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		
		List<Employee> empList = employeeService.getAllEmployees(currentPage);

		if (req.getSession().getAttribute("empPageQty") == null) {
			int empPageQty = employeeService.getPageTotal();
			req.getSession().setAttribute("empPageQty", empPageQty);
		}
		
		req.setAttribute("empList", empList);
		req.setAttribute("currentPage", currentPage);
		
		return "/emp/listAllEmps.jsp";
	}
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		employeeService = new EmployeeServiceImpl();
	}
	
	
}
