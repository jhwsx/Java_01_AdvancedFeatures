package com.java.advanced.features.io.bixiangdong.p27_encode;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 在 Java 中，字符串"abcd"与字符串"ab你好"的长度是一样的，都是四个字符。
 * 但对应的字节数不同，一个汉字占两个字节。
 * 定义一个方法，按照最大的字节数来取子串。
 * 如：对于"ab你好"，如果取三个字节，那么子串就是"ab"与"你"字的半个，那么半个就要舍弃。
 * 如果取四个字节就是"ab你",取五个字节还是"ab你"。
 *
 * @author wangzhichao
 * @since 2021/8/10
 */
public class Test2 {
    public static void main(String[] args) throws UnsupportedEncodingException {
//        utf8();
        gbk();
    }

    private static void gbk() throws UnsupportedEncodingException {
//        String str = "ab你好cd谢谢";
//        // 97, 98, -60, -29, -70, -61, 99, 100, -48, -69, -48, -69
//        byte[] bytes = str.getBytes("GBK");
//        for (int i = 0; i < bytes.length; i++) {
//            System.out.println("截取" + (i + 1) + "个字节，结果是" + getSubStringGBKByByteNum(str, i + 1));
//        }

        String str2 = "ab琲琲";
        // 97, 98, -84, 105, -84, 105
        byte[] bytes2 = str2.getBytes("GBK");
        for (int i = 0; i < bytes2.length; i++) {
            System.out.println("截取" + (i + 1) + "个字节，结果是" + getSubStringGBKByByteNum(str2, i + 1));
        }
    }

    private static void utf8() {
        String str = "ab你好cd谢谢";
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < bytes.length; i++) {
            System.out.println("截取" + (i + 1) + "个字节，结果是" + getSubStringUTF8ByByteNum(str, i + 1));
        }
    }

    public static String getSubStringGBKByByteNum(String str, int byteNum) throws UnsupportedEncodingException {
        byte[] bytes = str.getBytes("GBK");
        System.out.println(Arrays.toString(bytes));
        // GB_2312 中文两个字节表示，都是负数
        // GBK 中文两个字节表示，第一个字节是负数，第二个字节有的是正数，有的是负数。
        int count = 0;
        for (int i = byteNum - 1; i >= 0; i--) {
            if (bytes[i] < 0) {
                count++;
            } else {
                break;
            }
        }
        if (count % 2 == 0) {
            // 正好截取到完汉字 97, 98, -60, -29, count = 2
            return new String(bytes, 0, byteNum, "gbk");
        } else  {
            // 只截取了汉字的两个字节 97, 98, -60, count = 1
            return new String(bytes, 0, byteNum - 1, "gbk");
        }
    }

    public static String getSubStringUTF8ByByteNum(String str, int byteNum) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        // 97, 98, -28, -72, -128, -28, -70, -116
        // 从后往前
        int count = 0;
        for (int i = byteNum - 1; i >= 0; i--) {
            if (bytes[i] < 0) {
                count++;
            } else {
                break;
            }
        }
        if (count % 3 == 0) {
            // 正好截取到完汉字 97, 98, -28, -72, -128, count = 3
            return new String(bytes, 0, byteNum, StandardCharsets.UTF_8);
        } else if (count % 3 == 1) {
            // 只截取了汉字的一个字节 97, 98, -28 count = 1
            return new String(bytes, 0, byteNum - 1, StandardCharsets.UTF_8);
        } else {
            // 只截取了汉字的两个字节 97, 98, -28, -72 count = 2
            return new String(bytes, 0, byteNum - 2, StandardCharsets.UTF_8);
        }
    }


}
