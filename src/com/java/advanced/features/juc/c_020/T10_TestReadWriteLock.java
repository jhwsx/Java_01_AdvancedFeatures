package com.java.advanced.features.juc.c_020;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 读写锁里，读锁是共享锁，写锁是排他锁。
 * 它适用于读操作多，而写操作少的场景。
 * <p>
 * 当一个读线程在读操作时，允许其他读线程也进行读操作，这就是读锁的共享性；
 * 当一个写线程在写操作时，不允许其他写线程进行写操作，这就是写锁的排他性。
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
