package com.java.advanced.features.io.bixiangdong.p9_fileoutputstream;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wangzhichao
 * @since 2021/7/21
 */
public class FileOutputStreamDemo {
    public static final String LINE_SEPRATOR = System.getProperty("line.separator");
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("file.txt");
        fileOutputStream.write(("adbcde" + LINE_SEPRATOR + "HIJKLM").getBytes());
        // 不调用 flush，在 write 时就把内容写到了文件里面。
        fileOutputStream.close();
    }
}
