package com.java.advanced.features.generics.tutorial.t02_generic_types._01_a_simple_box_class;

/**
 * 一个简单的 Box 类
 * @author wangzhichao
 * @since 2020/4/23
 */
public class Box {
    private Object object;

    public void set(Object object) {
        this.object = object;
    }

    public Object get() {
        return object;
    }
}
