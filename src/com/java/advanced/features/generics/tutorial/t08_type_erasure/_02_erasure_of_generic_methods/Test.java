package com.java.advanced.features.generics.tutorial.t08_type_erasure._02_erasure_of_generic_methods;

/**
 * @author wangzhichao
 * @since 2020/4/25
 */
public class Test {
    public static void main(String[] args) {

    }

    public static <T> int count(T[] anArray, T elem) {
        int cnt = 0;
        for (T e : anArray) {
            if (e.equals(elem)) {
                ++cnt;
            }
        }
        return cnt;
    }
}
