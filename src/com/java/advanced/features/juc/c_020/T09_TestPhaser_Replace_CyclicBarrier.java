package com.java.advanced.features.juc.c_020;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 使用 Phaser 实现 T07_TestCyclicBarrier_2 例子
 *
 * @author wangzhichao
 * @since 2022/2/21
 */
public class T09_TestPhaser_Replace_CyclicBarrier {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("phase=" + phase + ", registeredParties=" + registeredParties);
                System.out.println("所有的数据都拿到了！！！");
                return true;
            }
        };

        new Thread(() -> {
            System.out.println("执行网络请求 start");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行网络请求 end");
            phaser.arriveAndAwaitAdvance();
        }).start();

        new Thread(() -> {
            System.out.println("执行读文件 start");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行读文件 end");
            phaser.arriveAndAwaitAdvance();
        }).start();

        new Thread(() -> {
            System.out.println("执行查数据库 start");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行查数据库 end");
            phaser.arriveAndAwaitAdvance();
        }).start();
        phaser.awaitAdvance(0);
        System.out.println("main end");
    }
}
