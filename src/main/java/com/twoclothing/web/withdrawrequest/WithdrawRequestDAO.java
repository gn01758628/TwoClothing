package com.twoclothing.web.withdrawrequest;

import java.util.List;

public interface WithdrawRequestDAO {

    void insert(WithdrawRequest withdrawRequest);

    WithdrawRequest getByPrimaryKey(Integer wrId);

    List<WithdrawRequest> getAll();

    List<WithdrawRequest> getAllByEmpID(Integer empId);

    List<WithdrawRequest> getAllByMbrid(Integer mbrId);

    List<WithdrawRequest> getAllByReqStatus(Integer reqstatus);

    void update(WithdrawRequest withdrawRequest);
}
