package com.twoclothing.tonyHsieh;


import java.util.List;

import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.employee.EmployeeDAO;
import com.twoclothing.model.employee.EmployeeHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO;
	
	public EmployeeServiceImpl() {
		employeeDAO = new EmployeeHibernateDAO(HibernateUtil.getSessionFactory());
	}

	@Override
	public void deleteEmployee(Integer empId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getEmployeeById(Integer empId) {
		// TODO Auto-generated method stub
		return employeeDAO.getEmployeeById(empId);
	}

	@Override
	public List<Employee> getAllEmployees(int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPageTotal() {
		// TODO Auto-generated method stub
		long total = employeeDAO.getTotal();
		// 計算Emp數量每頁3筆的話總共有幾頁
		int pageQty = (int)(total % 4 == 0 ? (total / 4) : (total / 4 + 1));
		return pageQty;
	}

	
	
   
   
}
