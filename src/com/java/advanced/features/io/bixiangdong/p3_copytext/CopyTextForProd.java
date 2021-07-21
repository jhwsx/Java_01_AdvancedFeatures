package com.java.advanced.features.io.bixiangdong.p3_copytext;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 复制文本文件
 * <p>
 * 正式开发的写法
 *
 * @author wangzhichao
 * @since 2021/7/21
 */
public class CopyTextForProd {
    public static void main(String[] args) {
        // 1, 创建一个字符输入流，用于把文件读入内存
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader("src.txt");
            // 2, 创建一个字符输出流，用于把内存的字符写入外部文件中
            fileWriter = new FileWriter("dst.txt");

            // 3, 进行读写操作
            char[] buffer = new char[1024];
            int length;
            while((length = fileReader.read(buffer)) != -1) {
                fileWriter.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4, 关闭流资源
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
