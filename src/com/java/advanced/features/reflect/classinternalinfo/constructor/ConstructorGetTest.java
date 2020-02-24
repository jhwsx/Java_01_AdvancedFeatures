package com.java.advanced.features.reflect.classinternalinfo.constructor;

import com.java.advanced.features.reflect.Person;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class ConstructorGetTest {
    public static void main(String[] args) {
        Class<Person> personClass = Person.class;
        System.out.println("1, 演示 public Constructor<T> getConstructor(Class<?>... parameterTypes)");
        System.out.println("获取指定参数列表的公共构造方法对象");
        try {
            System.out.println("获取 public Person():");
            Constructor<Person> personClassConstructor = personClass.getConstructor();
            System.out.println("personClassConstructor = " + personClassConstructor);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("获取 public Person(String name, Integer age):");
            Constructor<Person> personClassConstructor = personClass.getConstructor(String.class, Integer.class);
            // 需要注意的是，下面这种写法会抛出异常：
            // java.lang.NoSuchMethodException: com.java.advanced.features.reflect.Person.<init>(java.lang.String, int)
            // 这是因为没有找到参数列表为(String, int) 的构造方法
            // Constructor<Person> personClassConstructor = personClass.getConstructor(String.class, int.class);
            System.out.println("personClassConstructor = " + personClassConstructor);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println("2, 演示 public Constructor<?>[] getConstructors()");
        System.out.println("获取所有的公共构造方法对象数组:");
        Constructor<?>[] constructors = personClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("3, 演示 public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)");
        System.out.println("获取指定参数列表的构造方法对象");
        try {
            System.out.println("获取 private Person(String name, float salary)：");
            Constructor<Person> personClassDeclaredConstructor = personClass.getDeclaredConstructor(String.class, float.class);
            System.out.println("personClassDeclaredConstructor = " + personClassDeclaredConstructor);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("获取 public Person()：");
            Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor();
            System.out.println("declaredConstructor = " + declaredConstructor);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println("4, 演示 public Constructor<?>[] getDeclaredConstructors()");
        Constructor<?>[] declaredConstructors = personClass.getDeclaredConstructors();
        System.out.println("获取所有的构造方法对象:");
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }
    }
}
/*
打印结果：
1, 演示 public Constructor<T> getConstructor(Class<?>... parameterTypes)
获取指定参数列表的公共构造方法对象
获取 public Person():
personClassConstructor = public com.java.advanced.features.reflect.Person()
获取 public Person(String name, Integer age):
personClassConstructor = public com.java.advanced.features.reflect.Person(java.lang.String,java.lang.Integer)
2, 演示 public Constructor<?>[] getConstructors()
获取所有的公共构造方法对象数组:
public com.java.advanced.features.reflect.Person(java.lang.String,java.lang.Integer)
public com.java.advanced.features.reflect.Person()
3, 演示 public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)
获取指定参数列表的构造方法对象
获取 private Person(String name, float salary)：
personClassDeclaredConstructor = private com.java.advanced.features.reflect.Person(java.lang.String,float)
获取 public Person()：
declaredConstructor = public com.java.advanced.features.reflect.Person()
4, 演示 public Constructor<?>[] getDeclaredConstructors()
获取所有的构造方法对象:
public com.java.advanced.features.reflect.Person(java.lang.String,java.lang.Integer)
private com.java.advanced.features.reflect.Person(java.lang.String,float)
public com.java.advanced.features.reflect.Person()
 */
