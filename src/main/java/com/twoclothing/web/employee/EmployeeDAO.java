package com.twoclothing.web.employee;

import java.util.List;

public interface EmployeeDAO {

    // 根據ID查詢員工資訊
    Employee getEmployeeById(Integer empId);

    // 查詢所有員工資訊
    List<Employee> getAllEmployees();

    // 新增員工資訊
    void addEmployee(Employee employee);

    // 更新員工資訊
    void updateEmployee(Employee employee);

    // 根據ID刪除員工資訊
    void deleteEmployee(Integer empId);
}
