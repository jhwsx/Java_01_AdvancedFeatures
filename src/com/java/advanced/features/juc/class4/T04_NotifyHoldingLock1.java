package com.java.advanced.features.juc.class4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 这个程序有问题，不能保证线程 2 在 线程 1 添加 5 个元素后结束，也就是说，线程 2 在结束时，线程 1 还在添加。
 *
 * @author wangzhichao
 * @since 2022/2/22
 */
public class T04_NotifyHoldingLock1 {
    private volatile List<Integer> list = new ArrayList<>();

    public void add(int i) {
        System.out.println("add " + i);
        list.add(i);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        T04_NotifyHoldingLock1 test = new T04_NotifyHoldingLock1();
        final Object lock = new Object();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    test.add(i);
                    if (test.size() == 5) {
                        synchronized (lock) {
                            lock.notify();
                        }
                    }
                }
            }
        }, "Thread1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (test.size() != 5) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("Thread2 over");
            }
        }, "Thread2");
        thread2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
