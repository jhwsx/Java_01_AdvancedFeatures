package com.java.advanced.features.concurrent.threadsafe.deadlock.demo5;

class TaskA implements Runnable {

    @Override
    public void run() {
        while (true) {
            synchronized (DeadLockDemo.lock1) {
                System.out.println(Thread.currentThread().getName() + " holds lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " waits for lock2");
                synchronized (DeadLockDemo.lock2) {
                    System.out.println(Thread.currentThread().getName() + " hold2 lock2");
                }
            }
        }
    }
}

class TaskB implements Runnable {

    @Override
    public void run() {
        while (true) {
            synchronized (DeadLockDemo.lock1) {
                System.out.println(Thread.currentThread().getName() + " holds lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    System.out.println(Thread.currentThread().getName() + " waits for lock2");
                synchronized (DeadLockDemo.lock2) {
                    System.out.println(Thread.currentThread().getName() + " holds lock2");
                }
            }
        }
    }
}
// 这个例子演示解决死锁问题，通过打破环路等待条件。
public class DeadLockDemo {
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(new TaskA(), "ThreadA");
        Thread threadB = new Thread(new TaskB(), "ThreadB");

        threadA.start();
        threadB.start();
    }
}

/*
打印结果：
ThreadA holds lock1
ThreadB holds lock2
ThreadB waits for lock1
ThreadA waits for lock2

或者是
ThreadA holds lock1
ThreadB holds lock2
ThreadA waits for lock2
ThreadB waits for lock1

或者是
ThreadB holds lock2
ThreadA holds lock1
ThreadB waits for lock1
ThreadA waits for lock2

或者是
ThreadB holds lock2
ThreadA holds lock1
ThreadA waits for lock2
ThreadB waits for lock1
 */
