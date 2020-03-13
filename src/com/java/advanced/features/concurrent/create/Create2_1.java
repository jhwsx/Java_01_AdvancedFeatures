package com.java.advanced.features.concurrent.create;

import java.util.concurrent.*;

/**
 * @author wangzhichao
 * @since 2020/3/13
 */
class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int result = 0;
        for (int i = 0; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + " is calculating.");
            Thread.sleep(20);
            result += i;
        }
        return result;
    }
}
public class Create2_1 {
    public static void main(String[] args) {
       useThread();
       useExecutors();
    }

    static void useThread() {
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        new Thread(futureTask).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static void useExecutors() {
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(futureTask);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
