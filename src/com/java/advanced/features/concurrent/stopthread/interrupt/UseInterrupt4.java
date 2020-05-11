package com.java.advanced.features.concurrent.stopthread.interrupt;

/**
 * 演示 wait() 方法会清除中断标记
 */
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
                        // wait() 方法会清除中断标记，所以这里 isInterrupted() 打印为 false
                        System.out.println(Thread.currentThread().getName() + " wait interrupted, " +
                                "isInterrupted() = " + isInterrupted());
                        // 因为中断标记被清除了，还是无法结束任务，所以再调用一次 interrupt() 方法
                        // 这样才可以结束任务。
                        interrupt();
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
