package com.java.advanced.features.generics.tutorial.t05_generics_inheritance_subtypes._00;

import com.java.advanced.features.generics.tutorial.t04_bounded_type_parameters._00_bound.Box;

/**
 * @author wangzhichao
 * @since 2020/4/23
 */
public class Test {
    public static void main(String[] args) {
        Object someObject = new Object();
        Integer someInteger = new Integer(10);
        someObject = someInteger; // OK. 因为一个 Integer 是一个 Object，是 is-a 的关系。

        someMethod(new Integer(10)); // OK. 因为一个 Integer 是一个 Number，是 is-a 的关系。
        someMethod(new Double(10.1)); // OK. 因为一个 Double 是一个 Number，是 is-a 的关系。

        // 对于泛型

        Box<Number> box = new Box<>();
        box.set(new Integer(10)); // OK
        box.set(new Double(10.1)); // OK

        Box<Integer> integerBox = new Box<>();
        Box<Double> doubleBox = new Box<>();
        // boxTest(integerBox); // 编译报错, 因为 Box<Integer> 不是 Box<Number> 的子类型
        // boxTest(doubleBox); // 编译报错, 因为 Box<Double> 不是 Box<Number> 的子类型
        boxTest(box); // OK.

        boxTest2(integerBox);   // OK.
        boxTest2(doubleBox);    // OK.
        boxTest2(box);  // OK.
    }

    public static void boxTest(Box<Number> n) {

    }

    public static void boxTest2(Box n) {

    }

    public static void someMethod(Number n) {

    }
}
