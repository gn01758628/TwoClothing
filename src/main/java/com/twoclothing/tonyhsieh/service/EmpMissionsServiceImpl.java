package com.twoclothing.tonyhsieh.service;




import java.util.List;

import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.empmissions.EmpMissions;
import com.twoclothing.model.empmissions.EmpMissionsDAO;
import com.twoclothing.model.empmissions.EmpMissionsHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

public class EmpMissionsServiceImpl implements EmpMissionsService {

	private EmpMissionsDAO empMissionsDAO;
	
	public EmpMissionsServiceImpl() {
		empMissionsDAO = new EmpMissionsHibernateDAO(HibernateUtil.getSessionFactory());
		}

	@Override
	public EmpMissions getByCompositeKey(Integer empId, Integer permissionId) {
		// TODO Auto-generated method stub
		return empMissionsDAO.getByCompositeKey(empId, permissionId);
	}


	@Override
	public List<EmpMissions> getAllByEmpId(Integer empId) {
		// TODO Auto-generated method stub
		return empMissionsDAO.getAllByEmpId(empId);
	}


	@Override
	public List<EmpMissions> getAllByPermissionId(Integer permissionId) {
		// TODO Auto-generated method stub
		return empMissionsDAO.getAllByPermissionId(permissionId);
	}

	@Override
	public EmpMissions insert(Integer empId , Integer permissionId) {
		// TODO Auto-generated method stub
		EmpMissions.CompositeDetail compositeKey =new EmpMissions.CompositeDetail(empId, permissionId);	
		EmpMissions empMissions = new EmpMissions(compositeKey);
		empMissions.setCompositeKey(compositeKey);
		empMissionsDAO.insert(empMissions);
		return empMissions;
	}



	@Override
	public List<EmpMissions> getAll() {
		// TODO Auto-generated method stub
		return empMissionsDAO.getAll();
	}



	@Override
	public EmpMissions update(Integer empId , Integer permissionId) {
		// TODO Auto-generated method stub
		EmpMissions.CompositeDetail compositeKey =new EmpMissions.CompositeDetail(empId, permissionId);	
		EmpMissions empMissions = new EmpMissions(compositeKey);
		empMissions.setCompositeKey(compositeKey);
		empMissionsDAO.update(empMissions);
		return empMissions;
		
		
		
	}



	@Override
	public int delete(Integer empId, Integer permissionId) {
		// TODO Auto-generated method stub
		return empMissionsDAO.delete(empId, permissionId);
	}





	
	





  
   
}
