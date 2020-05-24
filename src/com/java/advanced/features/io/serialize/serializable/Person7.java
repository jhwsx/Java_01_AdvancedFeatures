package com.java.advanced.features.io.serialize.serializable;

public class Person7 {

    private String name;
    private int age;
    // 在 Person6 的基础上添加了无参构造器
    public Person7() {
    }

    public Person7(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
