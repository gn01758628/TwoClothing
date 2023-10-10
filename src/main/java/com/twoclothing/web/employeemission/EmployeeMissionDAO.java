package com.twoclothing.web.employeemission;

import java.util.List;

public interface EmployeeMissionDAO {
    void addEmployeeMission(EmployeeMission employeeMission);
    void updateEmployeeMission(EmployeeMission employeeMission);
    void deleteEmployeeMission(Integer empId, Integer permissionId);
    List<EmployeeMission> getAllEmployeeMissions();
    EmployeeMission getEmployeeMissionByIds(Integer empId, Integer permissionId);
    List<EmployeeMission> getEmployeeMissionsByEmpId(Integer empId);
    List<EmployeeMission> getEmployeeMissionsByPermissionId(Integer permissionId);
}
