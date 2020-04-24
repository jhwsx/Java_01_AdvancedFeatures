package com.java.advanced.features.generics.tutorial.t07_wildcards._01_upper_bounded_wildcards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2020/4/24
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Float> floatList = new ArrayList<>(Arrays.asList(0.1f, 0.2f, 0.3f));
        List<Double> doubleList = new ArrayList<>(Arrays.asList(1.1, 2.2, 3.3));
        process(integerList);
        process(floatList);
        process(doubleList);
        System.out.println(sumOfList(integerList));
        System.out.println(sumOfList(floatList));
        System.out.println(sumOfList(doubleList));
    }

    static void process(List<? extends Number> list) {
        System.out.println(list);
    }

    static double sumOfList(List<? extends Number> list) {
        double s = 0.0;
        for (Number n : list) {
            s += n.doubleValue();
        }
        return s;
    }
}
