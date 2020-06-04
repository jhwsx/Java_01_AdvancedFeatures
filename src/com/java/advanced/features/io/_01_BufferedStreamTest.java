package com.java.advanced.features.io;

import java.io.*;

public class _01_BufferedStreamTest {
    // a - z 的 byte 数组
    private static final byte[] byteArray = {
            0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
            0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A
    };

    public static void main(String[] args) {
//        bufferedOutputStreamTest();
        bufferedInputStreamTest();
    }

    private static void bufferedOutputStreamTest() {
        File file = new File("testtxt/_01_BufferedStreamTest.txt");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(file));
            // 写指定的值到输出流中
            bos.write(byteArray[0]);
            // 把指定的 byte 数组，从指定的位置开始写多长的 byte 值到输出流中。
            bos.write(byteArray, 1, byteArray.length - 1);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void bufferedInputStreamTest() {
        try {
            File file = new File("testtxt/_01_BufferedStreamTest.txt");
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(file));
            // 先读入 10 个 byte
            for (int i = 0; i < 10; i++) {
                // 返回可以从此输入流读取（或跳过）、且不受此输入流接下来的方法调用阻塞的估计字节数。
                if (bis.available() >= 0) {
                    // 下一个数据字节，如果到达流末尾，则返回 -1。
                    System.out.println(byteToString((byte) bis.read()));
                }
            }
            // TODO mark，skip，reset 方法不懂
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String byteToString(byte b) {
        byte[] bArray = {b};
        return new String(bArray);
    }
}
