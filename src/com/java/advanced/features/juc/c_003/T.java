package com.java.advanced.features.juc.c_003;

/**
 * synchronized 关键字
 * 对当前对象加锁
 *
 * @author wangzhichao
 * @since 2020/3/28
 */
public class T {
    private int count = 10;

    public synchronized void m() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }
}
