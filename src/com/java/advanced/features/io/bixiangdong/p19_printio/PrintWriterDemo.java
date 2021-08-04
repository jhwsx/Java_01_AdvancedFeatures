package com.java.advanced.features.io.bixiangdong.p19_printio;

import java.io.*;

/**
 * PrintWriter 是 Writer 的子类
 *
 * 向文本输出流打印对象的格式化表示形式
 *
 * 构造方法：
 *      接收一个文件或文件路径
 *      接收一个字节输出流，并可指定自动刷新
 *      就收一个字符输出流，并可指定自动刷新
 * @author wangzhichao
 * @since 2021/8/3
 */
public class PrintWriterDemo {
    public static void main(String[] args) throws IOException {
        PrintWriter printWriter = new PrintWriter("printwriter.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            if ("over".equals(line)) break;
            System.out.println(line);
            printWriter.println(line);
            printWriter.flush();
        }
        printWriter.close();
    }
}
