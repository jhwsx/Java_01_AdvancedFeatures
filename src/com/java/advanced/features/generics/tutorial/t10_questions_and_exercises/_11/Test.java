package com.java.advanced.features.generics.tutorial.t10_questions_and_exercises._11;

/**
 * Consider this class:
 * class Node<T> implements Comparable<T> {
 *     public int compareTo(T obj) {  }
 *     // ...
 * }
 * Will the following code compile? If not, why?
 *   Node<String> node = new Node<>();
 *   Comparable<String> comp = node;
 *
 * 可以编译。Node<String> 是 Comparable<String> 的子类型。
 *
 * @author wangzhichao
 * @since 2021/3/4
 */
public class Test {
    public static void main(String[] args) {
        Node<String> node = new Node<>();
        Comparable<String> comp = node;
    }
}

class Node<T> implements Comparable<T> {

    @Override
    public int compareTo(T o) {
        return 0;
    }
}


