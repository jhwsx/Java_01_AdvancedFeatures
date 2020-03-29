package com.java.advanced.features.juc.c_005;

/**
 * 分析一下这个程序的输出
 *
 * @author wangzhichao
 * @since 2020/3/28
 */
public class T implements Runnable {
    private /*volatile*/ int count = 100;

    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        Runnable t = new T();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "Thread" + i).start();
        }
    }
}
