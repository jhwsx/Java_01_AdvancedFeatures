package com.java.advanced.features.concurrent.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wangzhichao
 * @since 2020/3/15
 */
public class CreateThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是一段耗时任务。");
            }
        });
        threadPoolExecutor.shutdown();
    }
}
