package com.java.advanced.features.generics.tutorial.t10_questions_and_exercises._10;

/**
 * Given the following classes:
 * class Shape {  }
 * class Circle extends Shape { }
 * class Rectangle extends Shape { }
 * class Node<T> { }
 * <p>
 * Will the following code compile? If not, why?
 * <p>
 * Node<Circle> nc = new Node<>();
 * Node<Shape>  ns = nc;
 * <p>
 * 不能，Node<Shape> 和 Node<Circle> 不存在子类化关系。
 *
 * @author wangzhichao
 * @since 2021/3/4
 */
public class Test {

    public static void main(String[] args) {
        Node<Circle> nc = new Node<>();
//        Node<Shape> ns = nc; // 编译报错。
    }
}

class Shape {
}

class Circle extends Shape {
}

class Rectangle extends Shape {
}

class Node<T> {
}


