package com.java.advanced.features.juc.interview;

import java.util.concurrent.locks.LockSupport;

/**
 * @author wangzhichao
 * @since 2021/4/28
 */
public class AlternatePrint_LockSupport {

    private static Thread t1;
    private static Thread t2;

    public static void main(String[] args) {
        t1 = new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                LockSupport.park(); // 阻塞 t1
                System.out.print(i);
                LockSupport.unpark(t2); // 唤醒 t2
            }
        });
        t2 = new Thread(() -> {
            for (char i = 'A'; i <= 'Z'; i++) {
                System.out.print(i);
                LockSupport.unpark(t1); // 唤醒 t1
                LockSupport.park(); // 阻塞 t2
            }
        });
        t1.start();
        t2.start();
    }
}
