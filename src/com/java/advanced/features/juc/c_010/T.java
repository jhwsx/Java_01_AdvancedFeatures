package com.java.advanced.features.juc.c_010;

/**
 * 模拟一个父类子类的概念，父类 的 m 方法是 synchronized，子类调用 super.m 方法的时候
 * 必须得可重入，否则就会出现问题
 *
 * @author wangzhichao
 * @since 2020/3/28
 */
public class T {
    synchronized void m() {
        System.out.println("m start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        new TT().m();
    }
}

class TT extends T {
    @Override
    synchronized void m() {
        System.out.println("child m start");
        super.m();
        System.out.println("child m end");
    }
}

/*
打印结果：
child m start
m start
m end
child m end
*/
