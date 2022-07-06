package com.java.advanced.features.juc.class4;

/**
 * 使用自旋的方式，即添加一个 t2Started 的标记，来保证 t2 在 t1 之前打印。
 *
 * @author wangzhichao
 * @since 2021/4/28
 */
public class AlternatePrint_Sync_Wait_Notify_2 {

    private static Thread t1;
    private static Thread t2;
    private static volatile boolean t2Started = false;
    public static void main(String[] args) {
        final Object lock = new Object();
        t1 = new Thread(() -> {
            synchronized (lock) {

                while (!t2Started) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
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
                    t2Started = true;
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
