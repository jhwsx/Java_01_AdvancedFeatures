package com.java.advanced.features.io.bixiangdong.p13_transformcharset;

import java.io.*;

/**
 * 需求：将一个中文字符串按照指定的编码表写入到一个文件中，并重新读取该文件输出到控制台
 *
 * 使用转换流可以指定编码表的功能
 *
 * @author wangzhichao
 * @since 2021/7/25
 */
public class Test1 {
    public static void main(String[] args) throws IOException {
        OutputStreamWriter osr = new OutputStreamWriter(new FileOutputStream("gbk.txt"), "gbk");
        osr.write("静下心学习");
        osr.close();

        InputStreamReader isr = new InputStreamReader(new FileInputStream("gbk.txt"), "gbk");
        char[] buff = new char[1024];
        int len = isr.read(buff);
        System.out.println(new String(buff, 0, len));
    }
}
