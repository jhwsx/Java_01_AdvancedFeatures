package com.java.advanced.features.concurrent.blockingqueue.delayedqueue;

import java.util.concurrent.DelayQueue;

/**
 * 取出到期订单的任务，作为消费者
 * 在循环中，一直从 DelayQueue 中取出到期订单
 */
public class FetchOrderRunnable implements Runnable {
    private DelayQueue<ItemVo<Order>> delayQueue;

    public FetchOrderRunnable(DelayQueue<ItemVo<Order>> delayQueue) {
        this.delayQueue = delayQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ItemVo<Order> itemVo = delayQueue.take();
                long keepDuration = itemVo.getKeepDuration();
                Order data = itemVo.getData();
                System.out.println("取出订单：id=" + data.getId() + ", money=" + data.getMoney()
                + ", 延时时长=" + keepDuration + "ms.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
