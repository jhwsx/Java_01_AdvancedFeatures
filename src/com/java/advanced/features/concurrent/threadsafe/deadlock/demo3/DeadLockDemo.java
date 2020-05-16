package com.java.advanced.features.concurrent.threadsafe.deadlock.demo3;

import java.util.concurrent.locks.ReentrantLock;

class TaskA implements Runnable {

    @Override
    public void run() {
        while (true) {
            if (DeadLockDemo.lock1.tryLock()) {
                try {
                    System.out.println(Thread.currentThread().getName() + " holds lock1");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " waits for lock2");
                    if (DeadLockDemo.lock2.tryLock()) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " hold2 lock2");
                        } finally {
                            DeadLockDemo.lock2.unlock();
                        }
                    }
                } finally {
                    DeadLockDemo.lock1.unlock();
                }
            }
        }
    }
}

class TaskB implements Runnable {

    @Override
    public void run() {
        while (true) {
            if (DeadLockDemo.lock2.tryLock()) {
                try {
                    System.out.println(Thread.currentThread().getName() + " holds lock2");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " waits for lock1");
                    if (DeadLockDemo.lock1.tryLock()) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " holds lock1");
                        } finally {
                            DeadLockDemo.lock1.unlock();
                        }
                    }
                } finally {
                    DeadLockDemo.lock2.unlock();
                }
            }
        }
    }
}

public class DeadLockDemo {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread threadA = new Thread(new TaskA(), "ThreadA");
        Thread threadB = new Thread(new TaskB(), "ThreadB");

        threadA.start();
        threadB.start();
    }
}
