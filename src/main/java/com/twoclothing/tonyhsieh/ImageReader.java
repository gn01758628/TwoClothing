package com.twoclothing.tonyhsieh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.employee.EmployeeHibernateDAO;
import com.twoclothing.utils.HibernateUtil;
@WebServlet("/ReadIMG")
public class ImageReader extends HttpServlet{

	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 		response.setContentType("image/jpeg");
		ServletOutputStream outputStream = response.getOutputStream();
		try {
			int empid = Integer.parseInt(request.getParameter("empId"));

			EmployeeHibernateDAO employeeHibernateDAO = new EmployeeHibernateDAO(sessionFactory);
			Employee employee = employeeHibernateDAO.getEmployeeById(empid);
			if (employee != null) {
				byte[] avatar = employee.getAvatar();
				System.out.println("avatar");
				outputStream.write(avatar);
				outputStream.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
