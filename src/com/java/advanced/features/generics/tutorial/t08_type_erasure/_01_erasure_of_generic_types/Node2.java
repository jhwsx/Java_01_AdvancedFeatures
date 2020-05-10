package com.java.advanced.features.generics.tutorial.t08_type_erasure._01_erasure_of_generic_types;

/**
 * Node2 是 Node1 泛型擦除后的样子
 * @author wangzhichao
 * @since 2020/4/25
 */
public class Node2 {
    private Object data;
    private Node2 next;

    public Node2(Object data, Node2 next) {
        this.data = data;
        this.next = next;
    }

    public Object getData() {
        return data;
    }
}
