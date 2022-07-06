package com.java.advanced.features.juc.class4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 使用 Semaphore 来实现
 *
 * 这个好牵强啊。
 *
 * @author wangzhichao
 * @since 2021/4/19
 */
public class T08_Semaphore {
    volatile List lists = new ArrayList();
    private static Thread t1;
    private static Thread t2;

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T04_NotifyHoldingLock2 c = new T04_NotifyHoldingLock2();
        // 限流，流量就是1
        Semaphore semaphore = new Semaphore(1);
        // 我们让 t1 线程先启动
        t2 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("t2 结束");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");

        t1 = new Thread(() -> {
            System.out.println("t1 启动");
            try {
                // 获取到许可
                semaphore.acquire();
                for (int i = 0; i < 5; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);
                }
                // 释放许可
                semaphore.release();
                t2.start();
                t2.join();
                // 获取到许可
                semaphore.acquire();
                for (int i = 5; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);
                }
                // 释放许可
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();
    }
}
