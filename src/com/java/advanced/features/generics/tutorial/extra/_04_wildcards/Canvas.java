package com.java.advanced.features.generics.tutorial.extra._04_wildcards;

import java.util.List;

/**
 * @author wangzhichao
 * @since 2021/3/4
 */
public class Canvas {
    public void draw(Shape s) {
        s.draw(this);
    }

    // 只能接收 Shape 类型元素的集合
    public void drawAll1(List<Shape> shapes) {
        for (Shape shape : shapes) {
            shape.draw(this);
        }
    }

    // 可以接收 Shape 类型以及其子类型元素的集合
    public void drawAll2(List<? extends Shape> shapes) {
        for (Shape shape : shapes) {
            shape.draw(this);
        }
    }
}
