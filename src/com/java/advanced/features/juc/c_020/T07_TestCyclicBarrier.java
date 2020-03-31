package com.java.advanced.features.juc.c_020;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 循环栅栏
 * int parties Runnable barrierAction
 * Runnable barrierAction 在启动 barrier 时执行的命令；如果不执行任何操作，则该参数为 null
 * public CyclicBarrier(int parties)
 * public CyclicBarrier(int parties, Runnable barrierAction)
 *
 * @author wangzhichao
 * @since 2020/3/31
 */
public class T07_TestCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(20, () ->
                System.out.println("满人"));

        // 演示栅栏推倒之后又重新起来
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    // 在所有参与者都已经在此 barrier 上调用 await 方法之前，将一直等待
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
