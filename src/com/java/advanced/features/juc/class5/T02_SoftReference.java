package com.java.advanced.features.juc.class5;

import java.lang.ref.SoftReference;

/**
 * 软引用：当有一个对象被一个软引用所指向的时候，只有系统内存不够用的时候，才会回收它。
 * 实际应用：做缓存。
 *
 * 本例需要设置堆内存最大为 20m
 *
 * VMoptions： -Xmx20m
 *
 * @author wangzhichao
 * @since 2021/4/24
 */
public class T02_SoftReference {
    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(m.get());
        System.gc();
        try {
            // 为什么要睡眠一下？因为 System.gc() 是在别的线程里。
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());
        // 再分配一个数组，heap 将装不下，这时候系统会垃圾回收，先回收一次，如果不够，会把软引用干掉
        byte[] b = new byte[1024 * 1024 * 15];
        System.out.println(m.get());
    }
}
