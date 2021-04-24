package com.java.advanced.features.juc.class5;

import java.lang.ref.WeakReference;

/**
 * 弱引用：当有一个对象被一个弱引用指向的时候，只要垃圾回收看到这个弱引用，就会把它回收掉。
 *
 * 在 ThreadLocal 里的应用
 * @author wangzhichao
 * @since 2021/4/24
 */
public class T03_WeakReference {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get()); // null


    }
}
