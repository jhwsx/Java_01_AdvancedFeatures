package com.java.advanced.features.reflect.classinternalinfo.field;

import com.java.advanced.features.reflect.Apple;

import java.lang.reflect.Field;

public class FieldGetTest {
    public static void main(String[] args) {
        Class<Apple> appleClass = Apple.class;

        System.out.println("1, 演示 public Field[] getDeclaredFields()");
        System.out.println("获取本类所有已声明字段的 Field 对象数组");
        Field[] declaredFields = appleClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }


        System.out.println("2, 演示 public Field getDeclaredField(String name)");
        System.out.println("获取本类指定字段名的 Field 对象");
        try {
            System.out.println("获取 private int size;");
            Field sizeField = appleClass.getDeclaredField("size");
            System.out.println("sizeField = " + sizeField);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("获取 public float price;");
            Field priceField = appleClass.getDeclaredField("price");
            System.out.println("priceField = " + priceField);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("获取父类 Fruit 的 public String taste;");
            appleClass.getDeclaredField("taste");
        } catch (NoSuchFieldException e) {
            // 此处抛出异常：java.lang.NoSuchFieldException: taste
            System.out.println(e);
        }

        System.out.println("3, 演示 public Field[] getFields()");
        System.out.println("获取所有已声明字段的公共Field 对象数组，包括继承自父类的");
        Field[] fields = appleClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        System.out.println("4, 演示 public Field getField(String name)");
        System.out.println("获取指定字段名的公共的 Field 对象");
        try {
            System.out.println("获取 private String size");;
            Field sizeField = appleClass.getField("size");
        } catch (NoSuchFieldException e) {
            System.out.println(e);
        }

        try {
            System.out.println("获取 public float price;");
            Field priceField = appleClass.getField("price");
            System.out.println(priceField);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }
}

/*
打印结果：
1, 演示 public Field[] getDeclaredFields()
获取本类所有已声明字段的 Field 对象数组
private static int com.java.advanced.features.reflect.Apple.count
private final int com.java.advanced.features.reflect.Apple.id
java.lang.String com.java.advanced.features.reflect.Apple.color
private int com.java.advanced.features.reflect.Apple.size
public float com.java.advanced.features.reflect.Apple.price
2, 演示 public Field getDeclaredField(String name)
获取本类指定字段名的 Field 对象
获取 private int size;
sizeField = private int com.java.advanced.features.reflect.Apple.size
获取 public float price;
priceField = public float com.java.advanced.features.reflect.Apple.price
获取父类 Fruit 的 public String taste;
java.lang.NoSuchFieldException: taste
3, 演示 public Field[] getFields()
获取所有已声明字段的公共Field 对象数组，包括继承自父类的
public float com.java.advanced.features.reflect.Apple.price
public java.lang.String com.java.advanced.features.reflect.Fruit.taste
4, 演示 public Field getField(String name)
获取指定字段名的公共的 Field 对象
获取 private String size
java.lang.NoSuchFieldException: size
获取 public float price;
public float com.java.advanced.features.reflect.Apple.price
 */
