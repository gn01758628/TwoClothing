package com.twoclothing.huiwen.service;

import java.util.List;

import com.twoclothing.model.withdrawrequest.WithdrawRequest;
import com.twoclothing.model.withdrawrequest.WithdrawRequestHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

public class WithdrawRequestServiceImpl implements WithdrawRequestService{
	private WithdrawRequestHibernateDAO WRDAO;
	
	public WithdrawRequestServiceImpl() {
		WRDAO = new WithdrawRequestHibernateDAO(HibernateUtil.getSessionFactory());

	}
	
	@Override
	public int addWR(WithdrawRequest withdrawRequest) {
		return WRDAO.insert(withdrawRequest);
	}

	@Override
	public WithdrawRequest getWRById(Integer wrId) {
		return WRDAO.getByPrimaryKey(wrId);

	}

	@Override
	public List<WithdrawRequest> getAllWR() {
		return WRDAO.getAll();
	}

	@Override
	public List<WithdrawRequest> getAllWRByEmpId(Integer empId) {
		return WRDAO.getAllByEmpId(empId);
	}

	@Override
	public List<WithdrawRequest> getAllWRByMbrId(Integer mbrId) {
		return WRDAO.getAllByMbrId(mbrId);

	}

	@Override
	public List<WithdrawRequest> getAllWRByReqStatus(Integer reqstatus) {
		return WRDAO.getAllByReqStatus(reqstatus);

	}
	@Override
	public List<WithdrawRequest> getByMbrReqing(Integer mbrId) {
		return WRDAO.getByMbrReqing(mbrId);
		
	}

	@Override
	public int updateWR(List<WithdrawRequest> withdrawRequest) {
		return WRDAO.update(withdrawRequest);
	}

	@Override
	public Integer getBalanceByMbrId(Integer mbrId) {
		return WRDAO.getBalanceByMbrId(mbrId);
	}
	
	@Override
	public List<WithdrawRequest> getByStatus() {
		return WRDAO.getByStatus();
	}

}
