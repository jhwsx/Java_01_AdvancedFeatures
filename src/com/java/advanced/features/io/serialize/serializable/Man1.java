package com.java.advanced.features.io.serialize.serializable;

import java.io.Serializable;

/**
 * @author wangzhichao
 * @since 2020/5/24
 */
public class Man1 extends Person6 implements Serializable  {
    private double salary;

    public Man1(String name, int age, double salary) {
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
