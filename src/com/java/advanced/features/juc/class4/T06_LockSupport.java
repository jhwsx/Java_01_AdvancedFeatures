package com.java.advanced.features.juc.class4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 使用 LockSupport 来实现
 *
 * @author wangzhichao
 * @since 2021/4/19
 */
public class T06_LockSupport {
    volatile List lists = new ArrayList();
    private static Thread t1;
    private static Thread t2;

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T04_NotifyHoldingLock2 c = new T04_NotifyHoldingLock2();
        // 我们让 t2 线程先启动
        t2 = new Thread(() -> {
            System.out.println("t2 启动");
            // 如果 size 不等于 5，就处于等待状态
            while (c.size() != 5) { // 这里应该用 while 吧。
                // 使用 LockSupport 的 park() 方法阻塞当前的线程 t2
                LockSupport.park();
            }
            System.out.println("t2 结束");
            // 唤醒线程1继续执行吧。
            LockSupport.unpark(t1);
        }, "t2");
        t2.start();
        // 休眠 1 秒，是保证线程 t1 一定在 t2 之后启动。
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1 = new Thread(() -> {
            System.out.println("t1 启动");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);
                if (c.size() == 5) {
                    // 唤醒线程2继续执行吧
                    LockSupport.unpark(t2);
                    // 阻塞当前的线程 t1
                    LockSupport.park();
                }
                    /*try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
            }
        }, "t1");
        t1.start();
    }
}
