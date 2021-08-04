package com.java.advanced.features.io.bixiangdong.p12_test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 需求1：复制一个文本文件
 *
 * @author wangzhichao
 * @since 2021/7/24
 */
public class Test1 {
    public static void main(String[] args) throws Exception {
        // 1, 明确源和目的
        // 源：InputStream Reader
        // 目的：OutputStream Writer
        // 2, 明确数据是否是纯文本数据
        // 源：是纯文本，Reader
        // 目的：是纯文本，Writer
        // 3, 明确具体的设备
        // 源设备：硬盘 File，所以使用 FileReader
        // 目的设备：硬盘 File，所以使用 FileWriter
        FileReader fileReader = new FileReader("src.txt");
        FileWriter fileWriter = new FileWriter("dst.txt");
        // 4, 是否需要其他额外功能
        // 添加高效，缓冲区
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
