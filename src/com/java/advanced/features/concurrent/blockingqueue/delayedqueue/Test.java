package com.java.advanced.features.concurrent.blockingqueue.delayedqueue;

import java.util.concurrent.DelayQueue;

/**
 * 延时队列测试代码
 */
public class Test {
    public static void main(String[] args) {
        // 构建延时队列
        DelayQueue<ItemVo<Order>> delayQueue = new DelayQueue<>();
        // 开启放入订单的线程
        new Thread(new PutOrderRunnable(delayQueue), "putThread").start();
        // 开启取出到期订单的线程
        new Thread(new FetchOrderRunnable(delayQueue), "fetchThread").start();
        // 每个 500 ms 打印时间
        int count = 0;
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            System.out.println("时间：" + (500 * count));
            if (count == 30) {
                System.exit(0);
                break;
            }
        }
    }
}
