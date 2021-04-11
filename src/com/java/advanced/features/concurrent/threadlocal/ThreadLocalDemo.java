package com.java.advanced.features.concurrent.threadlocal;

/**
 * 参考：https://blog.csdn.net/qq_35190492/article/details/107599875
 *
 * @author wangzhichao
 * @since 2020/3/12
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {
        final ThreadLocal<Boolean> booleanThreadLocal = new ThreadLocal<>();
        booleanThreadLocal.set(true);
        System.out.println(Thread.currentThread().getName() + ", booleanThreadLocal.get()="+booleanThreadLocal.get());
        new Thread("Thread1") {
            @Override
            public void run() {
                super.run();
                booleanThreadLocal.set(false);
                System.out.println(Thread.currentThread().getName() + ", booleanThreadLocal.get()="+booleanThreadLocal.get());
            } 
        }.start();
        new Thread("Thread2") {
            @Override
            public void run() {
                super.run();
                System.out.println(Thread.currentThread().getName() + ", booleanThreadLocal.get()="+booleanThreadLocal.get());
            }
        }.start();
    }
}
