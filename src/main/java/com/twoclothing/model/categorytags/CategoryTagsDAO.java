package com.twoclothing.model.categorytags;

import java.util.List;

public interface CategoryTagsDAO {

    /**
     * @return PrimaryKey
     */
    int insert(CategoryTags categoryTags);

    CategoryTags getByPrimaryKey(Integer tagId);

    List<CategoryTags> getAll();

    List<CategoryTags> getAllByEmpId(Integer empId);

    List<Integer> getTagIdsWithoutChildren();

    /**
     * @return 所有的子孫標籤
     */
    List<CategoryTags> getAllSubByPrimaryKey(Integer tagId);

    List<CategoryTags> getAllHaveSubTags();

    /**
     * @return 修改是否成功
     */
    boolean update(CategoryTags categoryTags);
}
