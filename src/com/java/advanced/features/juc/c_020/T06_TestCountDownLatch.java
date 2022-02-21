package com.java.advanced.features.juc.c_020;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 倒数门栓
 *
 * @author wangzhichao
 * @since 2020/3/31
 */
public class T06_TestCountDownLatch {
    public static void main(String[] args) {
        usingJoin();
        System.out.println("------------------------------------");
        usingCountDownLatch();
    }

    private static void usingCountDownLatch() {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) {
                    result += j;
                }
                System.out.println(Thread.currentThread().getName() + ", result = " + result);
                latch.countDown();
            }, "t-" + i);
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        // 等 latch 的 数字变为 0 了，就不在等待在这里了，就会继续往下执行了。
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end countdownlatch");
    }

    private static void usingJoin() {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) {
                    result += j;
                }
                System.out.println(Thread.currentThread().getName() + ", result = " + result);
            }, "t-" + i);
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        // 等调用 join() 的线程结束了，再往下执行。
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("end join");
    }
}
