package com.java.advanced.features.juc.c_020;

import java.util.concurrent.Semaphore;

/**
 * 信号量
 *
 * 作用是限流
 *
 * @author wangzhichao
 * @since 2020/3/31
 */
public class T11_TestSemaphore {
    public static void main(String[] args) {
        // 参数：permits 表示允许的数量，这里是允许 2 个。打印结果：
        //T1 running...
        //T2 running...
        //T1 running...
        //T2 running...
        // or
        //T1 running...
        //T2 running...
        //T2 running...
        //T1 running..
//        Semaphore s = new Semaphore(2);
        // 改成 1，就可以看到限流的效果了。打印结果：
        //T1 running...
        //T1 running...
        //T2 running...
        //T2 running...
        Semaphore s = new Semaphore(1);
        new Thread(() -> {
            try {
                // 从 Semaphore 获取一个许可，获取不到就阻塞在这里
                // 获取到的话，Semaphore 的许可数减 1
                s.acquire();
                System.out.println("T1 running...");
                Thread.sleep(200);
                System.out.println("T1 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放一个许可，把它还给 Semaphore，Semaphore 的许可数会加 1.
                s.release();
            }

        }).start();

        new Thread(() -> {
            try {
                s.acquire();
                System.out.println("T2 running...");
                Thread.sleep(200);
                System.out.println("T2 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }

        }).start();
    }
}
