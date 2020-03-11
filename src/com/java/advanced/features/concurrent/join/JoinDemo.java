package com.java.advanced.features.concurrent.join;
class Task implements Runnable {
    private Thread joiner;
    public Task(Thread joiner) {
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
public class JoinDemo {
    public static void main(String[] args) {
        Thread joiner = Thread.currentThread();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Task(joiner), "Thread " + i);
            thread.start();
            joiner = thread;
        }
        System.out.println(Thread.currentThread().getName() + " is doing task.");
    }
}
