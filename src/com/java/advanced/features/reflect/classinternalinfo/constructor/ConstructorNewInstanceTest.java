package com.java.advanced.features.reflect.classinternalinfo.constructor;

import com.java.advanced.features.reflect.Apple;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorNewInstanceTest {
    public static void main(String[] args) {
        Class<Apple> appleClass = Apple.class;
        try {
            // 1，获取到 private Apple(String color, Float price) 对应的 Constructor 对象
            Constructor<Apple> declaredConstructor = appleClass.getDeclaredConstructor(String.class, Float.class);
            // 因为目标构造方法是 private 的，所以需要设置下边的代码为 true。
            declaredConstructor.setAccessible(true);
//            Apple apple = declaredConstructor.newInstance("red", Float.valueOf(1.8f));
            // 上面一行等价于下面一行
            Apple apple = declaredConstructor.newInstance("red", 1.8f);
            // 错误写法演示1: 参数顺序写错，抛出：java.lang.IllegalArgumentException: argument type mismatch
            // declaredConstructor.newInstance(Float.valueOf(1.8f), "red");
            // 错误写法演示2：参数个数写错，抛出: java.lang.IllegalArgumentException: wrong number of arguments
            // declaredConstructor.newInstance("red");
            System.out.println(apple);
            System.out.println("color = " + apple.getColor() + ", price = " + apple.getPrice());
        } catch (NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            // 2, 获取 public Apple(String color, int size) 对应的 Constructor 对象
            Constructor<Apple> constructor = appleClass.getConstructor(String.class, int.class);
            Apple apple = constructor.newInstance("red", 100);
            // 上面一行等价于下面一行
            //  Apple apple = constructor.newInstance("red", Integer.valueOf(100));
            System.out.println(apple);
            System.out.println("color = " + apple.getColor() + ", size = " + apple.getSize());
        } catch (NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
/*
打印结果：
com.java.advanced.features.reflect.Apple@4554617c
color = red, price = 1.8
com.java.advanced.features.reflect.Apple@74a14482
color = red, size = 100
 */
