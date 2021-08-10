package com.java.advanced.features.io.bixiangdong.p26_bytearraystream;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * StringReader StringWriter
 * 1, StringReader 其源为一个字符串的字符流。
 * 2, StringWriter 一个字符流，可以用其回收在字符串缓冲区中的输出来构造字符串。
 * 关闭 StringWriter 无效。此类中的方法在关闭该流后仍可被调用，而不会产生任何 IOException。
 *
 * @author wangzhichao
 * @since 2021/8/9
 */
public class StringReaderWriterDemo {
    public static void main(String[] args) throws IOException {
        StringReader sr = new StringReader("中国hello");
        StringWriter sw = new StringWriter();
        char[] buff = new char[1024];
        int len;
        while ((len = sr.read(buff)) != -1) {
            sw.write(buff, 0, len);
        }

        System.out.println(sw.toString());
    }
}
