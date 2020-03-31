package com.java.advanced.features.juc.c_012_singleton;

/**
 * 懒汉式单例
 * 在多线程环境下不能保证是单例了。
 *
 * @author wangzhichao
 * @since 2020/3/30
 */
public class Mgr03 {
    private static Mgr03 INSTANCE;

    private Mgr03() {
        //no instance
    }

    public static Mgr03 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr03();
        }
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() ->
                    System.out.println(Mgr03.getInstance().hashCode())).start();
        }
    }
}
