package com.java.advanced.features.juc.class4;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @author wangzhichao
 * @since 2021/4/20
 */
public class MyContainer1<T> {
    private final LinkedList<T> list = new LinkedList<>();
    private static final int MAX = 10;

    // 生产者
    public synchronized void put(T t) {
        while (list.size() == MAX) {
            // 如果容器已经满了，就挂起线程，释放锁。
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        // 唤醒消费者线程
        notifyAll();
    }

    // 消费者
    public synchronized T get() {
        T result = null;
        while (list.isEmpty()) {
            // 如果容器已经空了，就挂起消费者线程，释放锁。
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = list.removeFirst();
        notifyAll();
        return result;
    }

    public static void main(String[] args) {
        MyContainer1<String> container = new MyContainer1<>();
        // 启动 10 个消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(container.get());
                }
            }, "consumer" + i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    container.put(Thread.currentThread().getName() + " " + j);
                }
            }, "producer" + i).start();
        }
    }
}
