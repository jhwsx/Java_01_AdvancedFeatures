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
 * @author wangzhichao
 * @since 2020/3/28
 */
public class T04_ThreadState {
    static class MyThread extends Thread {
        @Override
        public void run() {
//            System.out.println(this.getState());
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }

    static class LoopDamemonThread extends Thread {
        private MyThread myThread;

        public LoopDamemonThread(MyThread myThread) {
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
        LoopDamemonThread loopDamemonThread = new LoopDamemonThread(t);
        loopDamemonThread.setDaemon(true);
        loopDamemonThread.start();
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        System.out.println(t.getState());
    }
}
