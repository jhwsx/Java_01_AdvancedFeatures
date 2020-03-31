package com.java.advanced.features.juc.c_018_00_Atomicxxx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 这个例子由 c_013 修改而来。
 * 这里我们不用 synchronized，而用 AtomicInteger 也达到了目的。
 * 参考学习：【Java并发编程】—–深入分析CAS原子操作 https://www.jianshu.com/p/9ff426a784ad
 *
 * @author wangzhichao
 * @since 2020/3/30
 */
public class T01_AtomicInteger {
    AtomicInteger count = new AtomicInteger(0);

    void m() {
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        T01_AtomicInteger t = new T01_AtomicInteger();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "thread-" + i));
        }
        threads.forEach(thread -> thread.start());

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
