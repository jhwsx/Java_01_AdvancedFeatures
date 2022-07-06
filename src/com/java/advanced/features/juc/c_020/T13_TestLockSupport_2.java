package com.java.advanced.features.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport 的 unpark(Thread thread) 唤醒线程演示。
 * LockSupport 的 unpark 方法的特点：
 * 1，不需要 notify 那样，必须在 synchronized 或者 ReentrantLock 中调用，
 * 可以直接在主线程调用；
 * 2，unpark 方法可以先于 park 方法执行，依然能够起到唤醒的作用。
 * @author wangzhichao
 * @since 2020/4/8
 */
public class T13_TestLockSupport_2 {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    // 当 i == 5 时，调用 LockSupport.park() 方法阻塞线程。
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
        // 注释这块代码，则 unpark 方法在 park 之前就执行了。
        /*try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        // 唤醒线程 t，需要把指定的线程传递给 LockSupport.unpark() 方法。
        LockSupport.unpark(t);
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end");
    }
}
