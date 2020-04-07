package com.java.advanced.features.juc.c_006;

/**
 * synchronized 关键字能够实现原子性和可见性，在 Java 内存模型中，synchronized 规定，
 * 线程在加锁时，先清空工作内存--> 在主内存中拷贝最新变量的副本到工作内存--> 执行完代码
 * --> 将更改后的共享变量的值刷新到主内存中 --> 释放互斥锁。
 * volatile 保证可见性，但不保证操作的原子性。
 * 参考文章：内存可见性和原子性：Synchronized和Volatile的比较
 * https://blog.csdn.net/guyuealian/article/details/52525724
 * 视频资源：http://www.imooc.com/video/6775
 * todo 这个视频还没有看完。
 * @author wangzhichao
 * @since 2020/3/28
 */
public class T implements Runnable {
    private int count = 100;

    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        Runnable t = new T();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "Thread" + i).start();
        }
    }
}
