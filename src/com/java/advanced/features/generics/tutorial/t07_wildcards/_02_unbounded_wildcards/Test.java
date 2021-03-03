package com.java.advanced.features.generics.tutorial.t07_wildcards._02_unbounded_wildcards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 什么时候使用无界通配符？
 *
 * 1.If you are writing a method that can be implemented using functionality provided in the Object class.
 *
 * 2.When the code is using methods in the generic class that don't depend on the type parameter.
 * For example, List.size or List.clear. In fact, Class<?> is so often used because most of the methods
 * in Class<T> do not depend on T.
 *
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

        // List<?> 和 List<? extends Object> 是完全等价的。
        List<? extends Object> list1 = new ArrayList<>();
        list1.add(null);
//        list1.add(1); // error
//        list1.add("hello"); // error
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
