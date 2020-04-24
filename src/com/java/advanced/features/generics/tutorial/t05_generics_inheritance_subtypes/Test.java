package com.java.advanced.features.generics.tutorial.t05_generics_inheritance_subtypes;

import com.java.advanced.features.generics.tutorial.t04_bounded_type_parameters._00_bound.Box;

/**
 * @author wangzhichao
 * @since 2020/4/23
 */
public class Test {
    public static void main(String[] args) {
        Object someObject = new Object();
        Integer someInteger = new Integer(10);
        someObject = someInteger;

        someMethod(new Integer(10));
        someMethod(new Double(10.1));


        Box<Number> box = new Box<>();
        box.set(new Integer(10));
        box.set(new Double(10.1));

        Box<Integer> integerBox = new Box<>();
        Box<Double> doubleBox = new Box<>();
        // boxTest(integerBox); // 编译报错
        // boxTest(doubleBox); // 编译报错
        boxTest(box);
    }

    public static void boxTest(Box<Number> n) {

    }
    public static void someMethod(Number n) {

    }
}
