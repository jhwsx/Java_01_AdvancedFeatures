package com.java.advanced.features.reflect.basicclass;

import com.java.advanced.features.reflect.Fruit;

public class _01_GetClassTest {
    public static void main(String[] args) {
        // 1, 通过类对象获取
        Fruit fruit = new Fruit();
        Class fruitClass1 = fruit.getClass();
        System.out.println("fruitClass1 = " + fruitClass1);
        // 2, 通过类的 class 对象获取
        Class fruitClass2 = Fruit.class;
        System.out.println("fruitClass2 = " + fruitClass2);
        // 3, 通过全类名获取
        Class fruitClass3 = null;
        try {
            fruitClass3 = Class.forName("com.java.advanced.features.reflect.Fruit");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("fruitClass3 = " + fruitClass3);
        // 4, 通过 ClassLoader.loadClass()加载
        Class fruitClass4 = null;
        try {
            fruitClass4 = _01_GetClassTest.class.getClassLoader().loadClass("com.java.advanced.features.reflect.Fruit");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("fruitClass4 = " + fruitClass4);

        System.out.println("result = " + (fruitClass1 == fruitClass2
                && fruitClass2 == fruitClass3
                && fruitClass3 == fruitClass4));
    }
}
/*
打印结果：
fruitClass1 = class com.java.advanced.features.reflect.Fruit
fruitClass2 = class com.java.advanced.features.reflect.Fruit
fruitClass3 = class com.java.advanced.features.reflect.Fruit
fruitClass4 = class com.java.advanced.features.reflect.Fruit
result = true
 */
