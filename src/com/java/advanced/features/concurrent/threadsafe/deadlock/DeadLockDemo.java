package com.java.advanced.features.concurrent.threadsafe.deadlock;

class TaskA implements Runnable {

    @Override
    public void run() {
        while (true) {
            synchronized (DeadLockDemo.lock1) {
                System.out.println(Thread.currentThread().getName() + " do something.");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " wake up");
                synchronized (DeadLockDemo.lock2) {
                    System.out.println(Thread.currentThread().getName() + " do other thing.");
                }
            }
        }

    }
}

class TaskB implements Runnable {

    @Override
    public void run() {
        while (true) {
            synchronized (DeadLockDemo.lock2) {
                System.out.println(Thread.currentThread().getName() + " do something.");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " wake up");
                synchronized (DeadLockDemo.lock1) {
                    System.out.println(Thread.currentThread().getName() + " do other thing.");
                }
            }
        }

    }
}
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
ThreadB do something.
ThreadA do something.

或者是
ThreadA do something.
ThreadB do something.
 */
