package com.java.advanced.features.generics.tutorial.t04_bounded_type_parameters._01_multiple_bounds;

/**
 * @author wangzhichao
 * @since 2020/4/23
 */
class A {
}

interface B {
}

interface C {
}

class D<T extends A & B & C> {

}

class E<T extends B & C> {

}

//class F<T extends B & C & A> { // 编译报错：A 是类，必须写在第一位
//
//}

public class MultipleBounds {
    public static void main(String[] args) {

    }
}
