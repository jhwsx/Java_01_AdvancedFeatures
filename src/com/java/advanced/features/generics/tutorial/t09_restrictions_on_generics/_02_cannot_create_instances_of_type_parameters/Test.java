package com.java.advanced.features.generics.tutorial.t09_restrictions_on_generics._02_cannot_create_instances_of_type_parameters;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2021/3/4
 */
public class Test {
    public static <E> void append(List<E> list) {
//        E elem = new E(); // Type parameter 'E' cannot be instantiated directly
//        list.add(elem);
    }

    public static <E> void appendFixed(List<E> list, Class<E> cls) throws Exception {
        E elem = cls.newInstance();
        list.add(elem);
    }

    public static void main(String[] args) throws Exception {
        List<String> ls = new ArrayList<>();
        appendFixed(ls, String.class);
    }
}
