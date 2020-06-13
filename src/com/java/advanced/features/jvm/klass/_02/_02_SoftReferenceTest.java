package com.java.advanced.features.jvm.klass._02;

import java.lang.ref.SoftReference;

/**
 * 软引用演示
 * 软引用：当一个对象只有软引用时，则当空间不足的时候才会回收它，可以用来构建敏感数据的缓存
 * （如网页缓存、图片缓存等）。
 *
 * 软引用可以和一个引用队列一同使用，当所引用的对象被回收，软引用便被加入到引用队列。
 * 设置 Vm options：-Xms20m -Xmx20m
 * 造成 OOM，看软引用会不会回收。
 */
public class _02_SoftReferenceTest {

    public static void main(String[] args)  {
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[10 * 1024 * 1024]);
        System.out.println(softReference.get());
        System.gc();
        try {
            Thread.sleep(500); // 休眠 500 ms，给垃圾回收器时间回收
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(softReference.get());
        byte[] bytes = new byte[12 * 1024 * 1024];
        System.out.println(softReference.get());
    }
}

