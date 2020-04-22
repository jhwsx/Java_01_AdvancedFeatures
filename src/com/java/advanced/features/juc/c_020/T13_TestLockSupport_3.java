package com.java.advanced.features.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport
 * 如果一个线程处于等待状态，连续两次调用 park() 方法，就会使线程永远无法被唤醒。
 * 参考文章：https://www.cnblogs.com/takumicx/p/9328459.html
 *
 * @author wangzhichao
 * @since 2020/4/8
 */
public class T13_TestLockSupport_3 {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    // 当 i == 5 时，调用 LockSupport.park() 方法阻塞线程。
                    LockSupport.park();
                }
                if (i == 8) {
                    // 当 i == 8 时，调用 LockSupport.park() 方法阻塞线程。
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        LockSupport.unpark(t);
    }
}
