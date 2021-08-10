package com.java.advanced.features.io.bixiangdong.p26_bytearraystream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * ByteArrayInputStream ByteArrayOutputStream
 * 1，为什么关闭此两个流无效？因为它们没有调用底层的流资源，不需要关闭。
 * 2，ByteArrayInputStream 包含一个内部缓冲区，该缓冲区包含从流中读取的字节。
 * 内部计数器跟踪 read 方法要提供的下一个字节。
 * 3，此类实现了一个输出流，其中的数据被写入一个 byte 数组。缓冲区会随着数据的不断写入而自动增长。
 * 可使用 toByteArray() 和 toString() 获取数据。
 * 4，注意：操作的数据太大了不行，毕竟是在内存里面。
 * @author wangzhichao
 * @since 2021/8/9
 */
public class ByteArrayStreamDemo {
    public static void main(String[] args) {
        ByteArrayInputStream bais = new ByteArrayInputStream("中国hello".getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int ch;
        while ((ch = bais.read()) != -1) {
            baos.write(ch);
        }
        System.out.println(baos.toString());
    }
}
