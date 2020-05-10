package com.java.advanced.features.generics.tutorial.t08_type_erasure._01_erasure_of_generic_types;

/**
 * @author wangzhichao
 * @since 2020/4/25
 */
public class Node1<T> {
    private T data;
    private Node1<T> next;

    public Node1(T data, Node1<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public static void main(String[] args) {

    }
}
