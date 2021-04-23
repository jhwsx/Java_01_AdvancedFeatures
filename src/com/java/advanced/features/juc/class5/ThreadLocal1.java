package com.java.advanced.features.juc.class5;

import java.util.concurrent.TimeUnit;

/**
 * @author wangzhichao
 * @since 2021/4/23
 */
public class ThreadLocal1 {
    volatile static Person p = new Person();
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name); // 打印 lisi
        }).start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "lisi";
        }).start();
    }
}

class Person {
    String name = "zhangsan";
}

