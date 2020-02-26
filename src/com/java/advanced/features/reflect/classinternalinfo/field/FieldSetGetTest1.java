package com.java.advanced.features.reflect.classinternalinfo.field;


import com.java.advanced.features.reflect.Apple;
import com.java.advanced.features.reflect.Fruit;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class FieldSetGetTest1 {
    public static void main(String[] args) throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException, NoSuchFieldException {
        Class<Apple> appleClass = Apple.class;
        Constructor<Apple> constructor = appleClass.getConstructor();
        Apple apple = constructor.newInstance();
        System.out.println("1, 获取 String color; 字段，修改它的值并获取修改后的值：");
        Field colorField = appleClass.getDeclaredField("color");
        // 解除此 Field 对象的 Java 语言访问控制
        colorField.setAccessible(true);
        colorField.set(apple, "red");
        String color = (String) colorField.get(apple);
        System.out.println("color = " + color + ", getColor() = " + apple.getColor());

        System.out.println("2, 获取 private int size; 字段，修改它的值并获取修改后的值：");
        Field sizeField = appleClass.getDeclaredField("size");
        // 下面这行不写，会报异常：
        // java.lang.IllegalAccessException: Class com.java.advanced.features.reflect.
        // classinternalinfo.field.FieldApiTest can not access a member of class
        // com.java.advanced.features.reflect.Apple with modifiers "private"
        sizeField.setAccessible(true);
        sizeField.set(apple, 10);
        int size = (int) sizeField.get(apple);
        System.out.println("size = " + size + ", getSize() = " + apple.getSize());

        System.out.println("3, 获取 private static int count; 字段，修改它的值并获取修改后的值：");
        Field countField = appleClass.getDeclaredField("count");
        countField.setAccessible(true);
        // 对于 set 来说， 如果底层字段是一个静态字段，则忽略 obj 变量；它可能为 null。
        // 所以，这里第一个参数 obj，可以为 null。当然，也可以填入对象值。
        countField.set(null, 33);
        // 对于 get 来说， 如果底层字段是一个静态字段，则忽略 obj 变量；它可能为 null。
        // 所以，这里第一个参数 obj，可以为 null。当然，也可以填入对象值。
        int count = (int) countField.get(null);
        System.out.println("count = " + count + ", getCount() = " + Apple.getCount());

        System.out.println("下面演示几种异常：");
        System.out.println("1: 实例字段下，指定对象变量为 null，抛出 NullPointerException 异常");
        try {
            Field priceField = appleClass.getField("price");
            priceField.set(null, 12f);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("2: 实例字段下，指定对象变量不是类的实例，抛出 IllegalArgumentException 异常");
        try {
            Field priceField = appleClass.getField("price");
            priceField.set(new Fruit(), 12f);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("3: 底层字段的类型是基本类型，但是设置给 obj 的字段的新值无法转换为基本类型，抛出 IllegalArgumentException");
        try {
            Field priceField = appleClass.getField("price");
            priceField.set(apple, "price");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

/*
打印结果：
1, 获取 String color; 字段，修改它的值并获取修改后的值：
color = red, getColor() = red
2, 获取 private int size; 字段，修改它的值并获取修改后的值：
size = 10, getSize() = 10
3, 获取 private static int count; 字段，修改它的值并获取修改后的值：
count = 33, getCount() = 33
下面演示几种异常：
1: 实例字段下，指定对象变量为 null，抛出 NullPointerException 异常
java.lang.NullPointerException
2: 实例字段下，指定对象变量不是类的实例，抛出 IllegalArgumentException 异常
java.lang.IllegalArgumentException: Can not set float field com.java.advanced.features.reflect.Apple.price to com.java.advanced.features.reflect.Fruit
3: 底层字段的类型是基本类型，但是设置给 obj 的字段的新值无法转换为基本类型，抛出 IllegalArgumentException
java.lang.IllegalArgumentException: Can not set float field com.java.advanced.features.reflect.Apple.price to java.lang.String
*/
