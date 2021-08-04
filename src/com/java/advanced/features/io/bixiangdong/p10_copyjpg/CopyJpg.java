package com.java.advanced.features.io.bixiangdong.p10_copyjpg;

import java.io.*;

/**
 * @author wangzhichao
 * @since 2021/7/21
 */
public class CopyJpg {
    public static void main(String[] args) throws IOException {
//        bytestream();
        charstream();
    }

    private static void bytestream() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("weather1.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("weather_copy.jpg");
        byte[] buf = new byte[1024];
        int len;
        while((len = fileInputStream.read(buf)) != -1) {
            fileOutputStream.write(buf, 0, len);
        }
        fileOutputStream.close();
        fileInputStream.close();
    }

    // 不可以
    // https://blog.csdn.net/weixin_30561425/article/details/98719546
    // https://www.cnblogs.com/xqry/p/6698138.html
    private static void charstream() throws IOException {
        FileReader fileReader = new FileReader("weather1.jpg");
        FileWriter fileWriter = new FileWriter("weather_2.jpg");
        char[] buf = new char[1024];
        int len;
        while((len = fileReader.read(buf)) != -1) {
            fileWriter.write(buf, 0, len);
            fileWriter.flush();
        }
        fileWriter.close();
        fileReader.close();
    }
}
