package com.java.advanced.features.juc.class5;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 视频资源：https://www.bilibili.com/video/BV11K411F7mj?p=5&spm_id_from=pageDriver
 * @author wangzhichao
 * @since 2021/4/20
 */
public class TestReentrantLock {
    private static volatile int i = 0;
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        i++;
        lock.unlock();
    }
}
