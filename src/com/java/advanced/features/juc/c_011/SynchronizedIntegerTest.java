package com.java.advanced.features.juc.c_011;

/**
 *
 * @author wangzhichao
 * @since 2022/2/20
 */
public class SynchronizedIntegerTest implements Runnable {
    private int count = 100;
    @Override
    public void run() {
        synchronized (buildLock()) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    private Integer buildLock() {
        return Integer.valueOf(10);
    }

    public static void main(String[] args) {
        SynchronizedIntegerTest t = new SynchronizedIntegerTest();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "THREAD" + i).start();
        }
    }
}
