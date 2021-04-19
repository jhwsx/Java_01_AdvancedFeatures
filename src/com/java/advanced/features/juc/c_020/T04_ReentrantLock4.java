package com.java.advanced.features.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 的 lockInterruptibly 方法：可以中断等待获取锁的线程。
 *
 * 参考学习：https://www.cnblogs.com/zqyanywn/p/11646451.html
 *
 * @author wangzhichao
 * @since 2020/3/31
 */
public class T04_ReentrantLock4 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        // 线程1 获取锁之后，就开始 sleep 了，不打算释放锁了。
        Thread t1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                System.out.println("t1 interrupted");
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                // 使用这种方式，调用 t2.interrupt() 方法时，不可以中断线程2的等待
                // lock.lock();
                // 使用这种方式，调用 t2.interrupt() 方法时，就可以中断线程2的等待
                lock.lockInterruptibly();
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("t2 interrupted");
            } finally {
                try {
                    lock.unlock();
                } catch (Exception e) {
                    // 调用 t2.interrupt(), 并且上面采用 lock.lockInterruptibly() 时，这里会有异常
                    // t2 e=java.lang.IllegalMonitorStateException
                    System.out.println("t2 e=" + e);
                }
            }
        });

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 中断 t2 的等待
        t2.interrupt();
    }
}
