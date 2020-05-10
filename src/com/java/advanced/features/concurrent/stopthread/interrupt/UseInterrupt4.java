package com.java.advanced.features.concurrent.stopthread.interrupt;

public class UseInterrupt4 {
    private static class MyThread extends Thread {
        private Object lock = new Object();
        @Override
        public  void run() {
            synchronized (lock) {
                while (!isInterrupted()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(Thread.currentThread().getName() + " wait interrupted, " +
                                "isInterrupted() = " + isInterrupted());
                    }
                    System.out.println(Thread.currentThread().getName() + " is running, " +
                            "isInterrupted() = " + isInterrupted());
                }
                System.out.println(Thread.currentThread().getName() + " end, " +
                        "isInterrupted() = " + isInterrupted());
            }

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
