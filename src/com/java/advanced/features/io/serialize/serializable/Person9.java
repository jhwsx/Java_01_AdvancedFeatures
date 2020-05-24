package com.java.advanced.features.io.serialize.serializable;

import java.io.Serializable;

public class Person9 implements Serializable {

//    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private double salary;

    public Person9(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person9(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person9{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
