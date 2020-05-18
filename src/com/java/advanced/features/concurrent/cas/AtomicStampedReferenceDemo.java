package com.java.advanced.features.concurrent.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {
    private static AtomicStampedReference<String> atomicStampedReference
            = new AtomicStampedReference<>("A", (int) System.currentTimeMillis());
    public static void main(String[] args) throws InterruptedException {
        int stamp = atomicStampedReference.getStamp();
        System.out.println(Thread.currentThread().getName() + ", reference = " + atomicStampedReference.getReference()
        +", stamp = " + stamp);

        Thread thiefThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int newStamp1 = (int) System.currentTimeMillis();
                atomicStampedReference.set("B", newStamp1);
                System.out.println(Thread.currentThread().getName() + ", 设置值为 B ，stamp = " + newStamp1);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int newStamp2 = (int) System.currentTimeMillis();
                atomicStampedReference.set("A", newStamp2);
                System.out.println(Thread.currentThread().getName() + ", 再改回设置值为 A ，stamp = " + newStamp2);

            }
        }, "thiefThread");

        Thread checkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String currReference = atomicStampedReference.getReference();
                int currStamp = atomicStampedReference.getStamp();
                boolean success = atomicStampedReference.compareAndSet("A",
                        "C", stamp, (int) System.currentTimeMillis());
                System.out.println(Thread.currentThread().getName() + ", currReference=" + currReference
                        + ", currStamp=" + currStamp + ": 设置新的值为 C, 是否成功: " + success);
            }
        }, "checkThread");

        thiefThread.start();
        thiefThread.join();

        checkThread.start();
        checkThread.join();
    }
}
