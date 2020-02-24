package com.java.advanced.features.reflect.classinternalinfo.field;

import com.java.advanced.features.reflect.Person;

import java.lang.reflect.Field;

public class FieldGetTest {
    public static void main(String[] args) {
        Class<Person> personClass = Person.class;
        System.out.println("1, 演示 public Field getDeclaredField(String name)");
        System.out.println("获取指定字段名的 Field 对象");
        try {
            System.out.println("获取 private String name;");
            Field nameField = personClass.getDeclaredField("name");
            System.out.println("nameField = " + nameField);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("获取 public float salary;");
            Field salaryField = personClass.getDeclaredField("salary");
            System.out.println("salaryField = " + salaryField);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        System.out.println("2, 演示 public Field[] getDeclaredFields()");
        System.out.println("获取所有已声明字段的 Field 对象数组");
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }

        System.out.println("3, 演示 public Field getField(String name)");
        System.out.println("获取指定字段名的公共的 Field 对象");
//        try {
            // 获取 private String name;
            // 下面这行代码抛出异常：java.lang.NoSuchFieldException: name
//            Field nameField = personClass.getField("name");
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }

        try {
            System.out.println("获取 public float salary;");
            Field salaryField = personClass.getField("salary");
            System.out.println(salaryField);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        System.out.println("4, 演示 public Field[] getFields()");
        System.out.println("获取所有已声明字段的 Field 对象数组");
        Field[] fields = personClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
    }
}

/*
打印结果：
1, 演示 public Field getDeclaredField(String name)
获取指定字段名的 Field 对象
获取 private String name;
nameField = private java.lang.String com.java.advanced.features.reflect.Person.name
获取 public float salary;
salaryField = public float com.java.advanced.features.reflect.Person.salary
2, 演示 public Field[] getDeclaredFields()
获取所有已声明字段的 Field 对象数组
private static int com.java.advanced.features.reflect.Person.count
private final int com.java.advanced.features.reflect.Person.id
private java.lang.String com.java.advanced.features.reflect.Person.name
private int com.java.advanced.features.reflect.Person.age
public float com.java.advanced.features.reflect.Person.salary
3, 演示 public Field getField(String name)
获取指定字段名的公共的 Field 对象
获取 public float salary;
public float com.java.advanced.features.reflect.Person.salary
4, 演示 public Field[] getFields()
获取所有已声明字段的 Field 对象数组
public float com.java.advanced.features.reflect.Person.salary
 */
