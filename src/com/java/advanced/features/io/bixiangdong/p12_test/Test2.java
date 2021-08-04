package com.java.advanced.features.io.bixiangdong.p12_test;

import java.io.*;

/**
 * 需求2：读取键盘录入信息，并写入到一个文件中
 * @author wangzhichao
 * @since 2021/7/24
 */
public class Test2 {
    public static void main(String[] args) throws IOException {
        // 1，明确源和目的
        // 源：InputStream Reader
        // 目的：OutputStream Writer
        // 2, 明确数据是否是纯文本数据
        // 源：是纯文本，Reader
        // 目的：是纯文本，Writer
        // 3，明确具体的设备
        // 源设备：键盘 System.in，即InputStream
        // 目的设备：硬盘 File，使用FileWriter
        // 4, 是否需要其他额外功能
        // 需要转换，字节流转字符流，InputStreamReader
        // 需要高效，BufferedReader，BufferedWriter
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter("test2.txt"));
        String line;
        while((line = br.readLine()) != null) {
            if ("over".equals(line)) {
                break;
            }
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }

}
