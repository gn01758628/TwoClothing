package com.twoclothing.web.categorytags;

import java.io.Serializable;
import java.util.Objects;

public class CategoryTags implements Serializable {
    private Integer tagId;
    private Integer superTagId;
    private String categoryName;
    private Integer empId;

    public CategoryTags() {
    }

    public CategoryTags(Integer tagId, Integer superTagId, String categoryName, Integer empId) {
        this.tagId = tagId;
        this.superTagId = superTagId;
        this.categoryName = categoryName;
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "CategoryTags{" +
                "tagId=" + tagId +
                ", superTagId=" + superTagId +
                ", categoryName='" + categoryName + '\'' +
                ", empId=" + empId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryTags that = (CategoryTags) o;
        return Objects.equals(tagId, that.tagId) && Objects.equals(superTagId, that.superTagId) && Objects.equals(categoryName, that.categoryName) && Objects.equals(empId, that.empId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, superTagId, categoryName, empId);
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getSuperTagId() {
        return superTagId;
    }

    public void setSuperTagId(Integer superTagId) {
        this.superTagId = superTagId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }
}
