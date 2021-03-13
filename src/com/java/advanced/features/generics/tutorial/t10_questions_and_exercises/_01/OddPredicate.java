package com.java.advanced.features.generics.tutorial.t10_questions_and_exercises._01;

/**
 * @author wangzhichao
 * @since 2021/3/4
 */
public class OddPredicate implements UnaryPredicate<Integer> {
    @Override
    public boolean test(Integer obj) {
        return obj % 2 != 0;
    }
}
