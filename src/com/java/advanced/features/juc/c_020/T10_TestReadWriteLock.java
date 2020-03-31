package com.java.advanced.features.juc.c_020;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * @author wangzhichao
 * @since 2020/3/31
 */
public class T10_TestReadWriteLock {
    static Lock lock = new ReentrantLock();
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();
    private static int value;

    // 读操作
    public static void read(Lock lock) {
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println("read over!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 写操作
    public static void write(Lock lock, int v) {
        lock.lock();
        try {
            Thread.sleep(1000);
            value = v;
            System.out.println("write over！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        // 使用 ReentrantLock
        /*Runnable readR = () -> {
            read(lock);
        };
        Runnable writeR = () -> {
            write(lock, new Random().nextInt());
        };*/
        // 使用 ReentrantReadWriteLock
        Runnable readR = () -> {
            // 传入读锁
            read(readLock);
        };
        Runnable writeR = () -> {
            // 传入写锁
            write(writeLock, new Random().nextInt());
        };
        for (int i = 0; i < 18; i++) {
            new Thread(readR).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(writeR).start();
        }
    }
}
