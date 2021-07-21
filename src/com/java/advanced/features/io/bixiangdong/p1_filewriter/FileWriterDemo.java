package com.java.advanced.features.io.bixiangdong.p1_filewriter;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 把一些文字存储到硬盘的文件中
 *
 * @author wangzhichao
 * @since 2021/7/21
 */
public class FileWriterDemo {
    public static final String LINE_SEPRATOR = System.getProperty("line.separator");
    public static void main(String[] args) throws IOException {
        // 创建一个字符输出流对象，用于向硬盘的文件中写入字符
        FileWriter fileWriter = new FileWriter("file.txt");
        // 写入字符
        // 查看 OutputStreamWriter 的文档：
        // 每次调用 write() 方法都会导致在给定字符（或字符集）上调用编码转换器。
        // 在写入底层输出流之前，得到的这些字节将在缓冲区中累积。可以指定此缓冲区的大小，
        // 不过，默认的缓冲区对多数用途来说已足够大。注意，传递给 write() 方法的字符没有缓冲。

        // 因此，必须调用一次 flush() 方法，刷新该流的缓冲，才能将字符写入文件。
        fileWriter.write("abcde" + LINE_SEPRATOR + "FGHIJK");

        fileWriter.flush();
        // 关闭此流，但要先刷新它。在关闭该流之后，再调用 write() 或 flush() 将导致抛出 IOException。关闭以前关闭的流无效。
        fileWriter.close();
    }
}
