package com.java.advanced.features.generics.tutorial.t10_questions_and_exercises._01;

import java.util.Collection;

public final class Algorithm {
    public static <T> int countIf(Collection<T> c, UnaryPredicate<T> p) {
        int count = 0;
        for (T t : c) {
            if (p.test(t)) {
                ++count;
            }
        }
        return count;
    }
}