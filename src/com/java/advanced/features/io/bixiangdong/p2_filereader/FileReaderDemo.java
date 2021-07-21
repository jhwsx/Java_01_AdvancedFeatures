package com.java.advanced.features.io.bixiangdong.p2_filereader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 需求：读取一个文本文件到内存，打印到控制台
 * @author wangzhichao
 * @since 2021/7/21
 */
public class FileReaderDemo {
    public static void main(String[] args) throws IOException {
        // 创建一个字符输入流对象，用于关联一个已存在的文件
        FileReader fileReader = new FileReader("file.txt");
        int ch;
        // read() 方法：读取单个字符。返回读取的字符，如果已到达流的末尾，则返回 -1
        while ((ch = fileReader.read()) != -1) {
            System.out.println(((char) ch));
        }
        // 关闭流资源
        fileReader.close();
    }
}
