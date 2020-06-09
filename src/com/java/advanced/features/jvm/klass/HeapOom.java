package com.java.advanced.features.jvm.klass;

/**
 * 演示堆内存溢出（直接溢出）
 * VM Args：-Xms30m -Xmx30m -XX:+PrintGCDetails
 * 表示设置堆的最小值参数 -Xms为30m，设置堆的最大值参数 -Xmx 为30m，
 * 两者设置为一样的值就避免了堆自动扩展，就限制堆的大小为 30M;
 */
public class HeapOom {
    public static void main(String[] args) {
        // 分配一个 35M 的数组
        byte[] bytes = new byte[35 * 1024 * 1024];
    }
}
