package com.java.advanced.features.concurrent.threadscommunication.multiple.apkarray;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Apk {
    private String apkName;
    private static int counter = 1;
    private final int code = counter++;
    public Apk(String apkName) {
        this.apkName = apkName;
    }

    @Override
    public String toString() {
        return "Apk: " + apkName + "-V" + code;
    }
}

class ApkBuffer {
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    final Apk[] items = new Apk[5];
    private int putptr, takeptr, count;

    // 发布 apk
    public void put() throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            Apk x = new Apk("QQ"); // 放在这里编号不会错乱。
            System.out.println("ApkBuffer, put=======>" + x);
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    // 测试 apk
    public Apk take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            Apk x = items[takeptr];
            System.out.println("ApkBuffer: take<===================" + x);
            if (++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }

}
class ReleaseApkRunnable implements Runnable {
    private ApkBuffer apkBuffer;
    public ReleaseApkRunnable(ApkBuffer apkBuffer) {
        this.apkBuffer = apkBuffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                apkBuffer.put();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class TestApkRunnable implements Runnable {
    private ApkBuffer apkBuffer;

    public TestApkRunnable(ApkBuffer apkBuffer) {
        this.apkBuffer = apkBuffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                apkBuffer.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ApkDemo {
    public static void main(String[] args) {
        ApkBuffer apkBuffer = new ApkBuffer();
        Runnable releaseApkRunnable = new ReleaseApkRunnable(apkBuffer);
        Runnable testApkRunnable = new TestApkRunnable(apkBuffer);
        Thread releaseThread1 = new Thread(releaseApkRunnable, "releaseThread1");
        Thread releaseThread2 = new Thread(releaseApkRunnable, "releaseThread2");
        Thread testThread1 = new Thread(testApkRunnable, "testThread1");
        Thread testThread2 = new Thread(testApkRunnable, "testThread2");
        releaseThread1.start();
        releaseThread2.start();
        testThread1.start();
        testThread2.start();
    }
}
