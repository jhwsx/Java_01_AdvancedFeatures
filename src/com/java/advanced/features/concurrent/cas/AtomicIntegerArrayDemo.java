package com.java.advanced.features.concurrent.cas;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {
        int[] array = {1, 2, 3};
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(array);
        int newValue = atomicIntegerArray.addAndGet(2, 1);
        System.out.println("newValue = " + newValue);
        int oldValue = atomicIntegerArray.getAndSet(1, 1);
        System.out.println("oldValue = " + oldValue);
        boolean result = atomicIntegerArray.compareAndSet(0, 1, 2);
        System.out.println("result = " + result);
    }
}
