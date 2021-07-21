package com.java.advanced.features.io.bixiangdong.p6_copytextbuffer;

import java.io.*;

/**
 * 复制文本文件
 * <p>
 * 正式开发的写法
 *
 * @author wangzhichao
 * @since 2021/7/21
 */
public class CopyTextBufferForProd {
    public static void main(String[] args) {

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            // 1, 创建一个字符输入流，用于把文件读入内存
            FileReader  fileReader = new FileReader("src.txt");
            // 2, 创建一个字符输入流缓冲区对象，关联被缓冲的字符输入流对象
            bufferedReader = new BufferedReader(fileReader);
            // 3, 创建一个字符输出流，用于把内存的字符写入外部文件中
            FileWriter  fileWriter = new FileWriter("dst.txt");
            // 4, 创建一个字符输出流缓冲区对象
            bufferedWriter = new BufferedWriter(fileWriter);
            // 3, 进行读写操作
            String line;
            while((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4, 关闭流资源
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
