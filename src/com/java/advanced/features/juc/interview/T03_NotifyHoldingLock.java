package com.java.advanced.features.juc.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 采用 synchronized， wait/notify
 * 这个程序不成功
 *
 * @author wangzhichao
 * @since 2020/4/8
 */
public class T03_NotifyHoldingLock {
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T03_NotifyHoldingLock c = new T03_NotifyHoldingLock();
        // 锁，加 final 是一个好的习惯。
        final Object lock = new Object();
        // 我们让 t2 线程先启动
        new Thread(() -> {
            // 如果 size 不等于 5，就处于等待状态
            synchronized (lock) {
                System.out.println("t2 启动");
                if (c.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
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
                        lock.notify(); // 注意啊：虽然 notify 了，但是并不释放锁，只有代码走到 synchronized 的最后一行时才会释放锁。
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
