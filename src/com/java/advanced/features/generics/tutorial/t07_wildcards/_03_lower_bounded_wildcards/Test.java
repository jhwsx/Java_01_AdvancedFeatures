package com.java.advanced.features.generics.tutorial.t07_wildcards._03_lower_bounded_wildcards;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2020/4/24
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        List<Number> numberList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        addNumber(integerList);
        addNumber(numberList);
        addNumber(objectList);
    }

    /**
     * 在 {@code List<Integer>}, {@code List<Number>}, {@code List<Object>} 集合的尾部添加数字
     * @param list
     */
    public static void addNumber(List<? super Integer> list) {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }
}
