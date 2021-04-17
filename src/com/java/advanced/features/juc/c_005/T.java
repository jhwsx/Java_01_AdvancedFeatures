package com.java.advanced.features.juc.c_005;

/**
 * 分析一下这个程序的输出
 * 这个程序会出现最后输出不为 0 的情况，这是有问题的程序。
 * 加上了 volatile 之后，仍然会出现最后输出不为 0 的情况，volatile 在这里可以保证可见性，不能保证原子性。
 * 加上了 synchronized 后，就一切正常了。这时没有必要再加上 volatile 了，synchronized 既保证了原子性又保证了可见性。
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
