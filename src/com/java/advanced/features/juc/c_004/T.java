package com.java.advanced.features.juc.c_004;

/**
 * synchronized 关键字
 * 对类对象加锁
 *
 * @author wangzhichao
 * @since 2020/3/28
 */
public class T {
    private static int count = 10;

    public static synchronized void m() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void mm() {
        synchronized (T.class) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }
}
