package com.java.advanced.features.juc.c_012_singleton;

/**
 * 懒汉式单例
 * 使用类锁
 * <p>
 * 在多线程环境下可以保证是单例。
 * 但是需要进行锁优化：就是尽肯能少地锁代码。
 *
 * @author wangzhichao
 * @since 2020/3/30
 */
public class Mgr04 {
    private static Mgr04 INSTANCE;

    private Mgr04() {
        //no instance
    }

    public static synchronized Mgr04 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr04();
        }
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() ->
                    System.out.println(Mgr04.getInstance().hashCode())).start();
        }
    }
}
