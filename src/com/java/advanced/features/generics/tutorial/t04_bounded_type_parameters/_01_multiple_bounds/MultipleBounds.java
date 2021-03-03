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

class D {
}

class E<T extends A & B & C> {

}

class F<T extends B & C> {

}

//class G<T extends B & C & A> { // 编译报错：A 是类，必须写在第一位
//
//}

//class H<T extends A & D> { // 编译报错：期望 D 是接口。这是因为多个边界时，只能有一个是类。
//
//}

public class MultipleBounds {
    public static void main(String[] args) {

    }
}
