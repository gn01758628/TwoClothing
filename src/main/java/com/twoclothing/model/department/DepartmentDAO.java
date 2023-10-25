package com.twoclothing.model.department;

import java.util.List;

public interface DepartmentDAO {
	int addDepartment(Department department);
	int updateDepartment(Department department);
	int deleteDepartment(Integer deptId);
    Department getDepartmentById(Integer deptId);
    Department getDepartmentByName(String deptname);
    List<Department> getAllDepartments();
}
