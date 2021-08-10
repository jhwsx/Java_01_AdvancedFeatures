package com.java.advanced.features.io.bixiangdong.p23_raf;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile 随机访问文件，不是 io 体系中的子类。
 * 1, 该对象既能读又能写；
 * 2, 内部维护一个 byte 数组，可以通过指针操作数组中的元素；
 * 3, 可以通过 getFilePointer() 方法获取指针的位置，并通过 seek 方法设置指针的位置；
 * 4, 该对象是对字节输入流和输出流进行了封装；
 * 5, 该对象的源或者目的只能是文件
 *
 * 实际应用：断点续传
 * @author wangzhichao
 * @since 2021/8/5
 */
public class RandomAccessFileDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("raf.txt", "rw");
        byte[] bytes1 = "王志超".getBytes();
        randomAccessFile.write(bytes1);
        // 0010 0110 0001 = 609
        // 只会保留低八位 0110 0001 = 97
        randomAccessFile.write(609);
        randomAccessFile.writeInt(609);
        byte[] bytes = new byte[bytes1.length];
        randomAccessFile.seek(0);
        randomAccessFile.read(bytes);
        System.out.println(new String(bytes));
        System.out.println(randomAccessFile.read());
        System.out.println(randomAccessFile.readInt());
        System.out.println("pos:" + randomAccessFile.getFilePointer());
        randomAccessFile.close();
    }
}
