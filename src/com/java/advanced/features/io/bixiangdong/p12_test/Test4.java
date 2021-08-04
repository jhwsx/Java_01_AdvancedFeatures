package com.java.advanced.features.io.bixiangdong.p12_test;

import java.io.*;

/**
 * 需求4：读取键盘录入数据，显示在控制台上。
 *
 * @author wangzhichao
 * @since 2021/7/25
 */
public class Test4 {
    public static void main(String[] args) throws IOException {
        // 1, 明确源和目的
        // 源：InputStream Reader
        // 目的：OutputStream Writer
        // 2, 明确数据是否是纯文本数据
        // 源：是纯文本，Reader
        // 目的：是纯文本：Writer
        // 3，明确具体的设备
        // 源：键盘：System.in，属于InputStream
        // 目的：控制台：System.out，属于PrintStream
        InputStream in = System.in;
        PrintStream out = System.out;
        // 4，需要额外的功能
        // 需要转换
        InputStreamReader isr = new InputStreamReader(in);
        OutputStreamWriter osr = new OutputStreamWriter(out);
        // 需要高效缓冲
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osr);

        String line;
        while((line = br.readLine()) != null) {
            if ("over".equals(line)) {
                break;
            }
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
    }
}
