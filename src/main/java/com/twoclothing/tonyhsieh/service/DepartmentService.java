package com.twoclothing.tonyhsieh.service;

import java.util.List;

import com.twoclothing.model.department.Department;


public interface DepartmentService {

	int deleteDepartment(Integer deptId);
	
	Department getDepartmentById(Integer deptId);
	
	List<Department> getAllDepartment();
	
	Department addDepartment(Integer deptId, String deptName);

	Department updateDepartment(Integer deptId,String deptName);
	
}
