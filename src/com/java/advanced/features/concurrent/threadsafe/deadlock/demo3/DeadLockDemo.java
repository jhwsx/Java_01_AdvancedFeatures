package com.java.advanced.features.concurrent.threadsafe.deadlock.demo3;

import java.util.concurrent.locks.ReentrantLock;

// TaskA 任务：需要先尝试获取 lock1 锁，再尝试获取 lock2 锁。
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

// TaskB 任务：需要先尝试获取 lock2 锁，再尝试获取 lock1 锁。
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
// 这个例子演示活锁问题：
// 两个线程在尝试拿锁的机制中，发生多个线程之间互相谦让，不断发生同一个线程总是拿到同一把锁，在尝试拿另一把锁时因为拿不到，而将本来已经持有的锁释放的过程。
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
