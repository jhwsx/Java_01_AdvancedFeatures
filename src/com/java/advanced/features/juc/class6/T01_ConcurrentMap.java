package com.java.advanced.features.juc.class6;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author wangzhichao
 * @since 2021/4/25
 */
public class T01_ConcurrentMap {
    public static void main(String[] args) {
        test("ConcurrentHashMap", new ConcurrentHashMap<>());
        test("ConcurrentSkipListMap", new ConcurrentSkipListMap<>());
        test("Hashtable", new Hashtable<>());
        test("HashMap", new HashMap<>());
    }

    private static void test(String mapName, Map<String, String> map) {
        Random r = new Random();
        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
                }
                latch.countDown();
            });
        }

        Arrays.asList(ths).forEach(t -> t.start());

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(mapName);
        System.out.println("cost:" + (end - start));
        System.out.println(map.size());
    }
}
