package com.java.advanced.features.reflect.clazz;

import com.java.advanced.features.reflect.Person;

public class GetClassTest {
    public static void main(String[] args) {
        // 1, 通过类对象获取
        Person person = new Person();
        Class personClass1 = person.getClass();
        System.out.println("personClass1 = " + personClass1);
        // 2, 通过类的 class 对象获取
        Class personClass2 = Person.class;
        System.out.println("personClass2 = " + personClass2);
        // 3, 通过全类名获取
        Class personClass3 = null;
        try {
            personClass3 = Class.forName("com.java.advanced.features.reflect.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("personClass3 = " + personClass3);
        // 4, 通过 ClassLoader.loadClass()加载
        Class personClass4 = null;
        try {
            personClass4 = GetClassTest.class.getClassLoader().loadClass("com.java.advanced.features.reflect.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("personClass4 = " + personClass4);

        System.out.println("result = " + (personClass1 == personClass2 && personClass2 == personClass3 && personClass3 == personClass4));

    }
}
/*
打印结果：
personClass1 = class com.java.advanced.features.reflect.Person
personClass2 = class com.java.advanced.features.reflect.Person
personClass3 = class com.java.advanced.features.reflect.Person
personClass4 = class com.java.advanced.features.reflect.Person
result = true

 */
