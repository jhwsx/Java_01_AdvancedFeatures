package com.java.advanced.features.juc.class6;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author wangzhichao
 * @since 2021/4/25
 */
public class T02_CopyOnWriteList {
    public static void main(String[] args) {
        test("ArrayList", new ArrayList<>()); // 不是线程安全的。
        test("Vector", new Vector<>());
        test("CopyOnWriteArrayList", new CopyOnWriteArrayList<>());
        test("synchronizedList", Collections.synchronizedList(new ArrayList<>()));
    }

    private static void test(String listName, List<String> lists) {
        Random r = new Random();
        Thread[] ths = new Thread[100];
        // 创建 100 个线程
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        lists.add("a" + r.nextInt(10000));
                    }
                }
            });
        }
        System.out.println(listName);
        runAndComputeTime(ths);
        System.out.println(lists.size());
    }

    static void runAndComputeTime(Thread[] ths) {
        long start = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t -> t.start());
        Arrays.asList(ths).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
