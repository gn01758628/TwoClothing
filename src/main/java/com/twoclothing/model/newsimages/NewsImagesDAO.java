package com.twoclothing.model.newsimages;

import java.util.List;

public interface NewsImagesDAO {
    void addNewsImage(NewsImages newsImage);
    void updateNewsImage(NewsImages newsImage);
    void deleteNewsImage(Integer imageId);
    NewsImages getNewsImageById(Integer imageId);
    List<NewsImages> getNewsImagesByNewsId(Integer newsId);
}
