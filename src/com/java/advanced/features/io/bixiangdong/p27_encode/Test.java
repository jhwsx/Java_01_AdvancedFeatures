package com.java.advanced.features.io.bixiangdong.p27_encode;

import java.nio.charset.StandardCharsets;

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
public class Test {
    public static void main(String[] args) {
        System.out.println(getSubStringByByteNum("ab一二", 1));
        System.out.println(getSubStringByByteNum("ab一二", 2));
        System.out.println(getSubStringByByteNum("ab一二", 3));
        System.out.println(getSubStringByByteNum("ab一二", 4));
        System.out.println(getSubStringByByteNum("ab一二", 5));
    }

    public static String getSubStringByByteNum(String str, int byteNum) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        System.out.println("bytes.length=" + bytes.length + ", byteNum=" + byteNum);
        printBytes(bytes);
        byte[] result = new byte[bytes.length];
        // 记录已经存放的字节数目
        int index = 0;
        for (int i = 0; i < bytes.length; i++) {
            String binaryString = Integer.toBinaryString(bytes[i] & 255);
            if (index >= byteNum) {
                break;
            }
            if (binaryString.length() < 8) {
                // 是 ASCII 码
                result[index++] = bytes[i];
            } else {
                if (binaryString.startsWith("0")) {
                    // 是单个字节的utf-8，直接放入结果数组
                    result[index++] = bytes[i];
                } else if (binaryString.startsWith("110")) {
                    // 是两个字节的utf-8
                    // 判断是否可以把两个字节都收入结果数组
                    if (index + 2 <= byteNum) {
                        result[index++] = bytes[i];
                    } else {
                        break;
                    }
                } else if (binaryString.startsWith("1110")) {
                    // 是三个字节的utf-8
                    // 判断是否可以把三个字节都收入结果数组
                    if (index + 3 <= byteNum) {
                        result[index++] = bytes[i];
                    } else {
                        break;
                    }
                } else if (binaryString.startsWith("10")) {
                    result[index++] = bytes[i];
                }
            }
        }
        return new String(result, StandardCharsets.UTF_8);
    }

    private static void printBytes(byte[] bytes) {
        for (byte aByte : bytes) {
            String binaryString = Integer.toBinaryString(aByte & 255);
            System.out.println(binaryString);
        }
    }

}
