package com.java.advanced.features.juc.class5;

import java.util.concurrent.TimeUnit;

/**
 * https://www.bilibili.com/video/BV1Yt4y1y7cS?from=search&seid=915475662089433992
 * https://www.bilibili.com/video/BV12f4y127bZ?from=search&seid=915475662089433992
 * @author wangzhichao
 * @since 2021/4/23
 */
public class ThreadLocal2 {
    //    volatile static Person2 p = new Person2();
    static ThreadLocal<Person2> tl = new ThreadLocal<>();
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get()); // 打印 null
        }).start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person2());
        }).start();
    }
}

class Person2 {
    String name = "zhangsan";
}

