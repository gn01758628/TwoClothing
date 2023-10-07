package com.twoclothing.web.withdrowrequest;

import java.util.List;

public interface WithdrawRequestDAO {

    public void insert(WithdrawRequest withdrawRequest);

    public WithdrawRequest getByPrimaryKey(Integer wrId);

    public List<WithdrawRequest> getAll();

    public List<WithdrawRequest> getAllByEmpID(Integer empId);

    public void update(Integer wrId);
}
