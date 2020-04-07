package com.java.advanced.features.juc.c_018_00_Atomicxxx;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 比较 AtomicLong，synchronized 和 LongAdder 的效率
 * 参考文章：synchronized和AtomicXXX效率比较 https://www.cnblogs.com/renjiaqi/p/11323461.html
 *
 * @author wangzhichao
 * @since 2020/3/31
 */
public class T02_AtomicVsSyncVsLongAdder {
    static AtomicLong count1 = new AtomicLong(0L);
    static long count2 = 0L;
    static LongAdder count3 = new LongAdder();
    static final int LOOP_COUNT = 1000000;
    static final int THREAD_COUNT = 1000;
    public static void main(String[] args) throws InterruptedException {
        testAtomicCost();
        testLockCost();
        testLongAdderCost();
    }

    private static void testLongAdderCost() throws InterruptedException {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < threads.length; i++) {
            threads[i] =
                    new Thread(() -> {
                        for (int k = 0; k < LOOP_COUNT; k++) {
                            count3.increment();
                        }
                    });
        }
        long start = System.currentTimeMillis();

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long end = System.currentTimeMillis();

        System.out.println("LongAdder: " + count3.longValue() + ", time: " + (end - start));
    }

    private static void testLockCost() throws InterruptedException {
        Thread[] threads = new Thread[THREAD_COUNT];
        final Object obj = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] =
                    new Thread(() -> {
                        for (int k = 0; k < LOOP_COUNT; k++) {
                            // 这里只是为了做测验，同步的代码是 count2++，而实际上同步 for 循环是更高效的
                            // 不过，这样的话，和 AtomicLong，LongAdder 的锁级别就不一样，没有可比性。
                            synchronized (obj) {
                                count2++;
                            }
                        }
                    });
        }
        long start = System.currentTimeMillis();

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long end = System.currentTimeMillis();

        System.out.println("Sync: " + count2 + ", time: " + (end - start));
    }

    private static void testAtomicCost() throws InterruptedException {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < threads.length; i++) {
            threads[i] =
                    new Thread(() -> {
                        for (int k = 0; k < LOOP_COUNT; k++) {
                            count1.incrementAndGet();
                        }
                    });
        }
        long start = System.currentTimeMillis();

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long end = System.currentTimeMillis();

        System.out.println("Atomic: " + count1.get() + ", time: " + (end - start));
    }
}
