package com.java.advanced.features.juc.c_012_singleton;

/**
 * 懒汉式单例
 * 双重校验锁（dcl） + volatile
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
