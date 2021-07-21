package com.java.advanced.features.io.bixiangdong.p5_bufferedreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 需求：读取一个文本文件到内存，打印到控制台
 * @author wangzhichao
 * @since 2021/7/21
 */
public class BufferedReaderDemo {
    public static void main(String[] args) throws IOException {
        // 1, 创建字符输入流对象，用于把外部文件读入内存
        FileReader fileReader = new FileReader("buffer.txt");
        // 2, 创建字符输入流的缓冲区对象，关联需要被缓冲的字符输入流对象
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        // 3, 进行读操作
        int ch;
        while((ch = bufferedReader.read()) != -1) {
            System.out.println(((char) ch));
        }
        // 4, 关闭流资源
        bufferedReader.close();
    }
}
