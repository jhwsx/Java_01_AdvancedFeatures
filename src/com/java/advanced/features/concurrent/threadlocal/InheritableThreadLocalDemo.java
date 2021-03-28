package com.java.advanced.features.concurrent.threadlocal;

/**
 * InheritableThreadLocal 可以共享线程的ThreadLocal数据
 *
 * @author wangzhichao
 * @since 2021/3/28
 */
public class InheritableThreadLocalDemo {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("这是主线程设置的值。");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String s = threadLocal.get();
                System.out.println("在子线程中获取到主线程设置的值：" + s);
            }
        }).start();
    }
}
