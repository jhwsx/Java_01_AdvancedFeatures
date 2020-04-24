package com.java.advanced.features.generics.tutorial.t01_why_use_generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2020/4/23
 */
public class NonGenericsTest {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("hello");
        String s = (String) list.get(0);
    }

}
