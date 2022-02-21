package com.java.advanced.features.juc.c_020;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier 循环栅栏
 * int parties Runnable barrierAction
 * Runnable barrierAction 在启动 barrier 时执行的命令；如果不执行任何操作，则该参数为 null
 * public CyclicBarrier(int parties)
 * public CyclicBarrier(int parties, Runnable barrierAction)
 *
 * @author wangzhichao
 * @since 2022/2/21
 */
public class T07_TestCyclicBarrier_2 {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, () ->
        {
            System.out.println("所有的数据都拿到了！！！");
        });

        new Thread(() -> {
            System.out.println("执行网络请求 start");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行网络请求 end");
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            System.out.println("执行读文件 start");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行读文件 end");
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            System.out.println("执行查数据库 start");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行查数据库 end");
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
