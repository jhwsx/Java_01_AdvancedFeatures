package com.java.advanced.features.io.bixiangdong.p11_transstream;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author wangzhichao
 * @since 2021/7/23
 */
public class ReadKey {
    public static void main(String[] args) throws IOException {
//        readKey1();
        readKey2();
    }

    /**
     * 不足：不能读取中文
     * @throws IOException
     */
    private static void readKey2() throws IOException {
        // 读取键盘输入，如果是 over 则结束；反之，转为大写并输出
        InputStream in = System.in;
        // 用来存放读取到的字符串
        StringBuilder sb = new StringBuilder();

        int ch;

        while((ch = in.read()) != -1) {
            if (ch == '\r') {
            } else if (ch == '\n') {
                // 用户已经输入一行了，准备判断
                String line = sb.toString();
                if ("over".equals(line)) {
                    break;
                } else {
                    // 转为大写，并输出。清空 sb
                    System.out.println(line.toUpperCase());
                    sb.delete(0, line.length());
                }
            } else {
                sb.append(((char) ch));
            }
        }
    }

    private static void readKey1() throws IOException {
        InputStream in = System.in;

        int ch = in.read(); // 阻塞式方法

        System.out.println(ch);

        // System.in 流资源由系统管理打开和释放。
    }
}
