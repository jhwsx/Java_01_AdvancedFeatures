package com.java.advanced.features.reflect.basicclass;

/**
 * @author wangzhichao
 * @since 2020/5/25
 */
public class Foo {
    static {
        System.out.println("静态代码块");
    }

    private static String staticField = initStaticField();

    private static String initStaticField() {
        System.out.println("静态字段初始化");
        return "静态字段的值";
    }
}
