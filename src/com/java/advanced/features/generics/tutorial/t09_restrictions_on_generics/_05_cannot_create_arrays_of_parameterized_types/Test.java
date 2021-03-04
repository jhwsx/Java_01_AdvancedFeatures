package com.java.advanced.features.generics.tutorial.t09_restrictions_on_generics._05_cannot_create_arrays_of_parameterized_types;

import java.util.ArrayList;
import java.util.List;

/**
 * 不能创建参数化类型的数组
 *
 * @author wangzhichao
 * @since 2021/3/4
 */
public class Test {
    public static void main(String[] args) {
        // 编译报错：Generic array creation
//        List<Integer>[] arrayOfLists = new List<Integer>[2];

        // 普通类型下，把不同类型存入数组，会抛出运行时异常：ArrayStoreException
        Object[] strings = new String[2];
        strings[0] = "hi";   // OK
        strings[1] = 100;    // An ArrayStoreException is thrown.

        // 泛型类型下，假定可以声明参数化类型的数组
//        Object[] lists = new List<Integer>[2];
//        lists[0] = new ArrayList<Integer>();
//        lists[1] = new ArrayList<String>();
    }
}
