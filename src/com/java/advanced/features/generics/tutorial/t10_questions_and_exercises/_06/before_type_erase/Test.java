package com.java.advanced.features.generics.tutorial.t10_questions_and_exercises._06.before_type_erase;

/**
 * What is the following method converted to after type erasure?
 *
 * @author wangzhichao
 * @since 2021/3/4
 */
public class Test {
    public static <T extends Comparable<T>>
    int findFirstGreaterThan(T[] at, T elem) {
        return 0;
    }
}
