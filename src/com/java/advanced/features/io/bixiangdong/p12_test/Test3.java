package com.java.advanced.features.io.bixiangdong.p12_test;

import java.io.*;

/**
 * 需求3：将一个文本文件显示在控制台上。
 *
 * @author wangzhichao
 * @since 2021/7/25
 */
public class Test3 {
    public static void main(String[] args) throws Exception {
        // 1, 明确源和目的
        // 源：InputStream Reader
        // 目的：OutputStream Writer
        // 2，是否是纯文本
        // 源：是纯文本 Reader
        // 目的：是纯文本 Writer
        // 3，明确具体的设备
        // 源设备：硬盘 File，所以用 FileReader
        // 目的设备：控制台 System.out
        FileReader fr = new FileReader("src.txt");
        PrintStream out = System.out;
        // 4，是否需要其他额外功能
        // 需要转换，字符流转为字节流
        OutputStreamWriter osw = new OutputStreamWriter(out);
        // 需要高效
        BufferedReader br = new BufferedReader(fr);
        BufferedWriter bw = new BufferedWriter(osw);

        String line;
        while((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        br.close();
    }
}
