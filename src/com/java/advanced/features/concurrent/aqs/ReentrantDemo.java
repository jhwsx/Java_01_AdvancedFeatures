package com.java.advanced.features.concurrent.aqs;

import java.util.concurrent.locks.Lock;

public class ReentrantDemo {
    //    Lock lock = new Mutex();
    Lock lock = new ReentrantMutex();

    void m1() {
        lock.lock();
        try {
            System.out.println("m1() called");
            m2();
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        lock.lock();
        try {
            System.out.println("m2() called");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantDemo reentrantDemo = new ReentrantDemo();
        reentrantDemo.m1();
    }
}
