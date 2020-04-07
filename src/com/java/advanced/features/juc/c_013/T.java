package com.java.advanced.features.juc.c_013;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile 不能替代 synchronized
 * 演示：
 * 加 synchronized 对比 不加 synchronized 的打印结果
 * 前者是 100000，后者却会出现少于 100000.
 *
 * 加 synchronized 且加 volatile 对比 加 synchronized 但不加 volatile 的打印结果。
 * 打印结果没有区别，都是 100000.
 *
 * 加 volatile 但不加 sychronized 对比 不加 volatile 且不加 sychronized 的打印结果
 * 前者会出现少于 100000，后者也会出现少于 100000.
 *
 * @author wangzhichao
 * @since 2020/3/30
 */
public class T {
    /*volatile*/ int count = 0;

    /*synchronized*/ void m() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        T t = new T();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "thread-" + i));
        }
        threads.forEach(thread -> thread.start());
        // 要在 start 之后，再去 join。
        // join 的目的是为了保证新建的线程都在主线程之前执行。
        threads.forEach(thread ->
        {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }
}
