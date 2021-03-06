package com.java.advanced.features.juc.c_007;

/**
 * 同步方法和非同步方法是否可以同时调用？
 * 可以。
 * @author wangzhichao
 * @since 2020/3/28
 */
public class T {
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1 start...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end...");
    }

    public void m2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2...");
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(()-> t.m1(), "t1").start();
        new Thread(() -> t.m2(), "t2").start();
    }
}

/*
打印结果：
t1 m1 start...
t2 m2...
t1 m1 end...
 */
