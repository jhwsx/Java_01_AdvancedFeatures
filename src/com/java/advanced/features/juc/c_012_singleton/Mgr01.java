package com.java.advanced.features.juc.c_012_singleton;

/**
 * 饿汉式
 * 这种方式的优点是在多线程环境下可以保证只会有一个实例
 * 缺点是不管是否用到类实例，在类装载时就完成实例化。
 *
 * @author wangzhichao
 * @since 2020/3/30
 */
public class Mgr01 {
    private static final Mgr01 INSTANCE = new Mgr01();

    private Mgr01() {
    }

    public static Mgr01 getInstance() {
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        Mgr01 m1 = Mgr01.getInstance();
        Mgr01 m2 = Mgr01.getInstance();
        System.out.println(m1 == m2);
    }
}
