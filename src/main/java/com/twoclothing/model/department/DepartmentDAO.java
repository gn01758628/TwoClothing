package com.twoclothing.model.department;

import java.util.List;

public interface DepartmentDAO {
    void addDepartment(Department department);
    void updateDepartment(Department department);
    void deleteDepartment(int deptId);
    Department getDepartmentById(int deptId);
    Department getDepartmentByName(String deptname);
    List<Department> getAllDepartments();
}
