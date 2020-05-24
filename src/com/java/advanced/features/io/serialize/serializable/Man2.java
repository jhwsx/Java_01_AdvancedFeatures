package com.java.advanced.features.io.serialize.serializable;

import java.io.Serializable;

/**
 * @author wangzhichao
 * @since 2020/5/24
 */
public class Man2 extends Person7 implements Serializable  {
    private double salary;

    public Man2(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
