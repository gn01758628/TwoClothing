package com.twoclothing.tonyhsieh;

import java.util.List;

import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditemimage.BidItemImage;
import com.twoclothing.model.employee.Employee;

public interface EmployeeService {

	int deleteEmployee(Integer empId);
	
	Employee getEmployeeById(Integer empId);
	
	List<Employee> getAllEmployees();
	
	int updateEmployee(Employee employee);

	int addEmployee(Integer empId, Integer deptId, String empName, String phone, String address, String email,
			String pswdHash, Integer empStatus);
	
}
