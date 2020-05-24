package com.java.advanced.features.io.serialize.serializable;

import java.io.*;

public class Person10 implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private double salary;

    public Person10(String name, int age, double salary) {
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

    private Object writeReplace() throws ObjectStreamException {
        System.out.println("writeReplace");
        return this;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject(); // 注意：这个是默认的，必须要写
        System.out.println("writeObject");
    }
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject(); // 注意：这个是默认的，必须要写
        System.out.println("readObject");
    }

    private Object readResolve() {
        System.out.println("readResolve");
        return this;
    }

    @Override
    public String toString() {
        return "Person10{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
