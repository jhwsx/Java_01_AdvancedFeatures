package com.java.advanced.features.io.bixiangdong.p2_filereader;

import java.io.FileReader;
import java.io.IOException;

/**
 * 需求：读取一个文本文件到内存，打印到控制台
 * @author wangzhichao
 * @since 2021/7/21
 */
public class FileReaderDemo2 {
    public static void main(String[] args) throws IOException {
        // 创建一个字符输入流对象，用于关联一个已存在的文件
        FileReader fileReader = new FileReader("file.txt");

        char[] buffer = new char[1024];
        int length;
        while((length = fileReader.read(buffer)) != -1) {
            System.out.println(new String(buffer, 0, length));
        }

        // 关闭流资源
        fileReader.close();
    }
}
