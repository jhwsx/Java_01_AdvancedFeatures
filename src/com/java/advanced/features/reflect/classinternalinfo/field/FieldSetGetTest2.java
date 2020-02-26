package com.java.advanced.features.reflect.classinternalinfo.field;


import com.java.advanced.features.reflect.Apple;
import com.java.advanced.features.reflect.Fruit;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class FieldSetGetTest2 {
    public static void main(String[] args) throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException, NoSuchFieldException {
        Class<Apple> appleClass = Apple.class;
        Constructor<Apple> constructor = appleClass.getConstructor();
        Apple apple = constructor.newInstance();

        System.out.println("1, 获取 private int size; 字段，修改它的值并获取修改后的值：");
        Field sizeField = appleClass.getDeclaredField("size");
        sizeField.setAccessible(true);
        sizeField.setInt(apple, 10);
        int size = sizeField.getInt(apple);
        System.out.println("size = " + size + ", getSize() = " + apple.getSize());

        System.out.println("2, 获取 private static int count; 字段，修改它的值并获取修改后的值：");
        Field countField = appleClass.getDeclaredField("count");
        countField.setAccessible(true);
        // 对于 set 来说， 如果底层字段是一个静态字段，则忽略 obj 变量；它可能为 null。
        // 所以，这里第一个参数 obj，可以为 null。当然，也可以填入对象值。
        countField.setInt(null, 33);
        // 对于 get 来说， 如果底层字段是一个静态字段，则忽略 obj 变量；它可能为 null。
        // 所以，这里第一个参数 obj，可以为 null。当然，也可以填入对象值。
        int count = countField.getInt(null);
        System.out.println("count = " + count + ", getCount() = " + Apple.getCount());

        System.out.println("3, 获取 public float price; 字段，修改它的值并获取修改后的值：");
        Field priceField = appleClass.getField("price");
        priceField.setFloat(apple, 12f);
        float price = priceField.getFloat(apple);
        System.out.println("price = " + price + ", getPrice() = " + apple.getPrice());
    }

}

/*
打印结果：
1, 获取 private int size; 字段，修改它的值并获取修改后的值：
size = 10, getSize() = 10
2, 获取 private static int count; 字段，修改它的值并获取修改后的值：
count = 33, getCount() = 33
3, 获取 public float price; 字段，修改它的值并获取修改后的值：
price = 12.0, getPrice() = 12.0
*/
