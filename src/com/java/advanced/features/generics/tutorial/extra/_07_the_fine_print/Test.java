package com.java.advanced.features.generics.tutorial.extra._07_the_fine_print;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2021/3/4
 */
public class Test {
    public static void main(String[] args) {
//        List<String>[] lsa = new List<String>[10]; // 编译报错：Generic array creation
//        Object o = lsa;
//        Object[] oa = (Object[]) o;
//        List<Integer> li = new ArrayList<>();
//        li.add(3);
//        oa[1] = li;
//
//        String s = lsa[1].get(0);

        List<?>[] lsa = new List<?>[10]; // OK
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<>();
        li.add(3);
        oa[1] = li;
        String s = (String) lsa[1].get(0);

        // List<String>[] lists = new List<?>[10];
    }

//    <T> T[] makeArray(T t) {
//        return new T[100]; // 编译报错：Type parameter 'T' cannot be instantiated directly
//    }
}
