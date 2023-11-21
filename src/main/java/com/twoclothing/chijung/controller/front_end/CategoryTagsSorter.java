package com.twoclothing.chijung.controller.front_end;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.twoclothing.model.categorytags.CategoryTags;

public class CategoryTagsSorter {

    public static List<CategoryTags> sortCategoryTags(List<CategoryTags> categoryTagsList) {
        for (CategoryTags categoryTags : categoryTagsList) {
            if (categoryTags.getSuperTagId() == null) {
                categoryTags.setSuperTagId(-1);
            }
        }

        List<CategoryTags> sortedList = new ArrayList<>();
        buildCategoryTree(sortedList, categoryTagsList, -1);

        return sortedList;
    }

    private static void buildCategoryTree(List<CategoryTags> sortedList, List<CategoryTags> categoryTagsList, int parentId) {
        Map<Integer, List<CategoryTags>> parentToChildrenMap = new HashMap<>();

        // 將標籤按照父標籤分組
        for (CategoryTags category : categoryTagsList) {
            parentToChildrenMap.computeIfAbsent(category.getSuperTagId(), k -> new ArrayList<>()).add(category);
        }

        buildCategoryTreeRecursive(sortedList, parentToChildrenMap, parentId);
    }

    private static void buildCategoryTreeRecursive(List<CategoryTags> sortedList, Map<Integer, List<CategoryTags>> parentToChildrenMap, int parentId) {
        List<CategoryTags> children = parentToChildrenMap.get(parentId);

        if (children != null) {
            // 排序子標籤，這部分可以根據實際需求修改
            children.sort(Comparator.comparingInt(CategoryTags::getTagId));

            for (CategoryTags category : children) {
                sortedList.add(category);

                // 遞迴處理子標籤
                buildCategoryTreeRecursive(sortedList, parentToChildrenMap, category.getTagId());
            }
        }
    }
}
