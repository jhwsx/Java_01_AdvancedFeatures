package com.java.advanced.features.homework._20200423_generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2020/4/26
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        addNumber(integerList);
        System.out.println(integerList);
    }

    public static void addNumber(List<? super Integer> list) {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }
}
