package com.java.advanced.features.io;

import java.io.*;

public class _05_OutputStreamWriterTest {
    private static final String STRING = "I love China";

    public static void main(String[] args) {
        testOutputStreamWriter();
    }

    /**
     * 这个例子需要注意的地方有：
     * 1，因为 FileOutputStream 是可追加的，所以必须在最后一起关闭流；
     * 2，必须调用 flush() 方法
     */
    private static void testOutputStreamWriter() {
        try {
            // 注意：第二个参数设置为 true，表示写出数据到文件时追加到文件后面
            FileOutputStream fos = new FileOutputStream(
                    "testtxt/_05_OutputStreamWriterTest.txt", true);
            OutputStreamWriter oswDefault = new OutputStreamWriter(fos);
            BufferedWriter bwDefault = new BufferedWriter(oswDefault);
            bwDefault.write(STRING);
            bwDefault.newLine();
            bwDefault.flush();
            // 默认是 UTF-8, 这个跟 IDE 中设置的编码有关
            System.out.println("oswDefault encoding: " + oswDefault.getEncoding());


            OutputStreamWriter oswGBK = new OutputStreamWriter(fos, "GBK");
            BufferedWriter bwGBK = new BufferedWriter(oswGBK);
            bwGBK.write(STRING + "GBK");
            bwGBK.newLine();
            bwGBK.flush();
            System.out.println("oswGBK encoding: " + oswGBK.getEncoding());

            OutputStreamWriter oswUTF8 = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bwUTF8 = new BufferedWriter(oswUTF8);
            bwUTF8.write(STRING + "UTF-8");
            bwUTF8.newLine();
            bwUTF8.flush();
            System.out.println("oswUTF8 encoding: " + oswUTF8.getEncoding());

            bwUTF8.close();
            bwGBK.close();
            bwDefault.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
