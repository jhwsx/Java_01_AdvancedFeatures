package com.java.advanced.features.generics.tutorial.t10_questions_and_exercises._02;

public final class Algorithm {
//    public static <T> T max(T x, T y) {
//        return x > y ? x : y;
//    }

    public static <T extends Comparable<T>> T max(T x, T y) {
        return x.compareTo(y) > 0 ? x : y;
    }
}