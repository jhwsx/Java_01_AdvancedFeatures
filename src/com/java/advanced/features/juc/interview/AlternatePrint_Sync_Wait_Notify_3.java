package com.java.advanced.features.juc.interview;

import java.util.concurrent.CountDownLatch;

/**
 * 使用自旋的方式，即添加一个 t2Started 的标记，来保证 t2 在 t1 之前打印。
 *
 * @author wangzhichao
 * @since 2021/4/28
 */
public class AlternatePrint_Sync_Wait_Notify_3 {

    private static Thread t1;
    private static Thread t2;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) {
        final Object lock = new Object();
        t1 = new Thread(() -> {
            try {
                // 注意啊：这行代码不能放在同步代码块中；否则，t1 会拿到 lock，不释放的。
                countDownLatch.await(); // 倒数门栓还没打开，就等着吧；打开后，就执行下面的代码。
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
//                try {
//                    // 注意啊：这行代码不能放在同步代码块中
//                    countDownLatch.await(); // 倒数门栓还没打开，就等着吧；打开后，就执行下面的代码。
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
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
                    if (countDownLatch.getCount() >= 1) {
                        countDownLatch.countDown(); // 我执行了一次了。
                    }
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
