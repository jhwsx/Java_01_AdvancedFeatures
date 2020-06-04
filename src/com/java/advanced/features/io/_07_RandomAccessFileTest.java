package com.java.advanced.features.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 这是读写文件的两种方式，跟文件格式无关：
 * File
 * RandomAccessFile
 *
 * RandomAccessFile 开发中用于断点续传
 */
public class _07_RandomAccessFileTest {
    private static final File file = new File("testtxt/_07_RandomAccessFileTest.txt");
    public static void main(String[] args) {
        testRandomAccessFileWrite();
        testRandomAccessFileRead();
    }

    private static void testRandomAccessFileWrite() {
        if (file.exists()) {
            file.delete();
        }
        try {
            // rw 代表是读写模式
            RandomAccessFile rsf = new RandomAccessFile(file, "rw");
            // seek() 方法
            // 设置到此文件开头测量到的文件指针偏移量，在该位置发生下一个读取或写入操作。
            // 偏移量的设置可能会超出文件末尾。偏移量的设置超出文件末尾不会改变文件的长度。
            // 只有在偏移量的设置超出文件末尾的情况下对文件进行写入才会更改其长度。
            rsf.seek(1000);
            System.out.println("After rsf.seek(1000):");
            printFileInfo(rsf); // rsf length: 0, rsf pointer: 1000
            // 设置文件的长度
            rsf.setLength(1000);
            System.out.println("After rsf.setLength(1000):");
            printFileInfo(rsf); // rsf length: 1000, rsf pointer: 1000

            // 从 1000 处开始写，文件长度被扩展。
            rsf.writeUTF("我爱中国");
            System.out.println("After rsf.writeUTF(\"我爱中国\"):");
            printFileInfo(rsf); // rsf length: 1014, rsf pointer: 1014

            rsf.writeChar('a'); // 2 个字节
            rsf.writeChars("abcde"); // 10 个字节
            System.out.println("After rsf.writeChars(): ");
            printFileInfo(rsf); // rsf length: 1026, rsf pointer: 1026

            rsf.seek(500);
            System.out.println("After rsf.seek(500):");
            printFileInfo(rsf); // rsf length: 1026, rsf pointer: 500

            for (int i = 0; i < 100; i++) {
                rsf.writeChar('a');
            }
            System.out.println("After 100 次 rsf.writeChar('a')：");
            printFileInfo(rsf); // rsf length: 1026, rsf pointer: 700

            // 从文件指针为 100 的地方插入一个长度为 100 的内容全是 1 的字节数组
            // file 的长度是不会变化的，因为下标为 200 没有超过 1026
            byte[] bytes = new byte[100];
            for (int i = 0; i < 100; i++) {
                bytes[i] = 1;
            }
            rsf.seek(100);
            rsf.writeBytes(new String(bytes));
            System.out.println("After rsf.seek(100); 写入 100 个 1：");
            printFileInfo(rsf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中读取内容
     *
     * 需要清楚知道：
     * 文件中有什么内容;
     * 内容的起始字节下标、长度。
     */
    private static void testRandomAccessFileRead() {
        /**
         * 文件内容：
         * 1，从 0 到 100 为空
         * 2，从 101 到 200 是 100 个 1
         * 3，从 201 到 500 是空
         * 4，从 501 到 700 是 100 个 a
         * 5，从 701 到 1000 是空
         * 6，从 1001 到 1014 是 我爱中国
         * 7，从 1015 到 1026 是 aabcde
         */
        try {
            RandomAccessFile rsf = new RandomAccessFile(file, "r");
            System.out.println("读取 2，从 101 到 200 是 100 个 1");
            rsf.seek(100);
            byte[] bytes = new byte[100];
            rsf.read(bytes, 0, 100);
            for (byte b : bytes) {
                System.out.print(b);
            }
            System.out.println();

            System.out.println("读取 4，从 501 到 700 是 100 个 a:");
            rsf.seek(500);
            byte[] bytes2 = new byte[200];
            rsf.read(bytes2, 0, 200);
            System.out.println(new String(bytes2));

            System.out.println("读取 6，从 1001 到 1014 是 我爱中国:");
            rsf.seek(1000);
            String str = rsf.readUTF();
            System.out.println(str);

            System.out.println("读取 7，从 1015 到 1026 是 aabcde:");
            byte[] bytes3 = new byte[12];
            rsf.read(bytes3, 0, 12);
            System.out.println(new String(bytes3));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printFileInfo(RandomAccessFile rsf) throws IOException {
        System.out.println("rsf length: " + rsf.length() + ", rsf pointer: " + rsf.getFilePointer());
    }




































}
