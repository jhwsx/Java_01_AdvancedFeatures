package com.java.advanced.features.generics.tutorial.t08_type_erasure._03_effects_of_type_erasure_and_bridge_methods.test1;

/**
 * @author wangzhichao
 * @since 2020/4/26
 */
class Node<T> {
    public T data;

    public Node(T data) {
        this.data = data;
    }

    public void setData(T data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}

class MyNode extends Node<Integer> {

    public MyNode(Integer data) {
        super(data);
    }

    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
}

public class Test {
    public static void main(String[] args) {
        MyNode mn = new MyNode(5);
        Node n = mn;
        n.setData("Hello"); // 调用的是 setData(Object data) 方法，这一点可以 ctrl + p 看到
    }
}

