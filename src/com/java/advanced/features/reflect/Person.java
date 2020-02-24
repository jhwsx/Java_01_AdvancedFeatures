package com.java.advanced.features.reflect;

public class Person {
    private static int count;
    private final int id = count++;
    private String name;
    private int age;
    public float salary;

    public Person() {

    }

    private Person(String name, float salary) {
        this.name = name;
        this.salary = salary;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        isAgeValid(age);
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void isAgeValid(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("age should be larger than 0, but age = " + age);
        }
    }
}
