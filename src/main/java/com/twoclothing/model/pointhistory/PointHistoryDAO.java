package com.twoclothing.model.pointhistory;

import java.util.List;

public interface PointHistoryDAO {

    int insert(PointHistory pointHistory);

    PointHistory getByPrimaryKey(Integer pointId);

    List<PointHistory> getAll();

    List<PointHistory> getAllByMbrId(Integer mbrId);
}
