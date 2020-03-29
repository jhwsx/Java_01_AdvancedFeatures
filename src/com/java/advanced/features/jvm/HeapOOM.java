package com.java.advanced.features.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * -Xms20m -Xmx20m 表示设置堆的最小值参数 -Xms为20m，设置堆的最大值参数 -Xmx 为20m，
 * 两者设置为一样的值就避免了堆自动扩展，就限制堆的大小为 20M;
 * -XX:+HeapDumpOnOutOfMemoryError 让虚拟机在内存溢出时 dump 出当前的内存堆转存储快照
 * 以便进行事后分析。
 */
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
