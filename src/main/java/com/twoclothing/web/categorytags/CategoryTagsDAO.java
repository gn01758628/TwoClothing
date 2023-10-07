package com.twoclothing.web.categorytags;

import java.util.List;

public interface CategoryTagsDAO {

    public void insert(CategoryTags categoryTags);

    public CategoryTags getByPrimaryKey(Integer tagId);

    public List<CategoryTags> getAll();

    public List<CategoryTags> getAllByEmpId(Integer empId);

    // 根據主鍵查詢其所有的子標籤
    public List<CategoryTags> getAllSubByPrimaryKey(Integer tagId);

    public void update(Integer tagId);
}
