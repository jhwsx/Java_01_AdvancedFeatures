package com.java.advanced.features.io.bixiangdong.p25_datastream;

import java.io.*;

/**
 * DataInputStream DataOutputStream
 * 操作基本数据类型
 *
 * @author wangzhichao
 * @since 2021/8/9
 */
public class DataStreamDemo {
    public static void main(String[] args) throws IOException {
        writeData();
        readData();
    }

    private static void readData() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream("DataStreamDemo.txt"));
        String s = dis.readUTF();
        System.out.println(s);
        dis.close();
    }

    private static void writeData() throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("DataStreamDemo.txt"));
        // 写入的不仅仅是字符串，还有格式头。
        dos.writeUTF("好好学习");
        dos.close();
    }
}
