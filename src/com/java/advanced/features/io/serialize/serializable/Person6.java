package com.java.advanced.features.io.serialize.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Person6 implements Serializable {

    private String name;
    private int age;
    private transient Address address;

    public Person6(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        System.out.println("readObject");
        name = ois.readUTF();
        age = ois.readInt();
        address = (Address) ois.readObject();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("writeObject");
        out.writeUTF(name);
        out.writeInt(age);
        out.writeObject(address);
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
