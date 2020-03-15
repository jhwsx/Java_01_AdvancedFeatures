package com.java.advanced.features.concurrent.threadpool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class MyRunnable implements Runnable {
    private int id;

    public MyRunnable(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Task#" + id + " start.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": Task#" + id + " end.");
    }
}
class SerialExecutor implements Executor {
    // 任务队列
    final Queue<Runnable> tasks = new ArrayDeque<Runnable>();
    // 这个才是真正的执行器
    final Executor executor;
    // 当前正在执行的任务
    Runnable active;
  
    // 初始化的时候，指定执行器
    SerialExecutor(Executor executor) {
        this.executor = executor;
    }
 
    // 添加任务到线程池: 将任务添加到任务队列，scheduleNext 触发执行器去任务队列取任务
    public synchronized void execute(final Runnable r) {
        tasks.offer(new Runnable() {
            public void run() {
                try {
                    r.run();
                } finally {
                    scheduleNext();
                }
            }
        });
        if (active == null) {
            scheduleNext();
        }
    }
    protected synchronized void scheduleNext() {
        if ((active = tasks.poll()) != null) {
            // 具体的执行转给真正的执行器 executor
            executor.execute(active);
        }
    }

    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();
        SerialExecutor serialExecutor = new SerialExecutor(executor);
        for (int i = 0; i < 10; i++) {
            serialExecutor.execute(new MyRunnable(i));
        }
    }
}