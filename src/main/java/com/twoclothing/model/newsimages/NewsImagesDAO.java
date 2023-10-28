package com.twoclothing.model.newsimages;

import java.util.List;

public interface NewsImagesDAO {
	void insert(NewsImages newsImage);

	NewsImages getByPrimaryKey(Integer imageId);

	List<NewsImages> getAllByNewsId(Integer newsId);

	void update(NewsImages newsImage);

	void delete(Integer imageId);
}
