package com.java.advanced.features.juc.c_020;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 是可重入锁
 *
 * @author wangzhichao
 * @since 2020/3/31
 */
public class T01_ReentrantLock1 {
    synchronized void m1() {
        System.out.println("m1...........start");
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            if (i == 2) {
                m2();
            }
        }
        System.out.println("m1...........end");
    }

    synchronized void m2() {
        System.out.println("m2....start");
        System.out.println("m2....end");
    }

    public static void main(String[] args) {
        T01_ReentrantLock1 t1 = new T01_ReentrantLock1();
        t1.m1();
    }
}
