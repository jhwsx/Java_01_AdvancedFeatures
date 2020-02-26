package com.java.advanced.features.reflect.classinternalinfo.constructor;

import com.java.advanced.features.reflect.Apple;

import java.lang.reflect.Constructor;

public class ConstructorGetTest {
    public static void main(String[] args) {
        Class<Apple> appleClass = Apple.class;

        System.out.println("1, 演示 public Constructor<?>[] getConstructors()");
        System.out.println("获取所有的公共构造方法对象数组:");
        Constructor<?>[] constructors = appleClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("2, 演示 public Constructor<T> getConstructor(Class<?>... parameterTypes)");
        System.out.println("获取指定参数列表的公共构造方法对象");
        try {
            System.out.println("获取 public Apple():");
            Constructor<Apple> constructor = appleClass.getConstructor();
            System.out.println("constructor = " + constructor);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("获取 public Apple(String color, int size):");
            Constructor<Apple> constructor = appleClass.getConstructor(String.class, int.class);
            // 需要注意的是，下面这种写法会抛出异常：
            // java.lang.NoSuchMethodException: com.java.advanced.features.reflect.Apple.<init>(java.lang.String, java.lang.Integer)
            // 这是因为没有找到参数列表为(String, Integer) 的构造方法
            // Constructor<Apple> constructor1 = appleClass.getConstructor(String.class, Integer.class);
            System.out.println("constructor = " + constructor);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("获取 private Apple(String color, Float price)：");
            appleClass.getConstructor(String.class, Float.class);
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        }

        System.out.println("3, 演示 public Constructor<?>[] getDeclaredConstructors()");
        Constructor<?>[] declaredConstructors = appleClass.getDeclaredConstructors();
        System.out.println("获取所有的构造方法对象:");
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }

        System.out.println("4, 演示 public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)");
        System.out.println("获取指定参数列表的构造方法对象");
        try {
            System.out.println("获取 private Apple(String color, Float price)：");
            Constructor<Apple> constructor = appleClass.getDeclaredConstructor(String.class, Float.class);
            System.out.println("constructor = " + constructor);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("获取 public Apple()：");
            Constructor<Apple> constructor = appleClass.getDeclaredConstructor();
            System.out.println("constructor = " + constructor);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
/*
打印结果：
1, 演示 public Constructor<?>[] getConstructors()
获取所有的公共构造方法对象数组:
public com.java.advanced.features.reflect.Apple(java.lang.String,int)
public com.java.advanced.features.reflect.Apple()
2, 演示 public Constructor<T> getConstructor(Class<?>... parameterTypes)
获取指定参数列表的公共构造方法对象
获取 public Apple():
constructor = public com.java.advanced.features.reflect.Apple()
获取 public Apple(String color, int size):
constructor = public com.java.advanced.features.reflect.Apple(java.lang.String,int)
获取 private Apple(String color, Float price)：
java.lang.NoSuchMethodException: com.java.advanced.features.reflect.Apple.<init>(java.lang.String, java.lang.Float)
3, 演示 public Constructor<?>[] getDeclaredConstructors()
获取所有的构造方法对象:
com.java.advanced.features.reflect.Apple(java.lang.String)
private com.java.advanced.features.reflect.Apple(java.lang.String,java.lang.Float)
public com.java.advanced.features.reflect.Apple(java.lang.String,int)
public com.java.advanced.features.reflect.Apple()
4, 演示 public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)
获取指定参数列表的构造方法对象
获取 private Apple(String color, Float price)：
constructor = private com.java.advanced.features.reflect.Apple(java.lang.String,java.lang.Float)
获取 public Apple()：
constructor = public com.java.advanced.features.reflect.Apple()
 */
