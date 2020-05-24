package com.java.advanced.features.io.serialize.serializable;

import java.io.Serializable;

public class Person8 implements Serializable {

    private String name;
    private int age;

    public Person8() {
    }

    public Person8(String name, int age) {
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
