package com.twoclothing.web.balancehistory;

import java.util.List;

public interface BalanceHistoryDAO {

    void insert(BalanceHistory balanceHistory);

    BalanceHistory getByPrimaryKey(Integer balanceId);

    List<BalanceHistory> getAll();

    List<BalanceHistory> getAllByMbrId(Integer mbrId);

}
