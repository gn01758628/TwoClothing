package com.twoclothing.model.employee;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.twoclothing.utils.HibernateUtil;

@Transactional
public class EmployeeHibernateDAO implements EmployeeDAO{

	private SessionFactory factory;

	public EmployeeHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public Employee getByPrimaryKey(Integer empId) {
		// TODO Auto-generated method stub
		return getSession().get(Employee.class, empId);
		}

	@Override
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Employee", Employee.class).list();
//		List<Employee> list = null;
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			Query<Employee> query = session.createQuery("from Employee", Employee.class);
//			list = query.getResultList();
//			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}
//		return list;
//		
			}
	@Override
	public int insert(Employee employee) {
		// TODO Auto-generated method stub
		// 回傳給 service 剛新增成功的自增主鍵值
		Integer empId = (Integer) getSession().save(employee);
		return empId;
		
	}
	@Override
	public int update(Employee employee) {
		// TODO Auto-generated method stub
		try {
			getSession().merge(employee);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}
	@Override
	public int delete(Integer empId) {
		// TODO Auto-generated method stub
		Employee emp = getSession().get(Employee.class, empId);
		if (emp != null) {
			getSession().delete(emp);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}
	@Override
	public List<Employee> getAll(int currentPage) {
		// TODO Auto-generated method stub
		int first = (currentPage - 1) * 4;
		return getSession().createQuery("from Employee", Employee.class)
				.setFirstResult(first)
				.setMaxResults(4)
				.list();
	}
	@Override
	public long getTotal() {
		// TODO Auto-generated method stub
		return getSession().createQuery("select count(*) from Employee", Long.class).uniqueResult();
	}

	
	
		
		
		
			
	
}
