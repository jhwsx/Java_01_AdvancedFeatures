package com.java.advanced.features.generics.tutorial.t10_questions_and_exercises._08;

import java.util.Arrays;
import java.util.List;

/**
 * Write a generic method to find the maximal element in the range [begin, end) of a list.
 *
 * @author wangzhichao
 * @since 2021/3/4
 */
public class Test {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, 0, -1, 8, 9, 11, 2);
        System.out.println(findMaxItem(list, 0, 3));
        System.out.println(findMaxItem(list, 1, 5));
        System.out.println(findMaxItem(list, 6, 7));
    }

    public static <T extends Comparable<T>> T findMaxItem(List<T> c, int begin, int end) {

        if (begin < 0 || begin >= c.size()) {
            throw new IllegalArgumentException("begin is not valid");
        }

        if (end < 0 || end >= c.size()) {
            throw new IllegalArgumentException("end is not valid");
        }

        if (begin > end) {
            throw new IllegalArgumentException("begin cannot be greater than end.");
        }

        T max = c.get(begin);
        for (int i = begin + 1; i < end; i++) {
            T t = c.get(i);
            if (t.compareTo(max) > 0) {
                max = t;
            }
        }
        return max;
    }
}

class Algorithm {
    public static <T extends Object & Comparable<? super T>>
    T max(List<? extends T> list, int begin, int end) {

        T maxElem = list.get(begin);

        for (++begin; begin < end; ++begin)
            if (maxElem.compareTo(list.get(begin)) < 0)
                maxElem = list.get(begin);
        return maxElem;
    }
}

