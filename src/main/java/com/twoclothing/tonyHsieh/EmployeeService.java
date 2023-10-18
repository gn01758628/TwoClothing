package com.twoclothing.tonyHsieh;

import java.util.List;

import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditemimage.BidItemImage;
import com.twoclothing.model.employee.Employee;

public interface EmployeeService {

	void deleteEmployee(Integer empId);
	
	Employee getEmployeeById(Integer empId);
	
	List<Employee> getAllEmployees(int currentPage);
	
	int getPageTotal();
}
