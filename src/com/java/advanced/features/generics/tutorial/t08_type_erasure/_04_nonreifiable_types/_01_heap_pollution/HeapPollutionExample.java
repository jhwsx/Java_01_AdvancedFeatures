package com.java.advanced.features.generics.tutorial.t08_type_erasure._04_nonreifiable_types._01_heap_pollution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Heap pollution 什么时候发生？
 * Heap pollution occurs when a variable of a parameterized type refers to an object that is not of that parameterized type.
 *
 * @author wangzhichao
 * @since 2020/4/26
 */
public class HeapPollutionExample {
    public static void main(String[] args) {
        List<String> stringListA = new ArrayList<>();
        List<String> stringListB = new ArrayList<>();

        ArrayBuilder.addToList(stringListA, "Seven", "Eight", "Nine");
        ArrayBuilder.addToList(stringListB, "Ten", "Eleven", "Twelve");

        List<List<String>> listOfStringLists = new ArrayList<>();
        ArrayBuilder.addToList(listOfStringLists, stringListA, stringListB);

        ArrayBuilder.faultyMethod(Arrays.asList("Hello!"), Arrays.asList("World!"));
    }
}

/*
D:\Workspace\Java_01_AdvancedFeatures\src>javac -Xlint:unchecked -encoding utf-8 com/java/advanced/features/generics/tutorial/t08_type_erasure/_04_nonreifiable_types/_01_heap_pollution/
HeapPollutionExample.java
com\java\advanced\features\generics\tutorial\t08_type_erasure\_04_nonreifiable_types\_01_heap_pollution\HeapPollutionExample.java:23: 警告: [unchecked] 对于类型为List<String>[]的 vararg
s 参数, 泛型数组创建未经过检查
        ArrayBuilder.addToList(listOfStringLists, stringListA, stringListB);
                              ^
com\java\advanced\features\generics\tutorial\t08_type_erasure\_04_nonreifiable_types\_01_heap_pollution\HeapPollutionExample.java:25: 警告: [unchecked] 对于类型为List<String>[]的 vararg
s 参数, 泛型数组创建未经过检查
        ArrayBuilder.faultyMethod(Arrays.asList("Hello!"), Arrays.asList("World!"));
                                 ^
2 个警告


 */