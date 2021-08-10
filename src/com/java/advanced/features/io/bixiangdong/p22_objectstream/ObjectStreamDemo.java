package com.java.advanced.features.io.bixiangdong.p22_objectstream;

import java.io.*;

/**
 * static 修饰的字段不参与序列化与反序列化
 * transient 关键字修饰的字段不参与序列化与反序列化
 * @author wangzhichao
 * @since 2021/8/5
 */
public class ObjectStreamDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        writeObj();
        readObj();
    }

    private static void writeObj() throws IOException {
        Person person = new Person("wzc", 18);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.object"));
        oos.writeObject(person);
        oos.close();
    }

    private static void readObj() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.object"));
        Person person = (Person) ois.readObject();
        System.out.println(person);
        ois.close();
    }
}
