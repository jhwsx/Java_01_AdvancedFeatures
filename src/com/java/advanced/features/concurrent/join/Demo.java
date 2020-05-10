package com.java.advanced.features.concurrent.join;
class Task1 implements Runnable {
    public Task1() {
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is doing task.");
    }
}

public class Demo {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Task1(), "Thread " + i);
            thread.start();
        }
        System.out.println(Thread.currentThread().getName() + " is doing task.");
    }
}
