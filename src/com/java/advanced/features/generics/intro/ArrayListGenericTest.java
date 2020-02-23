package com.java.advanced.features.generics.intro;

import java.util.ArrayList;

public class ArrayListGenericTest {
    public static void main(String[] args) {
        // 这是一个存放 String 数据的集合
        ArrayList<String> genericStringList = new ArrayList();
        genericStringList.add("hello");
        genericStringList.add("hi");
        // 一个无心的程序员意图存入了 int 类型，但是编译器会报错：
        // add (java.lang.String) in ArrayList cannot be applied to (int)
        // 这就起到了提前预防的作用
        // genericStringList.add(1);

        // 另外一个程序员，看到这个 nonGenericStringList，知道这是一个
        // 存放 String 数据的集合，就想看看这里面都有哪些元素。
        for (int i = 0; i < genericStringList.size(); i++) {
            // 取出元素时，无需再做强转
//            String e = (String) genericStringList.get(i);
            String e = genericStringList.get(i);
            System.out.println("e = " + e);
        }
    }
}
/*
打印结果：
e = hello
e = hi
 */