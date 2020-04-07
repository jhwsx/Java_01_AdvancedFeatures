package com.java.advanced.features.juc.c_020;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
// https://www.bbsmax.com/A/kmzLb8Mb5G/
// 要执行 doBusiness()中的代码首先需要得到锁lock，线程使用 lockInterruptibly()方法获取锁。
//
// 如下示例中，t0得到了锁，t1等待。在t1等待时，调用t1.interrupt()，中断t1的等待。
public class T04_ReentrantLock4_LockInterruptibly {
 
    private Lock lock = new ReentrantLock();
 
    public void doBusiness() {
        String name = Thread.currentThread().getName();
 
        try {
            System.out.println(name + " 开始获取锁");
            lock.lockInterruptibly();
            System.out.println(name + " 得到锁");
            System.out.println(name + " 开工干活");
            for (int i=0; i<5; i++) {
                Thread.sleep(1000);
                System.out.println(name + " : " + i);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " 被中断");
            System.out.println(name + " 做些别的事情");
        } finally {
//            try {
                lock.unlock();
                System.out.println(name + " 释放锁");
//            } catch (Exception e) {
//                System.out.println(name + " : 没有得到锁的线程运行结束");
//            }
        }
    }
 
    public static void main(String[] args) throws InterruptedException {
 
        T04_ReentrantLock4_LockInterruptibly lockTest = new T04_ReentrantLock4_LockInterruptibly();
 
        Thread t0 = new Thread(
                () -> lockTest.doBusiness()
        );
 
        Thread t1 = new Thread(
                () -> lockTest.doBusiness()
        );
 
        // 启动线程t1
        t0.start();
        Thread.sleep(10);
        // 启动线程t2
        t1.start();
        Thread.sleep(100);
        // 线程t1没有得到锁，中断t1的等待
        t1.interrupt();
    }
}