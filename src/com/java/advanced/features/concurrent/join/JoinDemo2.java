package com.java.advanced.features.concurrent.join;
class Task3 implements Runnable {
    public Task3() {
    }
    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is doing task.");
    }
}

public class JoinDemo2 {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Task3(), "Thread " + i);
            thread.start();
            System.out.println("<===========");
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("==========>");
        }
        System.out.println(Thread.currentThread().getName() + " is doing task.");
    }
}
