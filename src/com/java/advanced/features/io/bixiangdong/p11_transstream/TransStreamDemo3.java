package com.java.advanced.features.io.bixiangdong.p11_transstream;

import java.io.*;

/**
 * 加入使用 OutputStreamWriter
 * 正式写法
 * @author wangzhichao
 * @since 2021/7/23
 */
public class TransStreamDemo3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        OutputStream out = System.out;
        OutputStreamWriter osw = new OutputStreamWriter(out);
        BufferedWriter bw = new BufferedWriter(osw);

        String line;
        while ((line = br.readLine()) != null) {
            if ("over".equals(line)) {
                break;
            }
            bw.write(line.toUpperCase());
            bw.newLine();
            // 一定要刷新缓冲区
            bw.flush();
        }
    }
}
