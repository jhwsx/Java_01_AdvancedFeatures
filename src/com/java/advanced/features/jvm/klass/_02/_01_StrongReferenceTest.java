package com.java.advanced.features.jvm.klass._02;

import java.io.IOException;

/**
 * 强引用演示
 * 强引用：最普通的引用，通过一个赋值运算符（=），就得到了一个强引用。
 * 当内存空间不足时，JVM 宁愿抛出 OOM 错误，使程序终止，也不会回收强引用
 * 的对象来解决内存不足的问题。
 *
 * 中断或者回收强引用，可以设置引用为 null，这样 JVM 会在合适的时间，进行
 * 垃圾回收。
 * 设置 Vm options：-Xms20m -Xmx20m
 * 造成 OOM，看强引用会不会回收。
 */
public class _01_StrongReferenceTest {
    public static void main(String[] args) throws IOException {
        MyObject strongReference = new MyObject();
        System.gc();
        System.out.println("After gc:");
        System.out.println(strongReference);
        try {
            // 分配25M的空间，造成 OOM
            byte[] bytes = new byte[25 * 1024 * 1024];
        } catch (Throwable e) {
            System.out.println("After OOM:");
            System.out.println(strongReference);
        }
        strongReference = null;
        System.gc();
        System.in.read(); // 阻塞 main 线程的，给垃圾回收器执行时间。
    }

    static class MyObject {
        // 当被回收的时候，会调用一次 finalize 方法
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("finalize");
        }
    }
}
