package com.java.advanced.features.juc.c_008;

/**
 * 面试题：模拟银行账户
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 这样会不会有问题？
 * 容易产生脏读（dirtyRead）
 * @author wangzhichao
 * @since 2020/3/28
 */
public class Account {
    String name;
    double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public /*synchronized*/ double getBalance(String name) {
        return this.balance;
    }

    public static void main(String[] args) {
        Account account = new Account();
        new Thread(() -> account.set("zhangsan", 100)).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance("zhangsan")); // getBalance 不加锁，打印 0.0;加锁：打印 100.0
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance("zhangsan")); // 100.0
    }
}
