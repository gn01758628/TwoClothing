package com.twoclothing.web.empmissions;

import java.util.List;

public interface EmpMissionsDAO {

    void addEmpMissions(EmpMissions EmpMissions);
    void updateEmpMissions(EmpMissions EmpMissions);
    void deleteEmpMissions(Integer empId, Integer permissionId);
    List<EmpMissions> getAllEmpMissionss();
    EmpMissions getEmpMissionsByIds(Integer empId, Integer permissionId);
    List<EmpMissions> getEmpMissionssByEmpId(Integer empId);
    List<EmpMissions> getEmpMissionssByPermissionId(Integer permissionId);
}
