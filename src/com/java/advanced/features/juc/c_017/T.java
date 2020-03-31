package com.java.advanced.features.juc.c_017;

import java.util.concurrent.TimeUnit;

/**
 * 以对象作为锁，不能把对象变成别的对象，这样无法同步
 *
 * @author wangzhichao
 * @since 2020/3/30
 */
public class T {
    final Object o = new Object();

    void m() {
        synchronized (o) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t2 = new Thread(t::m, "t2");

//        t.o = new Object();

        t2.start();
    }
}
