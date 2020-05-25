package com.java.advanced.features.reflect.basicclass;

import com.java.advanced.features.reflect.Apple;

public class _01_GetClassTest {
    public static void main(String[] args) {
        // 1, 通过类对象获取
        Apple apple = new Apple();
        Class appleClass1 = apple.getClass();
        System.out.println("appleClass1 = " + appleClass1);
        // 2, 通过类的 class 对象获取
        Class appleClass2 = Apple.class;
        System.out.println("appleClass2 = " + appleClass2);
        // 3, 通过全类名获取
        Class appleClass3 = null;
        try {
            appleClass3 = Class.forName("com.java.advanced.features.reflect.Apple");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("appleClass3 = " + appleClass3);
        // 4, 通过 ClassLoader.loadClass()加载
        Class appleClass4 = null;
        try {
            appleClass4 = _01_GetClassTest.class.getClassLoader().loadClass("com.java.advanced.features.reflect.Apple");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("appleClass4 = " + appleClass4);

        System.out.println("result = " + (appleClass1 == appleClass2
                && appleClass2 == appleClass3
                && appleClass3 == appleClass4));
    }
}
/*
打印结果：
appleClass1 = class com.java.advanced.features.reflect.Apple
appleClass2 = class com.java.advanced.features.reflect.Apple
appleClass3 = class com.java.advanced.features.reflect.Apple
appleClass4 = class com.java.advanced.features.reflect.Apple
result = true
 */
