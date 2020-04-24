package com.java.advanced.features.generics.tutorial.t07_wildcards._04_wildcards_and_subtyping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2020/4/24
 */
class A {}

class B extends A {}

public class Test {
    public static void main(String[] args) {
        B b = new B();
        A a = b;

        // 在泛型里，上述关系就存在了
        List<B> lb = new ArrayList<>();
        // List<A> la = lb; // 编译错误

        List<? extends Integer> intList = new ArrayList<>();
        List<? extends Number> numList = intList; // List<? extends Integer> 是 List<? extends Number> 的子类型
    }
}
