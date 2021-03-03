package com.java.advanced.features.generics.tutorial.t02_generic_types._03_invoking_and_instantiating_generic_type;

import com.java.advanced.features.generics.tutorial.t02_generic_types._02_a_generic_version_of_the_box_class.Box;

/**
 * An invocation of a generic type is generally known as a parameterized type.
 *
 * Box<Integer> 这种叫做参数化类型
 *
 * @author wangzhichao
 * @since 2020/4/23
 */
public class Test {
    public static void main(String[] args) {
        // 调用泛型类型，把原来位置的 T 替换为具体的类型
        Box<Integer> integerBox;
        // 实例化泛型类
        Box<Float> floatBox = new Box<Float>();
    }
}
