package com.twoclothing.model.department;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.twoclothing.model.members.Members;
import com.twoclothing.utils.HibernateUtil;

@Transactional
public class DepartmentHibernateDAO implements DepartmentDAO{

	private SessionFactory factory;

	public DepartmentHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	
	@Override
	public int addDepartment(Department department) {
		// TODO Auto-generated method stub
		Integer deptId = (Integer) getSession().save(department);
		return deptId;
		
	}
	@Override
	public int updateDepartment(Department department) {
		// TODO Auto-generated method stub
		try {
			getSession().merge(department);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}
	@Override
	public int deleteDepartment(Integer deptId) {
		// TODO Auto-generated method stub
		Department department = getSession().get(Department.class, deptId);
		if (department != null) {
			getSession().delete(department);
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
		return getSession().get(Department.class, deptId);
	}
	@Override
	public Department getDepartmentByName(String deptname) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Department", Department.class).list();
	}
	
			
	
}
