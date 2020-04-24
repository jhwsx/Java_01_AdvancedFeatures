package com.java.advanced.features.generics.tutorial.t07_wildcards._02_unbounded_wildcards;

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
        List<String> stringList = new ArrayList<>(Arrays.asList("hello", "everyone"));
        // 下面三行编译不通过，类型不匹配
//        printList1(integerList);
//        printList1(floatList);
//        printList1(stringList);
        // List<Integer> 是 List<?> 的子类型
        printList2(integerList);
        printList2(floatList);
        printList2(stringList);

        // List<?> vs List<Object>
        List<?> list = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        list.add(null); // ok
        // list.add(1); // error
//        list.add("hello"); // error

        objectList.add(null); // ok
        objectList.add(1); // ok
        objectList.add("hello"); // ok
    }

    /**
     * 这个方法想要打印所有类型的集合，却不管用
     *
     * @param list
     */
    public static void printList1(List<Object> list) {
        for (Object elem : list) {
            System.out.println(elem + " ");
        }
        System.out.println();
    }

    /**
     * 这个方法可以打印所有类型的集合，使用了无界通配符
     *
     * @param list
     */
    public static void printList2(List<?> list) {
        for (Object elem : list) {
            System.out.println(elem + " ");
        }
        System.out.println();
    }
}
