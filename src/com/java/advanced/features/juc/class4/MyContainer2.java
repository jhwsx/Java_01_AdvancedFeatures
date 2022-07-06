package com.java.advanced.features.juc.class4;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangzhichao
 * @since 2021/4/20
 */
public class MyContainer2<T> {
    private final LinkedList<T> list = new LinkedList<>();
    private static final int MAX = 10;
    private final Lock lock = new ReentrantLock();
    private final Condition producerCondition = lock.newCondition();
    private final Condition consumerCondition = lock.newCondition();
    // 生产者
    public  void put(T t) {
        lock.lock();
        try {
            while (list.size() == MAX) {
                // 如果容器已经满了，就挂起生成者线程，释放锁。
                try {
                    producerCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(t);
            // 唤醒消费者线程
            consumerCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    // 消费者
    public T get() {
        T result = null;
        lock.lock();
        try {
            while (list.isEmpty()) {
                // 如果容器已经空了，就挂起消费者线程，释放锁。
                try {
                    consumerCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            result = list.removeFirst();
            producerCondition.signalAll();
        } finally {
            lock.unlock();
        }
        return result;
    }

    public static void main(String[] args) {
        MyContainer2<String> container = new MyContainer2<>();
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
