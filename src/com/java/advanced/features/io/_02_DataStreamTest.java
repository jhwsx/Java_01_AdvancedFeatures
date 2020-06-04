package com.java.advanced.features.io;

import java.io.*;
// TODO 补充一下进制转换的知识
public class _02_DataStreamTest {
    public static void main(String[] args) {
//        testDataOutputStream();
        testDataInputStream();
    }

    private static void testDataOutputStream() {
        try {
            DataOutputStream dos = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(
                                    new File("testtxt/_02_DataStreamTest.txt"))));
            dos.writeBoolean(true);
            dos.writeByte((byte) 0x41);
            dos.writeChar((char) 0x4243);
            dos.writeShort((short) 0x4445);
            dos.writeInt(0x12345678);
            dos.writeLong(0x987654321L);
            dos.writeUTF("abcdefghijklmnopqrstuvwxyz严12");
            dos.writeLong(0x023433L);
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testDataInputStream() {
        try {
            DataInputStream dis = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(
                                    new File("testtxt/_02_DataStreamTest.txt")
                            )
                    )
            );
//            dos.writeBoolean(true);
//            dos.writeByte((byte) 0x41);
//            dos.writeChar((char) 0x4243);
//            dos.writeShort((short) 0x4445);
//            dos.writeInt(0x12345678);
//            dos.writeLong(0x987654321L);
//            dos.writeUTF("abcdefghijklmnopqrstuvwxyz严12");
//            dos.writeLong(0x023433L);
            // 按写出的顺序，来读入
            System.out.println(dis.readBoolean());
            System.out.println(byteToHexString(dis.readByte()));
            System.out.println(charToHexString(dis.readChar()));
            System.out.println(shortToHexString(dis.readShort()));
            System.out.println(Integer.toHexString(dis.readInt()));
            System.out.println(Long.toHexString(dis.readLong()));
            System.out.println(dis.readUTF());
            System.out.println(Long.toHexString(dis.readLong()));
            dis.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取 byte 对应的 16 进制字符串
     */
    private static String byteToHexString(byte val) {
        return Integer.toHexString(val & 0xff);
    }
    /**
     * 获取 char 对应的 16 进制字符串
     */
    private static String charToHexString(char val) {
        return Integer.toHexString(val);
    }
    /**
     * 获取 short 对应的 16 进制字符串
     */
    private static String shortToHexString(short val) {
        return Integer.toHexString(val & 0xffff);
    }
}
