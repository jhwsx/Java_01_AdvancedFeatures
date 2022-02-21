package com.java.advanced.features.juc.c_011;

/**
 * 记一次synchronized锁字符串引发的坑兼再谈Java字符串
 * https://www.cnblogs.com/xrq730/p/6662232.html
 * @author wangzhichao
 * @since 2022/2/20
 */
public class SynchronizedStringTest implements Runnable {
    private int count = 100;
    @Override
    public void run() {
        synchronized (buildLock()) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    private String buildLock() {
        String lock = new StringBuilder().append("lock").toString();
        System.out.println("lock:" + lock.hashCode());
        return lock;
    }

    public static void main(String[] args) {
        SynchronizedStringTest t = new SynchronizedStringTest();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "THREAD" + i).start();
        }
    }
}
