package com.java.advanced.features.concurrent.stopthread.flag;

class MyRunnable implements Runnable {
    private FlagBean flagBean;

    public MyRunnable(FlagBean flagBean) {
        this.flagBean = flagBean;
    }

    @Override
    public synchronized void run() {
        while (!flagBean.isFlag()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "...." + e);
            }
        }
    }
}
class FlagBean {
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
public class UseFlag {
    public static void main(String[] args) {
        FlagBean flagBean = new FlagBean();
        MyRunnable target = new MyRunnable(flagBean);
        Thread thread1 = new Thread(target);
        Thread thread2 = new Thread(target);
        thread1.start();
        thread2.start();
        int i = 0;
        while (true) {
            if (i >= 30) {
                flagBean.setFlag(true);
                break;
            }
            System.out.println(Thread.currentThread().getName() + "。。。" + (i++));
        }
    }
}
