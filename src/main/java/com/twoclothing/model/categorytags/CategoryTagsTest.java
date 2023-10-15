package com.twoclothing.model.categorytags;

import java.util.List;

public class CategoryTagsTest {
    public static void main(String[] args) {
        CategoryTags c0 = new CategoryTags(null, null, "所有種類", 101);
        CategoryTags c1 = new CategoryTags(null, 1, "上衣", 101);
        CategoryTags c2 = new CategoryTags(null, 1, "褲子", 205);
        CategoryTags c3 = new CategoryTags(null, 1, "飾品", 205);
        CategoryTags c4 = new CategoryTags(null, 2, "短袖", 205);
        CategoryTags c5 = new CategoryTags(null, 2, "長袖", 101);
        CategoryTags c6 = new CategoryTags(null, 3, "短褲", 101);
        CategoryTags c7 = new CategoryTags(null, 3, "長褲", 101);
        CategoryTags c8 = new CategoryTags(null, 5, "男短袖", 101);
        CategoryTags c9 = new CategoryTags(null, 5, "女短袖", 101);
        CategoryTags c10 = new CategoryTags(7, 3, "七分褲", 208);
        CategoryTags c11 = new CategoryTags(2, 15, "亂填FK", 209);

        // CategoryTagsTest
        CategoryTagsDAO categoryTagsDAO = new CategoryTagsJDBCDAO();

        // 插入測試
        CategoryTags[] arr = {c0, c1, c2, c3, c4, c5, c6, c7, c8, c9};
        for (CategoryTags c : arr) {
            categoryTagsDAO.insert(c);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢測試
        // 單筆查詢
        CategoryTags byPrimaryKey = categoryTagsDAO.getByPrimaryKey(5);
        CategoryTags byPrimaryKey1 = categoryTagsDAO.getByPrimaryKey(11);
        System.out.println(byPrimaryKey);
        System.out.println(byPrimaryKey1);

        System.out.println("=====================================================================================================================================");

        // 全部查詢
        List<CategoryTags> list = categoryTagsDAO.getAll();
        for (CategoryTags c : list) {
            System.out.println(c);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢員工設定的標籤
        List<CategoryTags> list1 = categoryTagsDAO.getAllByEmpId(205);
        for (CategoryTags c : list1) {
            System.out.println(c);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢所有子標籤
        List<CategoryTags> list2 = categoryTagsDAO.getAllSubByPrimaryKey(3);
        for (CategoryTags c : list2) {
            System.out.println(c);
        }

        System.out.println("=====================================================================================================================================");

        // 修改測試
        categoryTagsDAO.update(c10);
        categoryTagsDAO.update(c11);
    }
}
