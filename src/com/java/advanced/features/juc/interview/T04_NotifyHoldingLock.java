package com.java.advanced.features.juc.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 采用 synchronized， wait/notify
 * 这个程序是成功的
 *
 * @author wangzhichao
 * @since 2020/4/8
 */
public class T04_NotifyHoldingLock {
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T04_NotifyHoldingLock c = new T04_NotifyHoldingLock();
        // 锁，加 final 是一个好的习惯。
        final Object lock = new Object();
        // 我们让 t2 线程先启动
        new Thread(() -> {
            System.out.println("t2 启动");
            // 如果 size 不等于 5，就处于等待状态
            synchronized (lock) {
                if (c.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
                // 唤醒线程1继续执行吧。
                lock.notify();
            }
        }, "t2").start();
        // 休眠 1 秒，是保证线程 t1 一定在 t2 之后启动。
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            System.out.println("t1 启动");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);
                    if (c.size() == 5) {
                        lock.notify(); // 虽然 notify 里，但是并不释放锁。
                        try {
                            lock.wait(); // 挂起当前线程，释放锁
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, "t1").start();


    }
}
