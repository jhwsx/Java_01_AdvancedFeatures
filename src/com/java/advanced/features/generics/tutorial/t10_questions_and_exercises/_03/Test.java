package com.java.advanced.features.generics.tutorial.t10_questions_and_exercises._03;

import java.util.Arrays;

/**
 * Write a generic method to exchange the positions of two different elements in an array.
 *
 * @author wangzhichao
 * @since 2021/3/4
 */
public class Test {
    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4};
        Algorithm.swap(array, 1,2);
        System.out.println(Arrays.toString(array));
    }
}

class Algorithm {
    public static <T> void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
