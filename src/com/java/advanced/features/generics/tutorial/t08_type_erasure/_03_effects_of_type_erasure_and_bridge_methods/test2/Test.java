package com.java.advanced.features.generics.tutorial.t08_type_erasure._03_effects_of_type_erasure_and_bridge_methods.test2;

/**
 * 这就是 test1 包擦除后的代码
 *
 * @author wangzhichao
 * @since 2020/4/26
 */
class Node {
    public Object data;

    public Node(Object data) {
        this.data = data;
    }

    public void setData(Object data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}

class MyNode extends Node {

    public MyNode(Integer data) {
        super(data);
    }
    // 编译器生成的桥接方法
    public void setData(Object data) {
        setData((Integer) data);
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
        n.setData("Hello");
        // Integer x = (String) mn.data;
    }
}
