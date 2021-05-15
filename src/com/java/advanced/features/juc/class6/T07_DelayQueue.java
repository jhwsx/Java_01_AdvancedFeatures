package com.java.advanced.features.juc.class6;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue 是一个 PriorityQueue，延迟期越短越会优先执行。
 * 是一个无界队列。
 *
 * @author wangzhichao
 * @since 2021/4/27
 */
public class T07_DelayQueue {
    static BlockingQueue<MyTask> tasks = new DelayQueue<>();

    static Random r = new Random();

    static class MyTask implements Delayed {

        String name;
        long runningTime;

        public MyTask(String name, long runningTime) {
            this.name = name;
            this.runningTime = runningTime;
        }

        // 返回与此对象相关的剩余延迟时间，以给定的时间单位表示。
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(),
                    TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(getDelay(TimeUnit.MILLISECONDS),
                    o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "name='" + name + '\'' +
                    ", runningTime=" + runningTime +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 添加任务
        long now = System.currentTimeMillis();
        MyTask t1 = new MyTask("t1", now + 1000);
        MyTask t2 = new MyTask("t2", now + 2000);
        MyTask t3 = new MyTask("t3", now + 1500);
        MyTask t4 = new MyTask("t4", now + 2500);
        MyTask t5 = new MyTask("t5", now + 500);

        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);
        // TODO 为什么和实际执行任务的顺序还是不一样呢？
        System.out.println("队列中任务的顺序：");
        System.out.println(tasks);
        MyTask[] array = new MyTask[5];
        array = tasks.toArray(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

        System.out.println("执行任务的顺序：");
        for (int i = 0; i < 5; i++) {
            System.out.println(tasks.take());
        }
    }
}
