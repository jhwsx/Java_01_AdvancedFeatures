package com.java.advanced.features.reflect.basicclass;

import com.java.advanced.features.reflect.Apple;

import java.io.Serializable;

/**
 * @author wangzhichao
 * @since 2020/5/25
 */
public class _03_GetSuperClassTest {
    public static void main(String[] args) {
        System.out.println("Apple.class.getSuperclass() = " + Apple.class.getSuperclass());
        System.out.println("Serializable.class.getSuperclass() = " + Serializable.class.getSuperclass());
        System.out.println("int.class.getSuperclass() = " + int.class.getSuperclass());
        System.out.println("void.class.getSuperclass() = " + void.class.getSuperclass());
        String[] array = new String[]{};
        System.out.println("array.getClass().getSuperclass() = " + array.getClass().getSuperclass());
    }
}
/*
打印结果：
Apple.class.getSuperclass() = class com.java.advanced.features.reflect.Fruit
Serializable.class.getSuperclass() = null
int.class.getSuperclass() = null
void.class.getSuperclass() = null
array.getClass().getSuperclass() = class java.lang.Object
 */