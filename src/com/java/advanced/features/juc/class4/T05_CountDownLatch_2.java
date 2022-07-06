package com.java.advanced.features.juc.class4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 采用 CountDownLatch
 * 使用两个 CountDownLatch 来配合，控制线程的执行。
 * 这个程序是成功的
 * @author wangzhichao
 * @since 2020/4/8
 */
public class T05_CountDownLatch_2 {
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T05_CountDownLatch_2 c = new T05_CountDownLatch_2();
        CountDownLatch latch = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        // 我们让 t2 线程先启动
        new Thread(() -> {
            System.out.println("t2 启动");
            // 如果 size 不等于 5，就处于等待状态
            if (c.size() != 5) {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 结束");
            latch2.countDown();
        }, "t2").start();
        // 休眠 1 秒，是保证线程 t1 一定在 t2 之后启动。
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            System.out.println("t1 启动");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);
                if (c.size() == 5) {
                    latch.countDown();
                    try {
                        latch2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }, "t1").start();
    }
}
