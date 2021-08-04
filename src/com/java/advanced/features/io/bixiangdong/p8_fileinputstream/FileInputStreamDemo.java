package com.java.advanced.features.io.bixiangdong.p8_fileinputstream;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author wangzhichao
 * @since 2021/7/21
 */
public class FileInputStreamDemo {
    public static void main(String[] args) throws IOException {
        // 1, 创建一个字节输入流对象，和指定的文件相关联
        FileInputStream fileInputStream = new FileInputStream("file.txt");
        byte[] buf = new byte[1024];
        // 2, 自定义缓冲数组进行读取
        int len;
        while((len = fileInputStream.read(buf)) != -1) {
            System.out.println(new String(buf, 0, len));
        }
        // 3, 关闭流资源
        fileInputStream.close();
    }
}
