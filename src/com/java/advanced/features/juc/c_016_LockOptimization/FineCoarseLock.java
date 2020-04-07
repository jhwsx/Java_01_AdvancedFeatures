package com.java.advanced.features.juc.c_016_LockOptimization;

import java.util.concurrent.TimeUnit;

/**
 * 只给需要同步的代码加锁，可以使线程争用时间变短，从而提高效率
 * todo 实际操作中，如何进行识别哪些代码是真正需要加锁的呢？
 *
 * @author wangzhichao
 * @since 2020/3/30
 */
public class FineCoarseLock {
    int count = 0;

    synchronized void m1() {
        // 这里不需要同步
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 这是需要同步的代码
        count++;

        // 这里不需要同步
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void m2() {
        // 这里不需要同步
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            // 这是需要同步的代码
            count++;
        }
        // 这里不需要同步
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
