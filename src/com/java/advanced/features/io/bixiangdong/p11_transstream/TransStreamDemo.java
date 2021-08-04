package com.java.advanced.features.io.bixiangdong.p11_transstream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 使用 InputStreamReader 来优化 ReadKey 的例子
 * 现在可以读取中文输入，而且代码简洁。
 *
 * @author wangzhichao
 * @since 2021/7/23
 */
public class TransStreamDemo {
    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        // 将字节流转为字符流，这里使用了转换流 = 字节流 + 码表，这里是解码
        InputStreamReader isr = new InputStreamReader(in);
        // 使用缓冲流，更加高效，可以使用 readLine() 方法
        BufferedReader br = new BufferedReader(isr);

        String line;
        while ((line = br.readLine()) != null) {
            if ("over".equals(line)) {
                break;
            }
            System.out.println(line.toUpperCase());
        }
    }
}
