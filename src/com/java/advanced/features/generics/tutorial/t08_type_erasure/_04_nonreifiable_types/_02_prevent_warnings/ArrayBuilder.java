package com.java.advanced.features.generics.tutorial.t08_type_erasure._04_nonreifiable_types._02_prevent_warnings;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2020/4/26
 */
public class ArrayBuilder {
    @SafeVarargs
    public static <T> void addToList(List<T> listArg, T... elements) {
        for (T element : elements) {
            listArg.add(element);
        }
    }

    @SuppressWarnings({"unchecked", "varargs"})
    // List<String>... l 会被翻译成 List[] l, 堆上的是 List[], 使用 List<String>... 指向了 List[], 所以这里会有堆污染。
    public static void faultyMethod(List<String>... l) {
        Object[] objectArray = l; // 有效的
        objectArray[0] = Arrays.asList(42);
        String s = l[0].get(0); // 这里抛出类型转换异常，取出的是 Integer，不能转换为 String。
    }
}
