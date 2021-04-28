package com.java.advanced.features.juc.interview;

/**
 *
 * @author wangzhichao
 * @since 2021/4/28
 */
public class AlternatePrint_MySolution {
    public static void main(String[] args) {
        Flag flag = new Flag();
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                synchronized (flag) {
                    while (flag.num % 2 == 1) { // 是奇数，就等待
                        try {
                            flag.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(i);
                    flag.num++;
                    flag.notify();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (char i = 'A'; i <= 'Z'; i++) {
                synchronized (flag) {
                    while (flag.num % 2 == 0) { // 是偶数，就等待
                        try {
                            flag.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(i);
                    flag.num++;
                    flag.notify();
                }
            }
        });
        t1.start();
        t2.start();
    }

    static class Flag {
        int num = 1;
    }
}
