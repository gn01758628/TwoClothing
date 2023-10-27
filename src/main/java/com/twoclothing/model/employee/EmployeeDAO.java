package com.twoclothing.model.employee;

import java.util.List;

public interface EmployeeDAO {
	// 新增員工資訊
	int insert(Employee employee);

	// 根據ID查詢員工資訊
	Employee getByPrimaryKey(Integer empId);

	// 查詢所有員工資訊
	List<Employee> getAll();

	// 更新員工資訊
	int update(Employee employee);

	// 根據ID刪除員工資訊
	int delete(Integer empId);

	List<Employee> getAll(int currentPage);

	long getTotal();

}
