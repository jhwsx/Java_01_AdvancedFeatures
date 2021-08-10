package com.java.advanced.features.io.bixiangdong.p26_bytearraystream;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

/**
 * CharArrayReader CharArrayWriter
 * 1, CharArrayReader 此类实现一个可用作字符输入流的字符缓冲区。
 * 2, CharArrayWriter 此类实现一个可用作 Writer 的字符缓冲区。缓冲区会随向流中写入数据而自动增长。
 * 可使用 toCharArray() 和 toString() 获取数据。
 * 注：在此类上调用 close() 无效，并且在关闭该流后可以调用此类中的各个方法，而不会产生任何 IOException。
 * @author wangzhichao
 * @since 2021/8/9
 */
public class CharArrayDemo {
    public static void main(String[] args) throws IOException {
        CharArrayReader car = new CharArrayReader("中国hello".toCharArray());
        CharArrayWriter caw = new CharArrayWriter();
        int ch;
        while ((ch = car.read()) != -1) {
            caw.write(ch);
        }

        System.out.println(caw);
    }
}
