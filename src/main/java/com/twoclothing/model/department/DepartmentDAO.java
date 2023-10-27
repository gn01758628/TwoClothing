package com.twoclothing.model.department;

import java.util.List;

public interface DepartmentDAO {
	int insert(Department department);

	Department getByPrimaryKey(Integer deptId);

	List<Department> getAll();

	Department getByDeptName(String deptname);

	int update(Department department);

	int delete(Integer deptId);

}
