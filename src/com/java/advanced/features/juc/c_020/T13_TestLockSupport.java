package com.java.advanced.features.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport 的 park() 阻塞线程演示。
 * 不用像 wait 那样，必须在 synchronized 中或者先 .lock() 。
 *
 * @author wangzhichao
 * @since 2020/4/8
 */
public class T13_TestLockSupport {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    // 当 i == 5 时，调用 LockSupport.park() 方法阻塞线程。
                    LockSupport.park();
                    // 使用 synchronized 要这样写
//                    synchronized (T13_TestLockSupport.class) {
//                        try {
//                            T13_TestLockSupport.class.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t.join();
        System.out.println("main end");
    }
}
