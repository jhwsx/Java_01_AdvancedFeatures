package com.java.advanced.features.io.bixiangdong.p22_objectstream;

import java.io.Serializable;

/**
 * @author wangzhichao
 * @since 2021/8/5
 */
public class Person implements Serializable {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
