package com.java.advanced.features.generics.tutorial.t01_why_use_generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2020/4/23
 */
public class GenericsTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("hello");
        String s = list.get(0); // no cast
    }

}
