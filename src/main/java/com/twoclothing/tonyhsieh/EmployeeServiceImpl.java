package com.twoclothing.tonyhsieh;


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
	public int deleteEmployee(Integer empId) {
		// TODO Auto-generated method stub
		Employee emp = employeeDAO.getEmployeeById(empId);
		if (emp != null) {
			employeeDAO.deleteEmployee(empId);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}





	@Override
	public Employee getEmployeeById(Integer empId) {
		// TODO Auto-generated method stub
		return employeeDAO.getEmployeeById(empId);
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeDAO.getAllEmployees() ;
	}

	@Override
	public int addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		// 回傳給 service 剛新增成功的自增主鍵值
		return employeeDAO.addEmployee(employee);
	
	}

	@Override
	public int updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeDAO.updateEmployee(employee);
	}

	

	
	
   
   
}
