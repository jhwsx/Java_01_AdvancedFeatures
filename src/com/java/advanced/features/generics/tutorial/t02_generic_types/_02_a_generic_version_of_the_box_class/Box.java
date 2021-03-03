package com.java.advanced.features.generics.tutorial.t02_generic_types._02_a_generic_version_of_the_box_class;

/**
 * 类型参数不可以指定为基本数据类型
 *
 * @author wangzhichao
 * @since 2020/4/23
 */
public class Box<T> {
    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
}
