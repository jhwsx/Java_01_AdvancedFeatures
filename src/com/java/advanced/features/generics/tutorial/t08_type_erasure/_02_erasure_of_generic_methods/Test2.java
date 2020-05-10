package com.java.advanced.features.generics.tutorial.t08_type_erasure._02_erasure_of_generic_methods;

/**
 * @author wangzhichao
 * @since 2020/4/25
 */
public class Test2 {
    public static void main(String[] args) {

    }

    public static int count(Object[] anArray, Object elem) {
        int cnt = 0;
        for (Object e : anArray) {
            if (e.equals(elem)) {
                ++cnt;
            }
        }
        return cnt;
    }
}
