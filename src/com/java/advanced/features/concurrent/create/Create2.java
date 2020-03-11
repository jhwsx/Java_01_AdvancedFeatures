package com.java.advanced.features.concurrent.create;
// 1. 定义实现 Runnable 接口的类；
class MyRunnable implements Runnable {
    // 2. 在实现类中覆盖 Runnable 接口的 run 方法；
    @Override
    public void run() {
        System.out.println("I am executing a heavy task.");
    }
}
public class Create2 {
    public static void main(String[] args) {
        // 3. 通过 Thread 类创建线程对象，把 Runnable 接口的实现类
        // 通过 Thread 的构造方法进行传递；
        Thread thread = new Thread(new MyRunnable());
        // 4. 调用线程对象的 start 方法启动线程。
        thread.start();
    }
}
/*
打印結果：
I am executing a heavy task.
 */