package com.java.advanced.features.juc.class6;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * LinkedBlockingQueue 是用链表实现的无界队列。
 *
 * 可以指定容量，比如指定容量为1，strs.put("a" + i)；后再调用 strs.add("xxx"); 方法，
 * 就会抛出异常 java.lang.IllegalStateException: Queue full
 *
 * 不指定容量的话，容量就是 Integer.MAX_VALUE
 * @author wangzhichao
 * @since 2021/4/27
 */
public class T05_LinkedBlockingQueue {
    static BlockingQueue<String> strs = new LinkedBlockingQueue<>(1);
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
