package com.java.advanced.features.juc.c_013;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile 不能替代 synchronized
 * 演示：
 * 加 synchronized 和不加 synchronized 的打印结果
 * 加 synchronized 和 volatile 对比 加 synchronized 但不加 volatile 的打印结果。
 *
 * @author wangzhichao
 * @since 2020/3/30
 */
public class T {
    volatile int count = 0;

    synchronized void m() {
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
