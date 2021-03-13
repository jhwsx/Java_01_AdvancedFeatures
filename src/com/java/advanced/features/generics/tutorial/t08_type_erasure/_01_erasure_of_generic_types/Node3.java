package com.java.advanced.features.generics.tutorial.t08_type_erasure._01_erasure_of_generic_types;


/**
 * Node3 泛型擦除后的形式是 Node4 类
 * @author wangzhichao
 * @since 2020/4/25
 */
public class Node3<T extends Comparable<T>> {
    private T data;
    private Node3<T> next;

    public Node3(T data, Node3<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }
}
