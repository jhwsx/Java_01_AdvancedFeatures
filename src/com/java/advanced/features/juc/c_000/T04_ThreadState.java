package com.java.advanced.features.juc.c_000;

/**
 * 线程的状态
 * NEW
 * 至今尚未启动的线程处于这种状态。
 * RUNNABLE
 * 正在 Java 虚拟机中执行的线程处于这种状态。
 * BLOCKED
 * 受阻塞并等待某个监视器锁的线程处于这种状态。
 * WAITING
 * 无限期地等待另一个线程来执行某一特定操作的线程处于这种状态。
 * TIMED_WAITING
 * 等待另一个线程来执行取决于指定等待时间的操作的线程处于这种状态。
 * TERMINATED
 * 已退出的线程处于这种状态。
 *
 * 这个小程序可以打印出线程的所有状态。
 *
 * @author wangzhichao
 * @since 2020/3/28
 */
public class T04_ThreadState {
    static class MyThread extends Thread {
        @Override
        public void run() {
            synchronized (T04_ThreadState.class) {
                System.out.println(Thread.currentThread().getName() + "获取锁");
                for (int i = 0; i < 2; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                }
                T04_ThreadState.class.notify();
                try {
                    T04_ThreadState.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "释放锁");
            }
        }
    }

    static class LoopDaemonThread extends Thread {
        private MyThread myThread;

        public LoopDaemonThread(MyThread myThread) {
            this.myThread = myThread;
        }

        private Thread.State lastState = State.NEW;

        @Override
        public void run() {
            while (true) {
                State state = myThread.getState();
                if (state != lastState) {
                    System.out.println(state);
                    lastState = state;
                }
            }
        }
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();
        System.out.println(t.getState());
        // 这里用一个守护线程来获取 MyThread 的状态。
        LoopDaemonThread loopDaemonThread = new LoopDaemonThread(t);
        loopDaemonThread.setDaemon(true);
        loopDaemonThread.start();
        t.start();
        synchronized (T04_ThreadState.class) {
            System.out.println(Thread.currentThread().getName() + "获取锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                T04_ThreadState.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            T04_ThreadState.class.notify();
            System.out.println(Thread.currentThread().getName() + "释放锁");
        }
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
