package com.java.advanced.features.juc.c_020;

import java.util.concurrent.Phaser;

/**
 * 使用 Phaser 来实现 T06_TestCountDownLatch 的例子
 * @author wangzhichao
 * @since 2022/2/21
 */
public class T09_TestPhaser_Replace_CountDownLatch {
    public static void main(String[] args) {
        Thread[] threads = new Thread[100];
        Phaser phaser = new Phaser(threads.length) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                return false;
            }
        };
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) {
                    result += j;
                }
                System.out.println(Thread.currentThread().getName() + ", result = " + result);
                phaser.arriveAndAwaitAdvance();
            }, "t-" + i);
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        phaser.awaitAdvance(0);
        System.out.println("main end");
    }
}
