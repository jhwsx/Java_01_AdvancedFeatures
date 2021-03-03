package com.java.advanced.features.generics.tutorial.t06_type_inference._02_type_inference_and_instantiation_of_generic_classes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 泛型类实例化的类型推断
 *
 * @author wangzhichao
 * @since 2020/4/23
 */
public class Test {
    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        // 钻石表示法，也是类型推断
        Map<String, List<String>> myMap = new HashMap<>();
        Map<String, List<String>> map2 = new HashMap(); // 这里会有警告
    }
}
