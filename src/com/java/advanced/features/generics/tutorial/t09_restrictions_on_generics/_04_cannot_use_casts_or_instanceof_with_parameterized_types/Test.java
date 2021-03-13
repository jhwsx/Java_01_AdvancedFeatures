package com.java.advanced.features.generics.tutorial.t09_restrictions_on_generics._04_cannot_use_casts_or_instanceof_with_parameterized_types;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2021/3/4
 */
public class Test {
    public static <E> void rtti(List<E> list) {
//        if (list instanceof ArrayList<Integer>) { // 编译报错：Illegal generic type for instanceof
//
//        }

        if (list instanceof ArrayList) {
            System.out.println("yes");
        }

        if (list instanceof ArrayList<?>) {
            System.out.println("pass");
        }
    }

    public static void cast() {
        List<Integer> li = new ArrayList<>();
        // 编译报错：Inconvertible types; cannot cast 'java.util.List<java.lang.Integer>' to 'java.util.List<java.lang.Number>'
        // List<Number> ln = (List<Number>)li;

        List<?> lWildcard = new ArrayList<>();
        List<Number> numberList = (List<Number>) lWildcard; // OK

        List<String> ls = new ArrayList<>();
        ArrayList<String> as = (ArrayList<String>) ls; // OK
    }

    <T> T badCast(T t, Object o) {
        return (T)o;
    }

    public static void main(String[] args) {
        rtti(new ArrayList<Integer>());
        rtti(new ArrayList<String>());
        rtti(new LinkedList<>());
    }
}
