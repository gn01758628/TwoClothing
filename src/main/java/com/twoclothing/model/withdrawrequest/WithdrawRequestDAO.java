package com.twoclothing.model.withdrawrequest;

import java.util.List;

public interface WithdrawRequestDAO {

    int insert(WithdrawRequest withdrawRequest);

    WithdrawRequest getByPrimaryKey(Integer wrId);

    List<WithdrawRequest> getAll();

    List<WithdrawRequest> getAllByEmpId(Integer empId);

    List<WithdrawRequest> getAllByMbrId(Integer mbrId);

    List<WithdrawRequest> getAllByReqStatus(Integer reqstatus);

    int update(List<WithdrawRequest> withdrawRequest);

    Integer getBalanceByMbrId(Integer mbrId);

	List<WithdrawRequest> getByStatus();
}
