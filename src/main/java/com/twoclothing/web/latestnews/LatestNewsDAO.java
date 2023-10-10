package com.twoclothing.web.latestnews;

import java.util.List;
import java.sql.Timestamp;

public interface LatestNewsDAO {
    void addLatestNews(LatestNews latestNews);
    void updateLatestNews(LatestNews latestNews);
    void deleteLatestNews(Integer newsId);
    LatestNews getLatestNewsById(Integer newsId);
    List<LatestNews> getAllLatestNews();
    List<LatestNews> getLatestNewsByEmployeeId(Integer empId);
    List<LatestNews> getLatestNewsByTimestamp(Timestamp timestamp); // 新增一个根据时间戳查询的方法
}
