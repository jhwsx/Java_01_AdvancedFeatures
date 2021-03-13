package com.java.advanced.features.concurrent.join;
class Task2 implements Runnable {
    private Thread joiner;
    public Task2(Thread joiner) {
        this.joiner = joiner;
    }
    @Override
    public void run() {
        try {
            joiner.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is doing task.");
    }
}
// 使用了 join() ，子线程的执行顺序是确定的。
public class JoinDemo {
    public static void main(String[] args) {
        Thread joiner = Thread.currentThread();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Task2(joiner), "Thread " + i);
            thread.start();
            joiner = thread;
        }
        System.out.println(Thread.currentThread().getName() + " is doing task.");
    }
}
