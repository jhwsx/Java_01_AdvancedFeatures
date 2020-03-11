package com.java.advanced.features.concurrent.threadscommunication.waitnofityinaction;

import java.util.concurrent.TimeUnit;

// 在软件开发过程中，对 apk 有两个过程：一个是开发工程师发布 apk，一个是测试工程师测试 apk。
// 测试 apk 任务在发布 apk 任务完成之前，是不能执行工作的；而发布 apk 任务在发另一个 apk
// 之前，必须等待测试任务完成。
class Apk {
    private String apkName;
    private String versionName;
    private boolean isForTest = false;

    public synchronized void releaseApk(String apkName, String versionName) {
        if (isForTest) {
            try {
                wait();
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
        this.apkName = apkName;
        this.versionName = versionName;
        isForTest = true;
        System.out.println("Release apk: " + this);
        notify();
    }

    public synchronized void testApk() {
        if (!isForTest) {
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
        isForTest = false;
        System.out.println("Test Apk: " + this);
        notify();
    }

    @Override
    public String toString() {
        return apkName + "," + versionName;
    }
}

class ReleaseApkRunnable implements Runnable {
    private Apk apk;

    public ReleaseApkRunnable(Apk apk) {
        this.apk = apk;
    }

    @Override
    public void run() {
        int x = 0;
        while (true) {
            if (x % 2 == 0) {
                apk.releaseApk("QQ", "Overseas");
            } else {
                apk.releaseApk("微信", "国内版");
            }
            x++;
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
        Thread releaseThread = new Thread(releaseApkRunnable);
        Thread testThread = new Thread(testApkRunnable);
        releaseThread.start();
        testThread.start();
    }
}
