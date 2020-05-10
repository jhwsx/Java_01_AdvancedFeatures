package com.java.advanced.features.generics.tutorial.t08_type_erasure._02_erasure_of_generic_methods;

/**
 * @author wangzhichao
 * @since 2020/4/25
 */
class Shape {}

class Circle extends Shape {
}

class Rectangle extends Shape {
}
public class Test3 {
    public static void main(String[] args) {

    }

    public static <T extends Shape> void draw(T shape) {

    }
}
