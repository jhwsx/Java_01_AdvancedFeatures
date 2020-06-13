package com.java.advanced.features.jvm.klass._01;

import java.util.LinkedList;
import java.util.List;

/**
 * VM Args：-Xms20m -Xmx20m -XX:+PrintGCDetails
 * 表示设置堆的最小值参数 -Xms为30m，设置堆的最大值参数 -Xmx 为20m，
 * 两者设置为一样的值就避免了堆自动扩展，就限制堆的大小为 20M;
 */
public class HeapOom2 {
    public static void main(String[] args) {
        List<Object> list = new LinkedList<>();
        int i = 0;
        while (true) {
            i++;
            // 每添加 10000 个，打印一行日志
            if (i % 10000 == 0) {
                System.out.println("i = " + i);
            }
            list.add(new Object());
        }
    }
}
