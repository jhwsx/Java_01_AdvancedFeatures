package com.java.advanced.features.concurrent.create;

// 两个储户，到一家银行里存钱，分别存 300， 分 3 次存，每次存 100
class Bank {
    private int sum = 0;

    //    public void add(int num) {
//        sum = sum + num;
//        try {Thread.sleep(10);} catch (InterruptedException e) {}
//        System.out.println("sum = " + sum);
//    }
    // 同步代码块
    private Object obj = new Object();
    public void add(int num) {
        synchronized (obj) {
            sum = sum + num;
            try {Thread.sleep(10);} catch (InterruptedException e) {}
            System.out.println("sum = " + sum);
        }
    }
    // 同步函数
//    public synchronized void add(int num) {
//        sum = sum + num;
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//        }
//        System.out.println("sum = " + sum);
//    }
}

class Customer implements Runnable {
    private Bank bank = new Bank();

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            bank.add(100);
        }
    }
}

public class BankDemo {
    public static void main(String[] args) {
        Customer customer = new Customer();
        Thread t1 = new Thread(customer);
        Thread t2 = new Thread(customer);
        t1.start();
        t2.start();
    }
}
