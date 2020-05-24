package com.java.advanced.features.io.serialize.serializable;

import java.io.Serializable;

// Serializable 是一个标记接口
public class Person5 implements Serializable {

    private String name;
    private int age;
    private static int count;
    public Person5(String name, int age) {
        this.name = name;
        this.age = age;
        this.count = 1;
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

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Person5.count = count;
    }
}
