package com.java.advanced.features.juc.c_012_volatile;

/**
 * volatile 关键字的作用
 * 这个小程序如果不加 volatile 关键字，那么死循环无法结束；加 volatile 关键字，死循环可以结束。
 *
 * 参考：
 * 程序员小灰的文章：
 * https://mp.weixin.qq.com/s/DZkGRTan2qSzJoDAx7QJag
 * @author wangzhichao
 * @since 2020/3/28
 */
public class T01_HelloVolatile {
    /*volatile*/ boolean running = true;

    void m() {
        System.out.println("m start");
        while (running) {
            // 为什么下面这行打印加上后，即便不把 running 变量加上 volatile，也可以结束循环？
            // 因为 println 里用到了 sychronized 来同步，而 sychronized 的特点是
            // 线程在加锁时，先清空工作内存--> 在主内存中拷贝最新变量的副本到工作内存--> 执行完代码
            // 将更改后的共享变量的值刷新到主内存中 --> 释放互斥锁。
            // System.out.println("server is running");
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        T01_HelloVolatile t = new T01_HelloVolatile();
        new Thread(() -> t.m(), "t1").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}
