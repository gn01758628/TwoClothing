package com.twoclothing.model.withdrawrequest;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.twoclothing.model.balancehistory.BalanceHistory;

public class WithdrawRequestHibernateDAO implements WithdrawRequestDAO{
	
	private SessionFactory factory;

	public WithdrawRequestHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int insert(WithdrawRequest withdrawRequest) {
		Integer wrId = (Integer) getSession().save(withdrawRequest);
		return wrId;
		
	}

	@Override
	public WithdrawRequest getByPrimaryKey(Integer wrId) {
		System.out.println(getSession().get(WithdrawRequest.class, wrId));
		return getSession().get(WithdrawRequest.class, wrId);
	}

	@Override
	public List<WithdrawRequest> getAll() {
		return getSession().createQuery("from WithdrawRequest", WithdrawRequest.class).list();

	}

	@Override
	public List<WithdrawRequest> getAllByEmpId(Integer empId) {
		return getSession().createQuery("from WithdrawRequest where empId = :empId", WithdrawRequest.class).setParameter("empId", empId).list();

	}

	@Override
	public List<WithdrawRequest> getAllByMbrId(Integer mbrId) {
		return getSession().createQuery("from WithdrawRequest where mbrId = :mbrId", WithdrawRequest.class).setParameter("mbrId", mbrId).list();

	}

	@Override
	public List<WithdrawRequest> getAllByReqStatus(Integer reqstatus) {
		return getSession().createQuery("from WithdrawRequest where reqstatus = :reqstatus", WithdrawRequest.class).setParameter("reqstatus", reqstatus).list();

	}

	@Override
	public int update(WithdrawRequest withdrawRequest) {
		try {
			getSession().update(withdrawRequest);
			return 1;
		}catch(Exception e) {
			return -1;
		}
	}

}
