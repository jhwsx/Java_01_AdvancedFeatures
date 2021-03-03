package com.java.advanced.features.generics.tutorial.t02_generic_types._04_the_diamond;

import com.java.advanced.features.generics.tutorial.t02_generic_types._02_a_generic_version_of_the_box_class.Box;

/**
 * @author wangzhichao
 * @since 2020/4/23
 */
public class Test {
    public static void main(String[] args) {
        // 类型推断，Java SE7 及其以后才有
        // 在构造方法上使用空的类型实参集合<>,这叫做菱形语法。
        Box<Integer> integerBox = new Box<>();
    }
}
