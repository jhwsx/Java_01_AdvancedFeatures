package com.java.advanced.features.juc.c_012_volatile;

/**
 * @author wangzhichao
 * @since 2020/3/28
 */
public class T01_HelloVolatile {
    boolean running = true;
    void m() {
        System.out.println("m start");
        while (running) {
            System.out.println("server is running");
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
