package com.java.advanced.features.concurrent.threadpool;

/**
 * @author wangzhichao
 * @since 2020/3/15
 */
public class CreateThread {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是一段耗时任务");
            }
        }).start();
    }
}
