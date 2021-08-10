package com.java.advanced.features.io.bixiangdong.p27_encode;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * 使用 gbk 进行编码，然后别人使用了 utf-8 进行了解码；在不让别人改动的情况下，如何正确地进行解码？
 *
 * @author wangzhichao
 * @since 2021/8/9
 */
public class Problem2Demo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "你好";
        // 使用 gbk 进行编码
        byte[] bytes = s.getBytes("gbk");
        System.out.println("bytes:" + Arrays.toString(bytes));
        // 别人拿到之后使用 utf-8 进行了解码
        String s2 = new String(bytes, "utf-8");
        System.out.println("s2 = " + s2); // 解码接错了
        // 我继续修正
        // 使用 utf-8 对 s2 进行编码，拿到原始的字节数组
        byte[] bytes1 = s2.getBytes("utf-8");
        // 如果 bytes1 与 bytes 不一样，那么就没有办法修正了。
        System.out.println("byte1:" + Arrays.toString(bytes1));
        // 再使用 gbk 进行解码
        String s3 = new String(bytes1, "gbk");
        System.out.println("s3 = " + s3);
    }
}
