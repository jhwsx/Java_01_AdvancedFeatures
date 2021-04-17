package com.java.advanced.features.juc.c_005;

/**
 * @author wangzhichao
 * @since 2021/4/15
 */
public class SynchronizedDemo {
    // 共享变量
    private boolean ready = false;
    private int result = 0;
    private int number = 1;
    // 写操作
    public void write() {
        ready = true; // 1.1
        Thread.yield();
        number = 2; // 1.2
    }
    // 1.1 -> 2.1 -> 2.2 -> 1.2, 结果是 3
    // 1.2 -> 2.1 -> 2.2 -> 1.1, 结果是 0
    // 2.2 -> 2.1 -> 1.1 -> 1.2, 结果是 6
    public void read() {
        if (ready) { // 2.1
            result = number * 3; // 2.2
        }
        System.out.println("result 的值为：" + result);
    }

    // 内部线程类
    private class ReadWriteThread extends Thread {
        // 根据构造方法中传入的 flag 参数，确定线程执行读操作还是写操作
        private boolean flag;

        public ReadWriteThread(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            if (flag) {
                // 构造方法中传入 true，执行写操作
                write();
            } else {
                // 构造方法中传入 false，执行读操作
                read();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            SynchronizedDemo syncDemo = new SynchronizedDemo();
            // 启动线程执行写操作
            syncDemo.new ReadWriteThread(true).start();
            // 启动线程执行读操作
            syncDemo.new ReadWriteThread(false).start();
        }
    }
}
