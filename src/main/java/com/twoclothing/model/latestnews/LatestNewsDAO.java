package com.twoclothing.model.latestnews;

import java.util.List;
import java.sql.Timestamp;

public interface LatestNewsDAO {
    void insert(LatestNews latestNews);
    LatestNews getByPrimaryKey(Integer newsId);
    List<LatestNews> getAll();
    List<LatestNews> getAllByEmpId(Integer empId);
    List<LatestNews> getAllByTimestamp(Timestamp timestamp); // 新增一个根据时间戳查询的方法
    void update(LatestNews latestNews);
    void delete(Integer newsId);
}
