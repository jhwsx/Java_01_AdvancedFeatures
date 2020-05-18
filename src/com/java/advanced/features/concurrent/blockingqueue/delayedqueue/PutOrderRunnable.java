package com.java.advanced.features.concurrent.blockingqueue.delayedqueue;

import java.util.concurrent.DelayQueue;

/**
 * 将订单放入 DelayQueue 中的任务
 */
public class PutOrderRunnable implements Runnable {
    private DelayQueue<ItemVo<Order>> delayQueue;

    public PutOrderRunnable(DelayQueue<ItemVo<Order>> delayQueue) {
        this.delayQueue = delayQueue;
    }

    @Override
    public void run() {
        // 一个 5s 后到期的订单
        Order order1 = new Order("1", 1);
        ItemVo<Order> itemVo1 = new ItemVo<>( 5000, order1);
        delayQueue.put(itemVo1);
        System.out.println("订单放入队列中：id=" + order1.getId() + ", money=" + order1.getMoney()
                + ", 超时时长：5s");
        // 一个 10s 后到期的订单
        Order order2 = new Order("2", 2);
        ItemVo<Order> itemVo2 = new ItemVo<>( 10000, order2);
        delayQueue.put(itemVo2);
        System.out.println("订单放入队列中：id=" + order2.getId() + ", money=" + order2.getMoney()
                + ", 超时时长：10s");
    }
}
