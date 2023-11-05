package com.twoclothing.model.empmissions;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.twoclothing.model.aproduct.itemimage.ItemImage;
import com.twoclothing.model.aproduct.itemtracking.ItemTracking;
import com.twoclothing.model.empmissions.EmpMissions.CompositeDetail;
import com.twoclothing.utils.HibernateUtil;

@Transactional
public class EmpMissionsHibernateDAO implements EmpMissionsDAO{

	private SessionFactory factory;

	public EmpMissionsHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	
	@Override
	public List<EmpMissions> getAll() {
		// TODO Auto-generated method stub
//		return getSession().createQuery("from EmpMissions", EmpMissions.class).list();
//	}
	List<EmpMissions> list = null;
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	try {
		session.beginTransaction();
		Query<EmpMissions> query = session.createQuery("from EmpMissions", EmpMissions.class);
		list = query.getResultList();
		session.getTransaction().commit();
	} catch (RuntimeException ex) {
		session.getTransaction().rollback();
		throw ex;
	}
	return list;
	}
	
	
	
	@Override
	public EmpMissions getByCompositeKey(Integer empId, Integer permissionId) {
		// TODO Auto-generated method stub
		EmpMissions.CompositeDetail compositeKey = new EmpMissions.CompositeDetail(empId, permissionId);
		return getSession().get(EmpMissions.class, compositeKey);
	}
	@Override
	public List<EmpMissions> getAllByEmpId(Integer empId) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from EmpMissions where empId = :empId order by empId", EmpMissions.class)
                .setParameter("empId", empId)
                .list();
	}
	@Override
	public List<EmpMissions> getAllByPermissionId(Integer permissionId) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from EmpMissions where permissionId = :permissionId order by permissionId", EmpMissions.class)
                .setParameter("permissionId", permissionId)
                .list();
	}

	@Override
	public int insert(EmpMissions empMissions) {
		// TODO Auto-generated method stub
		try {
			getSession().save(empMissions);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}
	@Override
	public int update(EmpMissions empMissions) {
		// TODO Auto-generated method stub
		try {
			getSession().merge(empMissions);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}
	@Override
	public int delete(Integer empId, Integer permissionId) {
		// TODO Auto-generated method stub
		EmpMissions.CompositeDetail compositeKey = new EmpMissions.CompositeDetail(empId, permissionId);
		EmpMissions empMissions = getSession().get(EmpMissions.class, compositeKey);
		if (empMissions != null) {
			getSession().delete(empMissions);
			return 1;
		} else {
			return -1;
		}
	}

	
	
		
		
		
			
	
}
