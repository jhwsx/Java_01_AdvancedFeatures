package com.java.advanced.features.juc.c_000;

/**
 * 线程的方法
 *
 * @author wangzhichao
 * @since 2020/3/28
 */
public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {
//         testSleep();
//         testYield();
        testJoin();
    }

    static void testSleep() {
        new Thread(() -> {
            // 每隔 500 ms，打印一次。
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                if (i % 10 == 0) {
                    Thread.yield();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("--------------B" + i);
                if (i % 10 == 0) {
                    Thread.yield();
                }
            }
        }).start();
    }

    static void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                // 在 t2 上调用 t1.join()，t2 必须等待 t1 运行完毕再继续 t2 的运行。
                t1.join(); // join() 方法的含义是等待调用本方法的线程死亡。join() 方法里面是用 wait() 来实现的。
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                System.out.println("--------------B" + i);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
    }
}
