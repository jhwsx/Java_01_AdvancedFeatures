package com.java.advanced.features.generics.tutorial.t08_type_erasure._04_nonreifiable_types;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2020/4/26
 */
public class ArrayBuilder {
    public static <T> void addToList(List<T> listArg, T... elements) {
        for (T element : elements) {
            listArg.add(element);
        }
    }

    public static void faultyMethod(List<String>... l) {
        Object[] objectArray = l;
        objectArray[0] = Arrays.asList(42);
        String s = l[0].get(0);
    }
}
