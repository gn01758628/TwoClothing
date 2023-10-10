package com.twoclothing.web.newsimage;

import java.util.List;

public interface NewsImageDAO {
    void addNewsImage(NewsImage newsImage);
    void updateNewsImage(NewsImage newsImage);
    void deleteNewsImage(Integer imageId);
    NewsImage getNewsImageById(Integer imageId);
    List<NewsImage> getNewsImagesByNewsId(Integer newsId);
}
