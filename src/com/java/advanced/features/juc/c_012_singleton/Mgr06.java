package com.java.advanced.features.juc.c_012_singleton;

/**
 * 懒汉式单例
 * 双重校验锁（dcl） + volatile
 * volatile 在这里的作用到底是什么？
 * INSTANCE = new Mgr06() 汇编后会分为四步：
 * 1，给指定申请内存空间；
 * 2，给成员变量默认初始化；
 * 3，调用构造方法，显式初始化成员变量；
 * 4，INSTANCE 变量和申请好的内存空间建立连接。
 * 如果不加 volatile，会发生指令重排：
 * 1 -> 4 -> 2 -> 3
 * 这样的话，会有问题了：我们没有显式初始完毕，就有线程拿到了对象。
 *
 * @author wangzhichao
 * @since 2020/3/30
 */
public class Mgr06 {
    private volatile static Mgr06 INSTANCE;

    private Mgr06() {
        //no instance
    }

    public static Mgr06 getInstance() {
        if (INSTANCE == null) {
            synchronized (Mgr06.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Mgr06();
                }
            }
        }
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() ->
                    System.out.println(Mgr06.getInstance().hashCode())).start();
        }
    }
}
