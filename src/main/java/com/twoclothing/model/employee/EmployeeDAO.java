package com.twoclothing.model.employee;

import java.util.List;

public interface EmployeeDAO {

    // 根據ID查詢員工資訊
    Employee getEmployeeById(Integer empId);

    // 查詢所有員工資訊
    List<Employee> getAllEmployees();

    // 新增員工資訊
    int addEmployee(Employee employee);

    // 更新員工資訊
    int updateEmployee(Employee employee);

    // 根據ID刪除員工資訊
    int deleteEmployee(Integer empId);
}
