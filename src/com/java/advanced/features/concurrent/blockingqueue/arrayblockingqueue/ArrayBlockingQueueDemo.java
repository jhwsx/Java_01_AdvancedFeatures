package com.java.advanced.features.concurrent.blockingqueue.arrayblockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author wangzhichao
 * @since 2021/4/11
 */
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
    // 固定容量，采用公平锁的阻塞队列
    private final ArrayBlockingQueue<Apk> abq = new ArrayBlockingQueue<>(5, true);

    // 发布 apk
    public void put() throws InterruptedException {
        // 阻塞队列不满时，才会添加
        Apk apk;
        abq.put(apk = new Apk("QQ"));
        System.out.println("ApkBuffer, put=======> " + apk);
    }

    // 测试 apk
    public Apk take() throws InterruptedException {
        Apk apk = abq.take();
        System.out.println("ApkBuffer, take<==================== " + apk);
        return apk;
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

public class ArrayBlockingQueueDemo {
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