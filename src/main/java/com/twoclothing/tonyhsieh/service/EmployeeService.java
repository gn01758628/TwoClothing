package com.twoclothing.tonyhsieh.service;

import java.util.List;


import com.twoclothing.model.employee.Employee;

public interface EmployeeService {

	int delete(Integer empId);
	
	Employee getByPrimaryKey(Integer empId);
	
	List<Employee> getAll();
	
	Employee insert(Integer deptId, String empName, String phone, String address, String email,
			String pswdHash, Integer empStatus,byte[] avatar);

	Employee update(Integer empId,Integer deptId, String empName, String phone, String address, String email,
			String pswdHash, Integer empStatus,byte[] avatar);
	
}
