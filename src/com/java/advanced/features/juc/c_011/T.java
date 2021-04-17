package com.java.advanced.features.juc.c_011;

/**
 * 异常锁：程序在执行过程中，如果出现异常，默认情况锁被释放。
 *
 * 锁升级：https://www.jianshu.com/p/b43b7bf5e052
 *
 * @author wangzhichao
 * @since 2020/3/28
 */
public class T {
    int count = 0;

    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + ", m() start");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count = " + count);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count == 5) {
                int i = 1 / 0; // 此处发生异常，锁将被释放。
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        };
        new Thread(r, "t1").start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r, "t2").start();
    }
}
