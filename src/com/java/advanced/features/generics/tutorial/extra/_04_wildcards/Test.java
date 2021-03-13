package com.java.advanced.features.generics.tutorial.extra._04_wildcards;

import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2021/3/4
 */
public class Test {

    public static void main(String[] args) {

        // 无界通配符
        Collection<?> c = new ArrayList<>();
        // c.add(new Object()); // 编译报错



        Canvas canvas = new Canvas();
        List<Circle> circleList = new ArrayList<>();
        List<Rectangle> rectangleList = new ArrayList<>();
        List<Shape> shapeList = new ArrayList<>();
        // canvas.drawAll1(circleList); // 编译报错：类型不匹配
        // canvas.drawAll1(rectangleList); // 编译报错：类型不匹配
        canvas.drawAll1(shapeList); // OK

        // 有界通配符

        canvas.drawAll2(circleList); // OK
        canvas.drawAll2(rectangleList); // OK
        canvas.drawAll2(shapeList); // OK
    }

    void printCollection1(Collection c) {
        Iterator iterator = c.iterator();
        for (int i = 0; i < c.size(); i++) {
            System.out.println(iterator.next());
        }
    }
    // 只能传入 Object 元素
    void printCollection2(Collection<Object> c) {
        for (Object o : c) {
            System.out.println(o);
        }
    }

    // 可以传入任何元素
    void printCollection3(Collection<?> c) {
        for (Object o : c) {
            System.out.println(o);
        }
    }

    void addRectangle(List<? extends Shape> shapes) {
        // 编译报错：因为 Shapes 的元素类型是 Shape 类型或其子类类型，不确定是哪个类型。所以，直接往里面添加 Rectangle 对象是不安全的。
        // 假如可以添加 Rectangle 类型，而传递给 shapes 的对象是 List<Circle> 类型，所以就把 Rectangle 对象添加到了 List<Circle> 里面，
        // 这显然是不可能的，所以不可以添加 Rectangle 类型给 shapes。
        // shapes.add(new Rectangle());
    }
}
