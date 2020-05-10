package com.java.advanced.features.generics.tutorial.t07_wildcards._03_lower_bounded_wildcards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2020/4/24
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>(Arrays.asList(-1, -2, -3));
        List<Number> numberList = new ArrayList<>(Arrays.asList(0.1f, 1000000));
        List<Object> objectList = new ArrayList<>(Arrays.asList("hello", "world"));
        addNumber(integerList);
        System.out.println(integerList);
        addNumber(numberList);
        System.out.println(numberList);
        addNumber(objectList);
        System.out.println(objectList);
    }

    /**
     * 在 {@code List<Integer>}, {@code List<Number>}, {@code List<Object>} 集合的尾部添加数字
     *
     * @param list
     */
    public static void addNumber(List<? super Integer> list) {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }
}
