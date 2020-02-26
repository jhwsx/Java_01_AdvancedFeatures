package com.java.advanced.features.reflect.classinternalinfo.method;

import com.java.advanced.features.reflect.Apple;

import java.lang.reflect.Method;

public class MethodGetTest {
    public static void main(String[] args) {
        Class<Apple> appleClass = Apple.class;
        System.out.println("1, 演示 public Method[] getDeclaredMethods()");
        System.out.println("获取本类声明的所有方法对象，但不包括继承的方法");
        Method[] declaredMethods = appleClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }

        System.out.println("2, 演示 public Method getDeclaredMethod(String name, Class<?>... parameterTypes)");
        System.out.println("获取 void checkSize(int size)");
        try {
            Method checkSizeMethod = appleClass.getDeclaredMethod("checkSize", int.class);
            System.out.println(checkSizeMethod);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println("3, 演示 public Method[] getMethods()");
        Method[] methods = appleClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println("4, 演示 public Method getMethod(String name, Class<?>... parameterTypes)");
        try {
            System.out.println("获取 private void checkPrice(float price)");
            Method checkPriceField = appleClass.getMethod("checkPrice", float.class);
            System.out.println(checkPriceField);
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        }

        try {
            System.out.println("获取  public String getColor()");
            Method colorMethod = appleClass.getMethod("getColor");
            System.out.println(colorMethod);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
