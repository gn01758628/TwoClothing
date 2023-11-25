package com.twoclothing.model.withdrawrequest;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.twoclothing.model.balancehistory.BalanceHistory;

public class WithdrawRequestHibernateDAO implements WithdrawRequestDAO {

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
		return getSession().get(WithdrawRequest.class, wrId);
	}

	@Override
	public List<WithdrawRequest> getAll() {
		return getSession().createQuery("from WithdrawRequest order by wrId desc", WithdrawRequest.class).list();

	}

	@Override
	public List<WithdrawRequest> getAllByEmpId(Integer empId) {
		return getSession().createQuery("from WithdrawRequest where empId = :empId", WithdrawRequest.class)
				.setParameter("empId", empId).list();

	}

	@Override
	public List<WithdrawRequest> getAllByMbrId(Integer mbrId) {
		return getSession()
				.createQuery("from WithdrawRequest where mbrId = :mbrId order by wrId desc", WithdrawRequest.class)
				.setParameter("mbrId", mbrId).list();

	}

	@Override
	public List<WithdrawRequest> getAllByReqStatus(Integer reqstatus) {
		return getSession().createQuery("from WithdrawRequest where reqstatus = :reqstatus", WithdrawRequest.class)
				.setParameter("reqstatus", reqstatus).list();

	}
	
	@Override
	public List<WithdrawRequest> getByMbrReqing(Integer mbrId) {
	    return getSession()
	            .createQuery("from WithdrawRequest where mbrId = :mbrId and reqstatus = 0", WithdrawRequest.class)
	            .setParameter("mbrId", mbrId)
	            .list();
	}

	@Override
	public int update(List<WithdrawRequest> withdrawRequest) {
		try {
	        for (WithdrawRequest wr : withdrawRequest) {
	            getSession().update(wr);
	        }
	            return 1;
		}catch(Exception e) {
			return -1;
		}
		
	}

	@Override
	public Integer getBalanceByMbrId(Integer mbrId) {
		return (Integer) getSession().createQuery("select balance from Members  where mbrId = :mbrId")
				.setParameter("mbrId", mbrId).uniqueResult();
	}

	@Override
	public List<WithdrawRequest> getByStatus() {
		return getSession()
				.createQuery("from WithdrawRequest where reqStatus = 0 order by wrId desc", WithdrawRequest.class)
				.list();

	}
}
