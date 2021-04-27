package com.java.advanced.features.juc.class6;

import java.util.concurrent.LinkedTransferQueue;

/**
 * TransferQueue
 * 1.7 才添加的。
 *
 * 参考：https://ifeve.com/java-transfer-queue/
 * @author wangzhichao
 * @since 2021/4/27
 */
public class T09_TransferQueue {
    public static void main(String[] args) {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            strs.transfer("aaa");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        strs.put("aaa");

    }
}
