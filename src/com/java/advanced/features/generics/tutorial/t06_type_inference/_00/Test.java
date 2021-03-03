package com.java.advanced.features.generics.tutorial.t06_type_inference._00;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 类型推断推断出了返回类型，这种返回类型可以和所有的类型实参一起使用。
 *
 * @author wangzhichao
 * @since 2020/4/23
 */
public class Test {
    public static void main(String[] args) {
        Serializable s = pick("d", new ArrayList<String>());
        Serializable d = pick("d", 1);
        Number pick = pick(1, 1L);
        Object d1 = pick("d", new Object());
    }

    static <T> T pick(T a1, T a2) {
        return a2;
    }
}
