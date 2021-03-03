package com.java.advanced.features.generics.tutorial.t07_wildcards._04_wildcards_and_subtyping;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用通配符在泛型类型之间建立联系
 *
 * @author wangzhichao
 * @since 2020/4/24
 */
class A {}

class B extends A {}

public class Test {
    public static void main(String[] args) {
        B b = new B();
        A a = b;

        // 在泛型里，上述关系就不存在了
        List<B> lb = new ArrayList<>();
        // List<A> la = lb; // 编译错误

        List<? extends Integer> wildcardExtendsIntegerList = new ArrayList<>();
        List<? extends Number> wildcardExtendsNumberList = wildcardExtendsIntegerList; // List<? extends Integer> 是 List<? extends Number> 的子类型
        List<?> wildcardList = null;
        List<? super Integer> wildcardSuperIntegerList = null;
        List<? super Number> wildcardSuperNumberList = null;
        List<Integer> integerList = null;
        List<Number> numberList = null;

        // 子类关系
        wildcardExtendsIntegerList = integerList;
        wildcardExtendsNumberList = wildcardExtendsIntegerList;
        wildcardList = wildcardExtendsNumberList;
        wildcardSuperIntegerList = integerList;

        wildcardSuperNumberList = numberList;
        wildcardSuperIntegerList = wildcardSuperNumberList;
        wildcardList = wildcardSuperIntegerList;
        wildcardExtendsNumberList = numberList;


    }
}
