package com.java.advanced.features.generics.tutorial.t07_wildcards._05_wildcard_capture_and_helper_methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO 不明白辅助方法起了什么作用？
 * https://stackoverflow.com/questions/36239642/i-am-having-some-wrong-concept-about-wildcard-in-java/36239876#36239876
 *
 * @author wangzhichao
 * @since 2020/4/24
 */
public class WildcardFixed {
    static void foo(List<?> i) {
        fooHelper(i);
    }

    // 创建了辅助方法，这样通配符可以通过类型推断而被捕获。
    private static <T> void fooHelper(List<T> l) {
        l.set(0, l.get(0));
    }

    public static void main(String[] args) {
        foo(new ArrayList<>(Arrays.asList(1, true, 3)));
    }
}
