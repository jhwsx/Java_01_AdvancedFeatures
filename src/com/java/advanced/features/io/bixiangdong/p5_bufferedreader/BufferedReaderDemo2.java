package com.java.advanced.features.io.bixiangdong.p5_bufferedreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 需求：读取一个文本文件到内存，打印到控制台
 * @author wangzhichao
 * @since 2021/7/21
 */
public class BufferedReaderDemo2 {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("buffer.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        char[] buffer = new char[1024];
        int length;
        while((length = bufferedReader.read(buffer)) != -1) {
            System.out.println(new String(buffer, 0, length));
        }

        bufferedReader.close();
    }
}
