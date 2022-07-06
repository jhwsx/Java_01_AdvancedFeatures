package com.java.advanced.features.juc.class4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 由于线程与线程之间是不可见的，所以线程2中的 c.size() 方法永远检测不到是 5，这里加 volatile 给 lists。
 * volatile 一定要尽量去修饰普通的值，不要去修饰引用值，这是因为 volatile 修饰引用类型，这个引用对象指向
 * 的是一个 new 出来的对象，如果这个对象里的成员变量的值改变了，是无法观察到的。
 *
 * 这个程序不理想，因为 volatile 修饰引用类型，这个引用对象指向的是一个 new 出来的对象，如果这个对象里的
 * 成员变量的值改变了，是无法观察到的。
 *
 * @author wangzhichao
 * @since 2020/4/8
 */
public class T02_WithVolatile {
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T02_WithVolatile c = new T02_WithVolatile();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                if (c.size() == 5) {
                    break;
                }
            }
            System.out.println("t2 结束");
        }, "t2").start();
    }
}
