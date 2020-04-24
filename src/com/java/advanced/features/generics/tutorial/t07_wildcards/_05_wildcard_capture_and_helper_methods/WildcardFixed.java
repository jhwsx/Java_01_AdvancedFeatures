package com.java.advanced.features.generics.tutorial.t07_wildcards._05_wildcard_capture_and_helper_methods;

import java.util.List;

/**
 * @author wangzhichao
 * @since 2020/4/24
 */
public class WildcardFixed {
    void foo(List<?> i) {
        fooHelper(i);
    }

    // 创建了辅助方法，这样通配符可以通过类型推断而被捕获。
    private <T> void fooHelper(List<T> l) {
        l.set(0, l.get(0));
    }

    public static void main(String[] args) {

    }
}
