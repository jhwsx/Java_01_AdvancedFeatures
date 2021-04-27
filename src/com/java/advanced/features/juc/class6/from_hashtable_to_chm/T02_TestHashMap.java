package com.java.advanced.features.juc.class6.from_hashtable_to_chm;

import java.util.HashMap;
import java.util.UUID;

/**
 * 这个程序运行会报错，因为 HashMap 不是线程安全的。
 * 参考：https://www.freesion.com/article/2057771003/
 *
 * @author wangzhichao
 * @since 2021/4/24
 */
public class T02_TestHashMap {
    static HashMap<UUID, UUID> m = new HashMap<>();

    static int count = Constants.COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static final int THREAD_COUNT = Constants.THREAD_COUNT;

    static {
        // 事先生成好 100 万个 key 和 100 万个 value
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    // 每个线程负责装一部分数据
    static class MyThread extends Thread {
        int start;
        // gap 表示每个线程负责装多少个 key，value 对。
        int gap = count / THREAD_COUNT;

        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < start + gap; i++) {
                m.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 100 个线程
        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < threads.length; i++) {
            // 参数表示从哪里开始添加
            threads[i] = new MyThread(i * (count / THREAD_COUNT));
        }

        for (Thread t : threads) {
            t.start();
        }
        // 等新开的线程都死亡了，再执行主线程。
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);

        System.out.println(m.size());
    }
}

/*
Exception in thread "Thread-63" java.lang.ClassCastException: java.util.HashMap$Node cannot be cast to java.util.HashMap$TreeNode
	at java.util.HashMap$TreeNode.moveRootToFront(HashMap.java:1835)
	at java.util.HashMap$TreeNode.putTreeVal(HashMap.java:2014)
	at java.util.HashMap.putVal(HashMap.java:638)
	at java.util.HashMap.put(HashMap.java:612)
	at com.java.advanced.features.juc.class6.from_hashtable_to_chm.T02_TestHashMap$MyThread.run(T02_TestHashMap.java:39)
Exception in thread "Thread-62" java.lang.ClassCastException: java.util.HashMap$Node cannot be cast to java.util.HashMap$TreeNode
	at java.util.HashMap$TreeNode.moveRootToFront(HashMap.java:1835)
	at java.util.HashMap$TreeNode.putTreeVal(HashMap.java:2014)
	at java.util.HashMap.putVal(HashMap.java:638)
	at java.util.HashMap.put(HashMap.java:612)
	at com.java.advanced.features.juc.class6.from_hashtable_to_chm.T02_TestHashMap$MyThread.run(T02_TestHashMap.java:39)
Exception in thread "Thread-24" java.lang.ClassCastException: java.util.HashMap$Node cannot be cast to java.util.HashMap$TreeNode
	at java.util.HashMap$TreeNode.moveRootToFront(HashMap.java:1835)
	at java.util.HashMap$TreeNode.putTreeVal(HashMap.java:2014)
	at java.util.HashMap.putVal(HashMap.java:638)
	at java.util.HashMap.put(HashMap.java:612)
	at com.java.advanced.features.juc.class6.from_hashtable_to_chm.T02_TestHashMap$MyThread.run(T02_TestHashMap.java:39)
Exception in thread "Thread-29" java.lang.ClassCastException: java.util.HashMap$Node cannot be cast to java.util.HashMap$TreeNode
	at java.util.HashMap$TreeNode.moveRootToFront(HashMap.java:1835)
	at java.util.HashMap$TreeNode.putTreeVal(HashMap.java:2014)
	at java.util.HashMap.putVal(HashMap.java:638)
	at java.util.HashMap.put(HashMap.java:612)
	at com.java.advanced.features.juc.class6.from_hashtable_to_chm.T02_TestHashMap$MyThread.run(T02_TestHashMap.java:39)
Exception in thread "Thread-28" java.lang.ClassCastException: java.util.HashMap$Node cannot be cast to java.util.HashMap$TreeNode
	at java.util.HashMap$TreeNode.moveRootToFront(HashMap.java:1835)
	at java.util.HashMap$TreeNode.putTreeVal(HashMap.java:2014)
	at java.util.HashMap.putVal(HashMap.java:638)
	at java.util.HashMap.put(HashMap.java:612)
	at com.java.advanced.features.juc.class6.from_hashtable_to_chm.T02_TestHashMap$MyThread.run(T02_TestHashMap.java:39)
Exception in thread "Thread-77" java.lang.ClassCastException: java.util.HashMap$Node cannot be cast to java.util.HashMap$TreeNode
	at java.util.HashMap$TreeNode.moveRootToFront(HashMap.java:1835)
	at java.util.HashMap$TreeNode.putTreeVal(HashMap.java:2014)
	at java.util.HashMap.putVal(HashMap.java:638)
	at java.util.HashMap.put(HashMap.java:612)
	at com.java.advanced.features.juc.class6.from_hashtable_to_chm.T02_TestHashMap$MyThread.run(T02_TestHashMap.java:39)
Exception in thread "Thread-78" java.lang.ClassCastException: java.util.HashMap$Node cannot be cast to java.util.HashMap$TreeNode
	at java.util.HashMap$TreeNode.moveRootToFront(HashMap.java:1835)
	at java.util.HashMap$TreeNode.putTreeVal(HashMap.java:2014)
	at java.util.HashMap.putVal(HashMap.java:638)
	at java.util.HashMap.put(HashMap.java:612)
	at com.java.advanced.features.juc.class6.from_hashtable_to_chm.T02_TestHashMap$MyThread.run(T02_TestHashMap.java:39)
Exception in thread "Thread-75" java.lang.ClassCastException: java.util.HashMap$Node cannot be cast to java.util.HashMap$TreeNode
	at java.util.HashMap$TreeNode.moveRootToFront(HashMap.java:1835)
	at java.util.HashMap$TreeNode.putTreeVal(HashMap.java:2014)
	at java.util.HashMap.putVal(HashMap.java:638)
	at java.util.HashMap.put(HashMap.java:612)
	at com.java.advanced.features.juc.class6.from_hashtable_to_chm.T02_TestHashMap$MyThread.run(T02_TestHashMap.java:39)

*/