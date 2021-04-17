package com.java.advanced.features.juc.c_009;

/**
 * synchronized 具有可重入性。
 * 可重入就是说线程可以进入任何一个它已经拥有的锁所同步着的代码。
 *
 * @author wangzhichao
 * @since 2020/3/28
 */
public class T {
    synchronized void m1() {
        System.out.println(Thread.currentThread().getName()+", m1 start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println(Thread.currentThread().getName()+", m1 end");
    }
    synchronized void m2() {
        System.out.println(Thread.currentThread().getName()+", m2 start");
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+", m2 end");
    }

    public static void main(String[] args) {
        // 在线程中执行的
        new T().m1();
    }
}
