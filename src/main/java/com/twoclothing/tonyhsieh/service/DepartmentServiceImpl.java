package com.twoclothing.tonyhsieh.service;


import java.util.List;

import com.twoclothing.model.department.Department;
import com.twoclothing.model.department.DepartmentDAO;
import com.twoclothing.model.department.DepartmentHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentDAO departmentDAO;
	
	public DepartmentServiceImpl() {
		departmentDAO = new DepartmentHibernateDAO(HibernateUtil.getSessionFactory());
		}

	@Override
	public int deleteDepartment(Integer deptId) {
		// TODO Auto-generated method stub
		Department depart = departmentDAO.getDepartmentById(deptId);
		if (depart != null) {
			departmentDAO.deleteDepartment(deptId);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}

	@Override
	public Department getDepartmentById(Integer deptId) {
		// TODO Auto-generated method stub
		return departmentDAO.getDepartmentById(deptId);
	}


	@Override
	public List<Department> getAllDepartment() {
		// TODO Auto-generated method stub
		return departmentDAO.getAllDepartments();
	}

	@Override
	public Department addDepartment(Integer deptId, String deptName) {
		// TODO Auto-generated method stub
		Department department = new Department();
		department.setDeptId(deptId);
		department.setDeptName(deptName);
		departmentDAO.addDepartment(department);
		return department;
	
	}



	@Override
	public Department updateDepartment(Integer deptId, String deptName) {
		// TODO Auto-generated method stub
		Department department = new Department();
		department.setDeptId(deptId);
		department.setDeptName(deptName);
		departmentDAO.updateDepartment(department);
		return department;
	
	}

  
   
}
