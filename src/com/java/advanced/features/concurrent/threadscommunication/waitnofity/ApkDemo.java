package com.java.advanced.features.concurrent.threadscommunication.waitnofity;

import java.util.concurrent.TimeUnit;

// 在软件开发过程中，对 apk 有两个过程：一个是开发工程师发布 apk，一个是测试工程师测试 apk。
// 测试 apk 任务在发布 apk 任务完成之前，是不能执行工作的；而发布 apk 任务在发另一个 apk
// 之前，必须等待测试任务完成。
class Apk {
    String apkName;
    String versionName;
    boolean isForTest = false;
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
            synchronized (apk) {
                if (apk.isForTest) {
                    try {
                        apk.wait();
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
                if (x % 2 == 0) {
                    apk.apkName = "QQ";
                    apk.versionName = "Overseas";
                } else {
                    apk.apkName = "微信";
                    apk.versionName = "国内版";
                }
                apk.isForTest = true;
                System.out.println("Release apk: " + apk.apkName + "," + apk.versionName);
                apk.notify();
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
            synchronized (apk) {
                if (!apk.isForTest) {
                    try {
                        apk.wait();
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
                System.out.println("Test pass: " + apk.apkName + "," + apk.versionName);
                apk.isForTest = false;
                apk.notify();
            }

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
