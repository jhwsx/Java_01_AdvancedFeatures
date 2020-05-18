package com.java.advanced.features.concurrent.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示 AtomicInteger 的使用
 */
public class AtomicIntegerDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        assert(atomicInteger.get() == 5);

        assert(atomicInteger.getAndIncrement() == 5);

        assert(atomicInteger.get() == 6);

        assert atomicInteger.incrementAndGet() == 7;

        assert atomicInteger.compareAndSet(7, 8);

        assert atomicInteger.compareAndSet(7, 8);
    }
}
