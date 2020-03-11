package com.java.advanced.features.concurrent.create;
// 1, 定义一个继承 Thread 的子类
class MyThread extends Thread {
    // 2, 在子类中覆盖 Thread 中的 run 方法
    @Override
    public void run() {
        System.out.println("I am executing a heavy task.");
    }
}
public class Create1 {
    public static void main(String[] args) {
        // 3, 创建 子类对象得到线程对象
        Thread myThread = new MyThread();
        // 4, 调用线程对象的 start 方法启动线程
        myThread.start();
    }
}

/**
 打印結果：
 I am executing a heavy task.
 */
