package com.java.advanced.features.juc.class6;

import java.util.PriorityQueue;

/**
 * @author wangzhichao
 * @since 2021/4/27
 */
public class T07_01_PriorityQueue {
    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue<>();

        q.add("c");
        q.add("e");
        q.add("a");
        q.add("d");
        q.add("z");

        for (int i = 0; i < 5; i++) {
            System.out.println(q.poll());
        }
    }
}
/*
打印顺序：
 a
 c
 d
 e
 z
 */