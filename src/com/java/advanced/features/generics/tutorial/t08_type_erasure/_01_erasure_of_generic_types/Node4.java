package com.java.advanced.features.generics.tutorial.t08_type_erasure._01_erasure_of_generic_types;

/**
 * Node4 是 Node3 泛型擦除后的样子
 * @author wangzhichao
 * @since 2020/4/25
 */
public class Node4 {
    private Comparable data;
    private Node4 next;

    public Node4(Comparable data, Node4 next) {
        this.data = data;
        this.next = next;
    }

    public Comparable getData() {
        return data;
    }
}
