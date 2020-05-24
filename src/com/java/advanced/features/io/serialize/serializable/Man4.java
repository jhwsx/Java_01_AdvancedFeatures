package com.java.advanced.features.io.serialize.serializable;

/**
 * @author wangzhichao
 * @since 2020/5/24
 */
public class Man4 extends Person8 {
    private double salary;

    public Man4(String name, int age, double salary) {
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
