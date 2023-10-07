package com.twoclothing.web.balancehistory;

import java.util.List;

public interface BalanceHistoryDAO {

    public void insert(BalanceHistory balanceHistory);

    public BalanceHistory getByPrimaryKey(Integer balanceId);

    public List<BalanceHistory> getAll();

    public List<BalanceHistory> getAllByMbrId(Integer mbrId);

}
