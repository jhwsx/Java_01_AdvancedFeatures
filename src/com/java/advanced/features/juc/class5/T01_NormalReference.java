package com.java.advanced.features.juc.class5;

import java.io.IOException;

/**
 * @author wangzhichao
 * @since 2021/4/24
 */
public class T01_NormalReference {
    public static void main(String[] args) throws IOException {
        M m = new M(); // 这种就是强引用了。
        // m = null;
        System.gc();
        System.in.read(); // 阻塞主线程
    }
}
