package com.java.advanced.features.concurrent.threadscommunication.multiple.unsafe.unsafe2;

import java.util.concurrent.TimeUnit;

class Apk {
    private String apkName;
    private boolean isForTest = false;
    private int code = 1;
    public synchronized void releaseApk(String name) {
        while (isForTest) {
            try {
                wait(); // rt1
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            // 这 200 ms 当作开发时间
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.apkName = name +"-V"+ code;
        System.out.println(Thread.currentThread().getName() + "============>Release apk: " + this.apkName);
        code++;
        isForTest = true;
        notify();
    }

    public synchronized void testApk() {
        while (!isForTest) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            // 这 60 ms 作为测试时间
            TimeUnit.MILLISECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Test Apk: " + this.apkName);
        isForTest = false;
        notify();
    }
}

class ReleaseApkRunnable implements Runnable {
    private Apk apk;

    public ReleaseApkRunnable(Apk apk) {
        this.apk = apk;
    }

    @Override
    public void run() {
        while (true) {
            apk.releaseApk("QQ");
        }
    }
}

class TestApkRunnable implements Runnable {
    private Apk apk;

    public TestApkRunnable(Apk apk) {
        this.apk = apk;
    }

    @Override
    public void run() {
        while (true) {
            apk.testApk();
        }
    }
}

public class ApkDemo {
    public static void main(String[] args) {
        Apk apk = new Apk();
        Runnable releaseApkRunnable = new ReleaseApkRunnable(apk);
        Runnable testApkRunnable = new TestApkRunnable(apk);
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
