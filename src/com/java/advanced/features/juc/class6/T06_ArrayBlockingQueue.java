package com.java.advanced.features.juc.class6;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue 是由数组支持的有界队列。
 *
 * 可以指定容量；
 * 可以指定可选的公平策略
 *
 * @author wangzhichao
 * @since 2021/4/27
 */
public class T06_ArrayBlockingQueue {
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(1, true);
    static Random r = new Random();

    public static void main(String[] args) {
        // 一个生产者任务
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    strs.put("a" + i);
                    // 如果没有休眠时间，消费打印顺序就不一定是生产顺序了。
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "p1").start();
        // 5 个消费者任务
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (;;) { // 一直去取
                    try {
                        System.out.println(Thread.currentThread().getName() + " take - " + strs.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c" + i).start();
        }
    }
}
