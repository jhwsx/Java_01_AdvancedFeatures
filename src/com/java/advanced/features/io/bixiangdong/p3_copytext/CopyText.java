package com.java.advanced.features.io.bixiangdong.p3_copytext;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 复制文本文件
 *
 * @author wangzhichao
 * @since 2021/7/21
 */
public class CopyText {
    public static void main(String[] args) throws IOException {
        // 1, 创建一个字符输入流，用于把文件读入内存
        FileReader fileReader = new FileReader("src.txt");
        // 2, 创建一个字符输出流，用于把内存的字符写入外部文件中
        FileWriter fileWriter = new FileWriter("dst.txt");

        // 3, 进行读写操作
        int ch;
        // 一次读取一个字符
        while((ch = fileReader.read()) != -1) {
            // 一次写入一个字符
            fileWriter.write(ch);
        }

        // 4, 关闭流资源
        fileWriter.close();
        fileReader.close();
    }
}
