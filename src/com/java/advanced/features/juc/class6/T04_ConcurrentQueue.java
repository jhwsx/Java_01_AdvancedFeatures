package com.java.advanced.features.juc.class6;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author wangzhichao
 * @since 2021/4/27
 */
public class T04_ConcurrentQueue {
    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 10; i++) {
            strs.offer("a" + i);
        }

        System.out.println(strs);

        System.out.println(strs.size());

        System.out.println(strs.poll());

        System.out.println(strs.size());

        System.out.println(strs.peek());

        System.out.println(strs.size());
    }
}
