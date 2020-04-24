package com.java.advanced.features.generics.tutorial.t06_type_inference._03_type_inference_and_generic_constructors_of_generic_and_nongeneric_classes;

/**
 * @author wangzhichao
 * @since 2020/4/23
 */
public class MyClass<X> {
    // 构造方法是个泛型方法
    <T> MyClass(T t) {

    }

    public static void main(String[] args) {
        // 构造函数的泛型进行了类型推断为 String
        new MyClass<Integer>("");
    }
}
