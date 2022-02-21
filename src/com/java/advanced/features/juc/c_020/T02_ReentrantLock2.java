package com.java.advanced.features.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 只有 m1 执行完毕的时候，m2 才能执行。
 *
 * @author wangzhichao
 * @since 2020/3/31
 */
public class T02_ReentrantLock2 {
    private Lock lock = new ReentrantLock();

    void m1() {
        lock.lock();
        try {
            System.out.println("m1...........start");
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
            System.out.println("m1...........end");
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        lock.lock();
        try {
            System.out.println("m2....start");
            System.out.println("m2....end");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T02_ReentrantLock2 t1 = new T02_ReentrantLock2();
        new Thread(t1::m1).start();
        try {
            // 休眠 1s，保证第一个线程先拿到锁
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t1::m2).start();
    }
}
