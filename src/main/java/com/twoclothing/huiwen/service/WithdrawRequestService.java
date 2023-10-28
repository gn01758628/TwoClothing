package com.twoclothing.huiwen.service;

import java.util.List;

import com.twoclothing.model.withdrawrequest.WithdrawRequest;

public interface WithdrawRequestService {
	
    int addWR(WithdrawRequest withdrawRequest);

    WithdrawRequest getWRById(Integer wrId);

    List<WithdrawRequest> getAllWR();

    List<WithdrawRequest> getAllWRByEmpId(Integer empId);

    List<WithdrawRequest> getAllWRByMbrId(Integer mbrId);

    List<WithdrawRequest> getAllWRByReqStatus(Integer reqstatus);

    int updateWR(WithdrawRequest withdrawRequest);
}
