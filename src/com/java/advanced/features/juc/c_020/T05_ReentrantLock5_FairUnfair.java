package com.java.advanced.features.juc.c_020;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 默认是非公平锁
 * 分别用 fair = true ，false 传给 ReentrantLock 的构造，查看打印结果。
 *
 * 采用公平锁时，打印结果看到，线程0与线程1出现很多交叉打印；
 * 采用非公平锁时，打印结果看到，线程0与线程1很少出现交叉打印。
 * @author wangzhichao
 * @since 2020/3/31
 */
public class T05_ReentrantLock5_FairUnfair implements Runnable {
    private static ReentrantLock lock = new ReentrantLock(false);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T05_ReentrantLock5_FairUnfair r = new T05_ReentrantLock5_FairUnfair();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
    }
}
