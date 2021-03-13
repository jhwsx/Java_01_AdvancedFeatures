package com.java.advanced.features.generics.tutorial.t10_questions_and_exercises._07;

import java.util.Arrays;
import java.util.List;

/**
 * Will the following method compile? If not, why?
 *
 * public static void print(List<? extends Number> list) {
 *     for (Number n : list)
 *         System.out.print(n + " ");
 *     System.out.println();
 * }
 *
 * @author wangzhichao
 * @since 2021/3/4
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4);
        print(integerList);
    }

    public static void print(List<? extends Number> list) {
        for (Number n : list)
            System.out.print(n + " ");
        System.out.println();
    }
}
