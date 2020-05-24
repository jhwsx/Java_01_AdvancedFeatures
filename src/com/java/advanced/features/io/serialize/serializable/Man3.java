package com.java.advanced.features.io.serialize.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * https://www.cnblogs.com/youxin/archive/2013/06/04/3116304.html
 * @author wangzhichao
 * @since 2020/5/24
 */
public class Man3 extends Person7 implements Serializable  {
    private double salary;

    public Man3(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        // 先序列化本类对象
        oos.defaultWriteObject();
        // 再序列化父类的域
        oos.writeObject(getName());
        oos.writeInt(getAge());
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        // 先反序列化本类对象
        ois.defaultReadObject();;
        // 再反序列化父类的域
        setName((String) ois.readObject());
        setAge(ois.readInt());
    }
}
