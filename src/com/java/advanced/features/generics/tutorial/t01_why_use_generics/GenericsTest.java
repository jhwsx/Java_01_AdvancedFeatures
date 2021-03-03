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
        // 使用了泛型，所以取出数据时不用进行类型强转
        String s = list.get(0); // no cast
        // 使用了泛型，有类型检查
        // Required type: String
        // Provided: int
        // list.add(1);
    }

}
