package com.java.advanced.features.juc.c_012_singleton;

/**
 * 懒汉式单例
 * 使用对象锁，进行锁细化
 *
 * 在多线程环境下不可以保证是单例。
 *
 * @author wangzhichao
 * @since 2020/3/30
 */
public class Mgr05 {
    private static Mgr05 INSTANCE;

    private Mgr05() {
        //no instance
    }

    public static Mgr05 getInstance() {
        if (INSTANCE == null) {
            synchronized (Mgr05.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Mgr05();
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
                    System.out.println(Mgr05.getInstance().hashCode())).start();
        }
    }
}
