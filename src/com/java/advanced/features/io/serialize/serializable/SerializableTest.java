package com.java.advanced.features.io.serialize.serializable;

import com.java.advanced.features.io.serialize.SerializeUtils;

import java.io.*;
import java.lang.reflect.Constructor;

public class SerializableTest {
    private static String filePath = "./obj.object";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 第一组：Person1 没有实现序列化接口，报错：
        //test1();

        // 第二组：Person2 实现了序列化接口，正常
        //test2();

        // 第三组：Person3 实现了序列化接口，但是它的成员有没有实现序列化接口，报错：
//        test3();

        // 第四组：Person4，在 Person3 基础上使用了 transient 关键字, 正常
//        test4();

        // 第五组：静态变量不参与序列化
//        test5();

        // 第六组 多引用写入, 两次写入，但取出的对象是一模一样的。
        // test6();

        // 解决多引用写入问题，使用 oos.reset();
         // test7();

        // 解决多引用写入问题，使用 oos.writeUnshared(personWrite);
        // test8();

        // 反序列化打破单例的例子
        // testSingleton();

        // 修改反序列化打破单例
        // testSingletonFix();

//        testEnum();

        // 反射打破单例
        // testSingletonReflect();

        // 不允许通过反射创建单例
        // testSingletonReflectFix();
    }

    private static void testSingletonReflectFix() {
        try {
            SingletonReflectFix instance = SingletonReflectFix.getInstance();
            Class<SingletonReflectFix> clazz = SingletonReflectFix.class;
            Constructor<SingletonReflectFix> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            SingletonReflectFix newInstance = constructor.newInstance();
            System.out.println("instance == newInstance : " + (instance == newInstance));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testSingletonReflect() {
        try {
            SingletonSerializeFix instance = SingletonSerializeFix.getInstance();
            Class<SingletonSerializeFix> clazz = SingletonSerializeFix.class;
            Constructor<SingletonSerializeFix> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            SingletonSerializeFix newInstance = constructor.newInstance();
            System.out.println("instance == newInstance : " + (instance == newInstance));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testEnum() throws IOException, ClassNotFoundException {
        Color writeRed = Color.RED;
        SerializeUtils.writeObject(filePath, writeRed);
        Color readRed = SerializeUtils.readObject(filePath);
        System.out.println("writeRed=" + writeRed + ", readRed=" + readRed);
        System.out.println("writeRed.ordinal() = " + writeRed.ordinal()
                + ", readRed.ordinal() = " + readRed.ordinal());
        System.out.println("writeRed == readRed : " + (writeRed == readRed));

        /*
        在较低的版本中会出问题，现在是好的。
        打印结果：
        writeRed=RED, readRed=RED
        writeRed.ordinal() = 0, readRed.ordinal() = 0
        writeRed == readRed : true
        */
    }

    private static void testSingletonFix() throws IOException, ClassNotFoundException {
        SingletonSerializeFix writeSingleton = SingletonSerializeFix.getInstance();
        SerializeUtils.writeObject(filePath, writeSingleton);
        SingletonSerializeFix readSingleton = SerializeUtils.readObject(filePath);
        System.out.println("writeSingleton == readSingleton : " + (writeSingleton == readSingleton));
    }

    private static void testSingleton() throws IOException, ClassNotFoundException {
        Singleton writeSingleton = Singleton.getInstance();
        SerializeUtils.writeObject(filePath, writeSingleton);
        Singleton readSingleton = SerializeUtils.readObject(filePath);
        System.out.println("writeSingleton == readSingleton : " + (writeSingleton == readSingleton));
    }

    private static void test8() throws IOException, ClassNotFoundException {
        // 序列化
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Person2 personWrite = new Person2("wzc", 32);
        oos.writeObject(personWrite);
        personWrite.setAge(33);
        oos.writeUnshared(personWrite);
        oos.close();
        // 反序列化
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Person2 personRead1 = (Person2) ois.readObject();
        Person2 personRead2 = (Person2) ois.readObject();
        ois.close();
        System.out.println("personWrite:" + personWrite);
        System.out.println("personRead1:" + personRead1);
        System.out.println("personRead2:" + personRead2);
        System.out.println("personRead1 == personRead2:" + (personRead1 == personRead2));
        /*
         输出结果：ok
            personWrite:Person2{name='wzc', age=33}
            personRead1:Person2{name='wzc', age=32}
            personRead2:Person2{name='wzc', age=33}
            personRead1 == personRead2:false
         */
    }


    private static void test7() throws IOException, ClassNotFoundException {
        // 序列化
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Person2 personWrite = new Person2("wzc", 32);
        oos.writeObject(personWrite);
        personWrite.setAge(33);
        oos.reset(); // 增加了一行代码
        oos.writeObject(personWrite);
        oos.close();
        // 反序列化
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Person2 personRead1 = (Person2) ois.readObject();
        Person2 personRead2 = (Person2) ois.readObject();
        ois.close();
        System.out.println("personWrite:" + personWrite);
        System.out.println("personRead1:" + personRead1);
        System.out.println("personRead2:" + personRead2);
        System.out.println("personRead1 == personRead2:" + (personRead1 == personRead2));
        /*
         输出结果：ok
            personWrite:Person2{name='wzc', age=33}
            personRead1:Person2{name='wzc', age=32}
            personRead2:Person2{name='wzc', age=33}
            personRead1 == personRead2:false
         */
    }

    private static void test6() throws IOException, ClassNotFoundException {
        // 序列化
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Person2 personWrite = new Person2("wzc", 32);
        oos.writeObject(personWrite);
        personWrite.setAge(33);
        oos.writeObject(personWrite);
        oos.close();
        // 反序列化
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Person2 personRead1 = (Person2) ois.readObject();
        Person2 personRead2 = (Person2) ois.readObject();
        ois.close();
        System.out.println("personWrite:" + personWrite);
        System.out.println("personRead1:" + personRead1);
        System.out.println("personRead2:" + personRead2);
        System.out.println("personRead1 == personRead2:" + (personRead1 == personRead2));

        /*
        输出结果：
        personWrite:Person2{name='wzc', age=33}
        personRead1:Person2{name='wzc', age=32}
        personRead2:Person2{name='wzc', age=32}
        personRead1 == personRead2:true
         */
    }

    private static void test5() throws IOException, ClassNotFoundException {
        Person5 wzc = new Person5("wzc", 32);
        SerializeUtils.writeObject(filePath, wzc);
        wzc.setCount(2);
        Person5 person5 = SerializeUtils.readObject(filePath);
        System.out.println(person5.getName() + ":" + person5.getAge() + ":" + person5.getCount());
    }

    private static void test4() throws IOException, ClassNotFoundException {
        SerializeUtils.writeObject(filePath, new Person4("wzc", 32, new Address("shanghai")));
        Person4 person4 = SerializeUtils.readObject(filePath);
        System.out.println(person4.getName() + ":" + person4.getAge() + ":" + person4.getAddress());
    }

    private static void test3() throws IOException, ClassNotFoundException {
        // java.io.NotSerializableException: com.java.advanced.features.io.serialize.serializable.Address
        SerializeUtils.writeObject(filePath, new Person3("wzc", 32, new Address("shanghai")));
        Person3 person3 = SerializeUtils.readObject(filePath);
        System.out.println(person3.getName() + ":" + person3.getAge() + ":" + person3.getAddress());
    }

    private static void test2() throws IOException, ClassNotFoundException {
        //SerializeUtils.writeObject(filePath, new Person2("wzc", 32));
        Person2 person2 = SerializeUtils.readObject(filePath);
        System.out.println(person2.getName() + ":" + person2.getAge());
    }

    private static void test1() throws IOException, ClassNotFoundException {
        //  java.io.NotSerializableException: com.java.advanced.features.io.serialize.serializable.Person1
        SerializeUtils.writeObject(filePath, new Person1("wzc", 32));
        Person1 person1 = SerializeUtils.<Person1>readObject(filePath);
        System.out.println(person1.getName() + ":" + person1.getAge());
    }
}
