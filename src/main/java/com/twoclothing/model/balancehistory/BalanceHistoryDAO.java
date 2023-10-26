package com.twoclothing.model.balancehistory;

import java.util.List;

public interface BalanceHistoryDAO {

    int insert(BalanceHistory balanceHistory);

    BalanceHistory getByPrimaryKey(Integer balanceId);

    List<BalanceHistory> getAll();

    List<BalanceHistory> getAllByMbrId(Integer mbrId);

}
