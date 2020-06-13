package com.java.advanced.features.jvm.klass._01;

import java.nio.ByteBuffer;

/**
 * 演示直接内存（堆外内存）的 OOM
 * VM Args：-XX:MaxDirectMemorySize=100m
 * -XX:MaxDirectMemorySize=100m 表示限制直接内存容量最大为 100M
 */
public class DirectMemoryOom {
    public static void main(String[] args) {
        // 使用 nio
        // 直接分配 128M 的直接内存，但是限制的是 100M
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(128 * 1024 * 1024);
    }
}
