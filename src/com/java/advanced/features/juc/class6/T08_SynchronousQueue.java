package com.java.advanced.features.juc.class6;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue
 *
 * 一种阻塞队列，其中每个插入操作必须等待另一个线程的对应移除操作 ，反之亦然。同步队列没有任何内部容量，甚至连一个队列的容量都没有。
 *
 * 用途：专门用来两个线程之间传递内容的。
 *
 * @author wangzhichao
 * @since 2021/4/27
 */
public class T08_SynchronousQueue {
    public static void main(String[] args) {
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(()->{
            try {
                // 这个线程在 take(), take 不到就一直等着
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            strs.put("aaa"); // put 完之后，就阻塞在这里，等待 take 完了再去执行下面的代码
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 添加代码1：
//        try {
//            strs.put("bbb"); // 没有人 take 了，永远阻塞在这里
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        // 添加代码2：
        // 证明了 SynchronousQueue 的容量是 0.
        // strs.add("ccc"); // java.lang.IllegalStateException: Queue full
        System.out.println(strs.size());
    }
}
