package com.twoclothing.tonyhsieh;


import java.util.List;

import com.twoclothing.model.aproduct.item.Item;
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
	public int addEmployee(Integer empId,Integer deptId,String empName,String phone,String address,String email,String pswdHash, Integer empStatus) {
		// TODO Auto-generated method stub
		
		Employee employee = new Employee();		
		employee.setEmpId(empId);
		employee.setDeptId(deptId);
		employee.setEmpName(empName);
		employee.setPhone(phone);
		employee.setAddress(address);
		employee.setEmail(email);
		employee.setPswdHash(pswdHash);
		employee.setEmpStatus(empStatus);
//		employee.setAvatar(null);
		employeeDAO.addEmployee(employee);
	
		return employeeDAO.addEmployee(employee);
	
	}

	
	
	
	@Override
	public	int updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeDAO.updateEmployee(employee);
	}

	

	
	
   
   
}