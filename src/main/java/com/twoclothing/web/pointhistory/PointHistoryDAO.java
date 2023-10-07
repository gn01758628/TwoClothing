package com.twoclothing.web.pointhistory;

import java.util.List;

public interface PointHistoryDAO {

    public void insert(PointHistory pointHistory);

    public PointHistory getByPrimaryKey(Integer pointId);

    public List<PointHistory> getAll();

    public List<PointHistory> getAllByMbrId(Integer mbrId);
}
