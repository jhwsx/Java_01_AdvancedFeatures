package com.java.advanced.features.io.bixiangdong.p4_bufferedwriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 把一些文字存储到硬盘的文件中
 *
 * @author wangzhichao
 * @since 2021/7/21
 */
public class BufferedWriterDemo {
    public static void main(String[] args) throws IOException {
        // 1, 创建一个字符输出流对象，用于向硬盘的文件中写入字符
        FileWriter fileWriter = new FileWriter("buffer.txt");
        // 2, 创建一个字符输出流的缓冲区对象，和指定要被缓冲的字符输出流相关联
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        // 3, 写入字符
        for (int i = 0; i < 4; i++) {
            bufferedWriter.write("abcde" + i);
            bufferedWriter.newLine();
            // 调用一次 flush，及时把字符刷新到文件中。
            // 这是 BufferedWriter 自己的 flush 方法，作用是刷新该流的缓冲。
            bufferedWriter.flush();
        }
        // 4, 关闭流资源
        // 调用包装器的 close 方法，会依次调用被包装者的 close 方法。
        bufferedWriter.close();
    }
}
