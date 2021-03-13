package com.java.advanced.features.generics.tutorial.t10_questions_and_exercises._01;


import java.util.Arrays;
import java.util.Collection;

/**
 * Write a generic method to count the number of elements in a collection that have a specific property
 * (for example, odd integers, prime numbers, palindromes)
 *
 * @author wangzhichao
 * @since 2021/3/4
 */
public class Test {
    public static void main(String[] args) {
        Collection<Integer> ci = Arrays.asList(1, 2, 3, 4);
        int count = Algorithm.countIf(ci, new OddPredicate());
        System.out.println("Number of odd integers: " + count);
    }
}



