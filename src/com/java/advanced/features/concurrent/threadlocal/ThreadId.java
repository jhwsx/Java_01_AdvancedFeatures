package com.java.advanced.features.concurrent.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadId {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId =
            new ThreadLocal<Integer>() {
                @Override
                protected Integer initialValue() {
                    return nextId.getAndIncrement();
                }
            };

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+":"+ThreadId.get());
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+":"+ThreadId.get());
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+":"+ThreadId.get());
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+":"+ThreadId.get());
            }
        }.start();
    }
}