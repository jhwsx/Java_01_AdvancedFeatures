package com.java.advanced.features.juc.c_001;

/**
 * synchronized 关键字
 * 对某个对象加锁
 *
 * @author wangzhichao
 * @since 2020/3/28
 */
public class T {
    private int count = 10;
    private Object o = new Object();

    public void m() {
        synchronized (o) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }
}
