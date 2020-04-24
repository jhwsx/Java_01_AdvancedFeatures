package com.java.advanced.features.generics.tutorial.t02_generic_types._06_raw_types;

import com.java.advanced.features.generics.tutorial.t02_generic_types._02_a_generic_version_of_the_box_class.Box;

/**
 * @author wangzhichao
 * @since 2020/4/23
 */
public class Test {
    public static void main(String[] args) {
        // 创建 Box 的参数化类型，给形式的类型参数 T 提供了一个实际的类型参数。
        Box<Integer> integerBox = new Box<>();
        // 如果实际的类型参数被省略了，那么创建了 Box<T> 的原始类型
        Box rawBox = new Box();
        // Box 是泛型类 Box<T> 的原始类型。但是，一个非泛型的类或者接口不是原始类型。

        // 允许把参数化类型赋值给原生类型
        Box<String> stringBox = new Box<>();
        Box rawBox2 = stringBox;

        // 把原生类型赋值给一个参数化类型，会得到一个警告
        Box rawBox3 = new Box(); // rawBox3 is a raw type of Box<T>
        Box<Integer> intBox = rawBox3; // warning: unchecked assignment.

        // 使用原生类型来调用对应的泛型类型的泛型方法，也会得到一个警告
        rawBox2.set(8); // warning: unchecked call.
    }
}
