package com.java.advanced.features.reflect.basicclass;

import com.java.advanced.features.reflect.Apple;

import java.util.Collection;

/**
 * @author wangzhichao
 * @since 2020/5/25
 */
public class _03_GetSuperClassTest {
    public static void main(String[] args) {
        System.out.println("Apple.class.getSuperclass() = " + Apple.class.getSuperclass());
        System.out.println("Collection.class.getSuperclass() = " + Collection.class.getSuperclass());
        System.out.println("int.class.getSuperclass() = " + int.class.getSuperclass());
        System.out.println("void.class.getSuperclass() = " + void.class.getSuperclass());
        System.out.println("stringArray.getClass().getSuperclass() = " + String[].class.getSuperclass());
        System.out.println("integerArray.getClass().getSuperclass() = " + Integer[].class.getSuperclass());
    }
}
/*
打印结果：
Apple.class.getSuperclass() = class com.java.advanced.features.reflect.Fruit
Collection.class.getSuperclass() = null
int.class.getSuperclass() = null
void.class.getSuperclass() = null
stringArray.getClass().getSuperclass() = class java.lang.Object
integerArray.getClass().getSuperclass() = class java.lang.Object
 */