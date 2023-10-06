package com.twoclothing.members;

import java.io.Serializable;
import java.util.Objects;

public class Members implements Serializable {
    private String name;
    private Integer age;

    public Members() {
    }

    @Override
    public String toString() {
        return "Members{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Members members = (Members) o;
        return Objects.equals(name, members.name) && Objects.equals(age, members.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
