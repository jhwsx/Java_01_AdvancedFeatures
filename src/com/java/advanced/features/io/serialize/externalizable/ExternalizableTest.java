package com.java.advanced.features.io.serialize.externalizable;

import com.java.advanced.features.io.serialize.SerializeUtils;

import java.io.IOException;

public class ExternalizableTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filePath = "./obj.object";
        // 第一组：java.io.InvalidClassException: com.java.advanced.features.io.serialize.externalizable.Person; no valid constructor
//        SerializeUtils.writeObject(filePath, new Person1("wzc", 32));
//        Person1 person1 = SerializeUtils.readObject(filePath);
//        System.out.println(person1.getName() + ":" + person1.getAge());
        // 第二组，输出正确
//        SerializeUtils.writeObject(filePath, new Person2("wzc", 32));
//        Person2 person2 = SerializeUtils.readObject(filePath);
//        System.out.println(person2.getName() + ":" + person2.getAge());
        // 第三组，即便属性使用了 transient 关键字，仍然可以序列化
        SerializeUtils.writeObject(filePath, new Person3("wzc", 32));
        Person3 person3 = SerializeUtils.readObject(filePath);
        System.out.println(person3.getName() + ":" + person3.getAge());
    }
}
