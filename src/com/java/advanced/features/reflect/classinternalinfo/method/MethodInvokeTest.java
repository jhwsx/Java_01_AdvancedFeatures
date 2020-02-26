package com.java.advanced.features.reflect.classinternalinfo.method;

import com.java.advanced.features.reflect.Apple;

import java.lang.reflect.Method;

public class MethodInvokeTest {
    public static void main(String[] args) throws Exception {
        // 获取 Apple 对象
        Class<Apple> appleClass = Apple.class;
        Apple apple = appleClass.newInstance();
        // 演示 public Object invoke(Object obj, Object... args)
        // 获取 public void initColorAndPrice(String color, float price) 方法，并调用
        Method initColorAndPriceMethod = appleClass.getDeclaredMethod("initColorAndPrice", String.class, float.class);
        boolean result = (boolean) initColorAndPriceMethod.invoke(apple, "red", 18f);
        System.out.println("getColor() = " + apple.getColor() + ", getPrice() = " + apple.getPrice() + ", result = " + result);

        // 获取 void setSize(int size) 方法，并调用
        Method checkSizeMethod = appleClass.getDeclaredMethod("setSize", int.class);
        // 解除此 Method 对象的 Java 语言访问控制
        checkSizeMethod.setAccessible(true);
        // 没有返回值时，返回 null
        Object invoke = checkSizeMethod.invoke(apple, 100);
        System.out.println("getSize() = " + apple.getSize() + ", result = " + invoke);


        // 获取 public static int getCount() 方法，并调用
        Method getCountMethod = appleClass.getMethod("getCount");
        // 底层方法是静态的，那么可以忽略指定的 obj 参数。该参数可以为 null。
        int count = (int) getCountMethod.invoke(null);
        System.out.println("count = " + count);
    }
}
/*
打印结果：
getColor() = red, getPrice() = 18.0, result = true
getSize() = 100, result = null
count = 1
 */
