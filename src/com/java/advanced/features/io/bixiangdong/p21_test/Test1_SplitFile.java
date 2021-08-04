package com.java.advanced.features.io.bixiangdong.p21_test;

import java.io.*;
import java.util.Arrays;

/**
 * 需求：文件切割器
 * 把一个文件按大小进行切割
 *
 * @author wangzhichao
 * @since 2021/8/3
 */
public class Test1_SplitFile {
    public static void main(String[] args) throws IOException {
//        File file = new File("H:\\xinjing.mp4");
        File file = new File("H:\\Kotlin实战.pdf");
        File dir = new File("H:\\partFiles");
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
        // 每份5M
        int partSize = 5 * 1024 * 1024;
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int len;
        int currentSize = 0;
        FileOutputStream fos = null;
        int i = 0;
        int totalSize = 0;
        while ((len = fis.read(buffer)) != -1) {
            totalSize += len;
            currentSize += len;
            if (currentSize > partSize) {
                // 写好一份有多余的
                // 需要写入的长度
                int writeLen = currentSize - partSize;
                fos.write(buffer, 0, writeLen);
                fos.close();
                // 多出来的长度
                int leftLen = len - writeLen;
                currentSize = leftLen;
                byte[] tempBuffer = new byte[1024];
                move(tempBuffer, writeLen, leftLen);
                File part = new File("H:\\partFiles\\" + i++ + ".part");
                fos = new FileOutputStream(part);
                fos.write(tempBuffer, 0, leftLen);
            } else if (currentSize < partSize) {
                // 不够写好一份
                if (fos == null) {
                    File part = new File("H:\\partFiles\\" + i++ + ".part");
                    fos = new FileOutputStream(part);
                }
                fos.write(buffer, 0, len);
            } else {
                // 刚好够写好一份
                fos.write(buffer,0,len);
                fos.close();
                currentSize = 0;
                fos = null;
            }
        }
        fos.close();
        System.out.println("切割数目：" + i);
        System.out.println("totalSize = " + totalSize);
    }

    /**
     * @param buf      读取大小
     * @param writeLen 已经写入到文件中的
     * @param leftLen  内存中剩余的字节长度
     */
    public static void move(byte[] buf, int writeLen, int leftLen) {
        byte[] newbuf = new byte[8192];
        for (int i = 0; i < leftLen; i++) {
            if (buf[writeLen + i] != 0) {
                newbuf[i] = buf[writeLen + i];
            }
        }
        Arrays.fill(buf, (byte) 0);
        System.arraycopy(newbuf, 0, buf, 0, newbuf.length);
    }
}
/*
21,986,442
 5,242,880
 5,242,880
 5,242,880
 5,242,880
 1,014,922
 */