package com.java.advanced.features.concurrent.stopthread.interrupt;

public class UseInterrupt2 {
    private static class MyThread extends Thread {
        @Override
        public void run() {
            while (!isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + " is running, " +
                        "isInterrupted() = " + isInterrupted());
            }
            System.out.println(Thread.currentThread().getName() + " end, " +
                    "isInterrupted() = " + isInterrupted());
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 在 main 线程中，调用 myThread 的 interrupt() 方法，发出中断 myThread 线程的信号
        myThread.interrupt();
    }
}
