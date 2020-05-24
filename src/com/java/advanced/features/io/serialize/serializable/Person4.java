package com.java.advanced.features.io.serialize.serializable;

import java.io.Serializable;

// Serializable 是一个标记接口
public class Person4 implements Serializable {

    private String name;
    private int age;
    private transient Address address;

    public Person4(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
