package com.java.advanced.features.generics.tutorial.t04_bounded_type_parameters._02_generic_methods_and_bounded_type_parameters;

import java.io.Serializable;

/**
 * 限定的类型参数对于通用算法的实现是关键的。
 *
 * @author wangzhichao
 * @since 2020/4/23
 */
public class Util {
//    public static <T> int countGreaterThan(T[] anArray, T elem) {
//        int count = 0;
//        for (T e : anArray) {
//            if (e > elem) { // 编译报错：> 运算符不能应用于 T，只能用于基本数据类型的数值类型。
//                ++count;
//            }
//        }
//        return count;
//    }

    // 使用由 Comparable<T> 接口限定的类型参数
    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray) {
            if (e.compareTo(elem) > 0) {
                ++count;
            }
        }
        return count;
    }

    public static <T extends Comparable<T>& Runnable> int countGreaterThan2(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray) {
            if (e.compareTo(elem) > 0) {
                ++count;
            }
            e.run();
        }
        return count;
    }
}
