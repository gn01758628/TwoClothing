package com.twoclothing.tonyhsieh.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.empmissions.EmpMissions;
import com.twoclothing.model.abid.biditemreport.BidItemReport;


public interface EmpMissionsService {

	EmpMissions insert(Integer empId , Integer permissionId);

	List<EmpMissions> getAll();

	EmpMissions getByCompositeKey(Integer empId, Integer permissionId);

	List<EmpMissions> getAllByEmpId(Integer empId);

	List<EmpMissions> getAllByPermissionId(Integer permissionId);

	EmpMissions update(Integer empId , Integer permissionId);

	int delete(Integer empId, Integer permissionId);
	
}
