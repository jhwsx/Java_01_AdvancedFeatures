package com.java.advanced.features.generics.genericmethod;

import java.util.ArrayList;
import java.util.List;

public class GenericMethodVarargs {
    public static <T> List<T> toList(T... args) {
        List<T> result = new ArrayList<>();
        for (T item : args) {
            result.add(item);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(GenericMethodVarargs.toList(1, 2, 3, 4));
        System.out.println(GenericMethodVarargs.toList("a", "b", "c", "d", "e"));
        // 在这里，可以体会显式的类型说明的作用
        // 本来，我们是传入一个都是 String 类型元素的可变参数列表，但是有一个不和谐的 int 类型：88，也被传入了，但是编译器没有提示出问题。
        System.out.println(GenericMethodVarargs.toList("h", "e", "l", "l", "o", 88));
        // 如果采用显式的类型说明，编译器就会给出出错的提示: 期望的是 String 类型元素的可变参数列表，但是实际上里面包含了 int 类型。
        // System.out.println(GenericMethodVarargs.<String>toList("h", "e", "l", "l", "o", 88));
    }
}
/*
打印结果：
[1, 2, 3, 4]
[a, b, c, d, e]
[h, e, l, l, o, 88]
 */