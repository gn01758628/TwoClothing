package com.twoclothing.tonyhsieh.service;

import java.util.List;

import com.twoclothing.model.department.Department;


public interface DepartmentService {

	int delete(Integer deptId);
	
	Department getByPrimaryKey(Integer deptId);
	
	List<Department> getAllDepartment();
	
	Department insert(Integer deptId, String deptName);

	Department update(Integer deptId,String deptName);
	
}
