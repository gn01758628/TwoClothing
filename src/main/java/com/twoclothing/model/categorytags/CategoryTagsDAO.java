package com.twoclothing.model.categorytags;

import java.util.List;

public interface CategoryTagsDAO {

    void insert(CategoryTags categoryTags);

    CategoryTags getByPrimaryKey(Integer tagId);

    List<CategoryTags> getAll();

    List<CategoryTags> getAllByEmpId(Integer empId);

    // 根據主鍵查詢其所有的子標籤
    List<CategoryTags> getAllSubByPrimaryKey(Integer tagId);

    void update(CategoryTags categoryTags);
}
