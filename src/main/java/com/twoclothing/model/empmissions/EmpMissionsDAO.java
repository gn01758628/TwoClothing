package com.twoclothing.model.empmissions;

import java.util.List;

import com.twoclothing.model.empmissions.EmpMissions.CompositeDetail;

public interface EmpMissionsDAO {

	void insert(EmpMissions EmpMissions);

	List<EmpMissions> getAll();

	EmpMissions getByCompositeKey(CompositeDetail compositeKey);

	List<EmpMissions> getAllByEmpId(Integer empId);

	List<EmpMissions> getAllByPermissionId(Integer permissionId);

	void update(EmpMissions EmpMissions);

	void delete(Integer empId, Integer permissionId);
}
