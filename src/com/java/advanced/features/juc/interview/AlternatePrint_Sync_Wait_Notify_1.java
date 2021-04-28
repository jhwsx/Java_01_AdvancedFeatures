package com.java.advanced.features.juc.interview;

/**
 * 这个例子不能保证t2先执行
 *
 * @author wangzhichao
 * @since 2021/4/28
 */
public class AlternatePrint_Sync_Wait_Notify_1 {

    private static Thread t1;
    private static Thread t2;

    public static void main(String[] args) {
        final Object lock = new Object();
        t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i <= 26; i++) {
                    System.out.print(i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }

        });
        t2 = new Thread(() -> {
            synchronized (lock) {
                for (char i = 'A'; i <= 'Z'; i++) {
                    System.out.print(i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }

        });
        t1.start();
        t2.start();
    }
}
