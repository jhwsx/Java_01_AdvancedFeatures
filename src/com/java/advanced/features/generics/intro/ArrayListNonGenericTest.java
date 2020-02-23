package com.java.advanced.features.generics.intro;

import java.util.ArrayList;

public class ArrayListNonGenericTest {
    public static void main(String[] args) {
        // 这是一个存放 String 数据的集合
        ArrayList nonGenericStringList = new ArrayList();
        nonGenericStringList.add("hello");
        nonGenericStringList.add("hi");
        // 一个无心的程序员却存入了 int 类型，但是编译器没有任何提示
        nonGenericStringList.add(1);
        
        // 另外一个程序员，看到这个 nonGenericStringList，知道这是一个
        // 存放 String 数据的集合，就想看看这里面都有哪些元素。
        for (int i = 0; i < nonGenericStringList.size(); i++) {
          String e = (String) nonGenericStringList.get(i);
            System.out.println("e = " + e);
        }
    }
}
/*
打印结果：
e = hello
e = hi
Exception in thread "main" java.lang.ClassCastException: java.base/java.lang.Integer cannot be cast to java.base/java.lang.String
        at com.java.advanced.features.generics.intro.ArrayListNonGenericTest.main(ArrayListNonGenericTest.java:17)
*/