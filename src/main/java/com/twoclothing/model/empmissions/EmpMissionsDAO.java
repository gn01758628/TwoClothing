package com.twoclothing.model.empmissions;

import java.util.List;

import com.twoclothing.model.empmissions.EmpMissions.CompositeDetail;

public interface EmpMissionsDAO {

	int insert(EmpMissions empMissions);

	List<EmpMissions> getAll();

	EmpMissions getByCompositeKey(Integer empId, Integer permissionId);

	List<EmpMissions> getAllByEmpId(Integer empId);

	List<EmpMissions> getAllByPermissionId(Integer permissionId);

	int update(EmpMissions empMissions);

	int delete(Integer empId, Integer permissionId);
}
