package com.twoclothing.tonyhsieh;

import java.util.List;


import com.twoclothing.model.employee.Employee;

public interface EmployeeService {

	int deleteEmployee(Integer empId);
	
	Employee getEmployeeById(Integer empId);
	
	List<Employee> getAllEmployees();
	
	Employee addEmployee(Integer deptId, String empName, String phone, String address, String email,
			String pswdHash, Integer empStatus);

	Employee updateEmployee(Integer empId,Integer deptId, String empName, String phone, String address, String email,
			String pswdHash, Integer empStatus);
	
}
