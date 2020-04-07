package com.java.advanced.features.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 的 tryLock 进行尝试锁定，synchronized 不具备这个功能。
 *
 * @author wangzhichao
 * @since 2020/3/31
 */
public class T03_ReentrantLock3 {
    private Lock lock = new ReentrantLock();
    // 3 秒钟才可以把代码执行完毕
    void m1() {
        System.out.println("m1 ...... start");
        lock.lock();
        try {
            for (int i = 0; i < 3; i++) {
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println("m1 ...... end");
    }

    void m2() {
        System.out.println("m2 ... start");
//        boolean locked = false;
        // tryLock 没有等待时间的用法
//        try {
//            // tryLock: 仅在调用时锁未被另一个线程保持的情况下，才获取该锁。
//            locked = lock.tryLock();
//            System.out.println("m2 ..." + locked);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (locked) {
//                lock.unlock();
//            }
//        }
        // tryLock 有等待时间的用法
        boolean locked = false;
        try {
            // tryLock(long timeout, TimeUnit unit): 如果锁在给定等待时间内没有被另一个线程保持，且当前线程未被中断，则获取该锁。
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2 ..." + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
        System.out.println("m2 ... end");
    }

    public static void main(String[] args) {
        T03_ReentrantLock3 r1 = new T03_ReentrantLock3();
        new Thread(r1::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r1::m2).start();
    }
}
